package com.wpaul15.scryfall.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wpaul15.scryfall.api.model.Card;
import com.wpaul15.scryfall.api.model.Language;
import java.util.UUID;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public final class ScryfallApi {

  private static String BASE_URL = "https://api.scryfall.com";

  private static final ObjectMapper OBJECT_MAPPER =
      JsonMapper.builder()
          .addModule(new JavaTimeModule())
          // TODO: Use the property naming strategy. There is a known issue regarding the naming
          //       strategy and records.
          // See: https://github.com/FasterXML/jackson-databind/issues/3102
          //          .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
          .build();

  /**
   * Fetches a single {@link Card} with the given name, using an exact-match search. That is, {@code
   * name} must be exactly the name of the desired card. {@code name} is case-insensitive and
   * punctuation is optional.
   *
   * <p>If a card has been printed in multiple sets, this method will return only the most recent
   * printing. If the version from a specific set is desired, use {@link #getCardByExactName(String,
   * String)}.
   *
   * @param name the card's name
   * @return a {@link Card} with the given name
   */
  public Mono<Card> getCardByExactName(String name) {
    return getSingle(String.format("/cards/named?exact=%s", name), Card.class);
  }

  /**
   * Fetches a single {@link Card} from the given set with the given name, using an exact-match
   * search. That is, {@code name} must be exactly the name of the desired card. {@code name} is
   * case-insensitive and punctuation is optional.
   *
   * <p>Search results will be limited to cards in the given set.
   *
   * @param name the card's name
   * @param setCode the code for the set containing the card
   * @return a {@link Card} with the given name
   */
  public Mono<Card> getCardByExactName(String name, String setCode) {
    return getSingle(String.format("/cards/named?exact=%s&set=%s", name, setCode), Card.class);
  }

  /**
   * Fetches a single {@link Card} from the given set with the given collector number.
   *
   * @param setCode the code for the set containing the card
   * @param collectorNumber the card's collector number within the set
   * @return a {@link Card} with the given set and collector number
   */
  public Mono<Card> getCardByCollectorNumber(String setCode, String collectorNumber) {
    return getSingle(String.format("/cards/%s/%s", setCode, collectorNumber), Card.class);
  }

  /**
   * Fetches a single {@link Card} from the given set with the given collector number and language.
   * That is, this {@code Card} will have a unique ID representing the card printed in the given
   * language.
   *
   * @param setCode the code for the set containing the card
   * @param collectorNumber the card's collector number within the set
   * @param language the card language
   * @return a {@link Card} with the given set, collector number, and language
   */
  public Mono<Card> getCardByCollectorNumber(
      String setCode, String collectorNumber, Language language) {
    return getSingle(
        String.format("/cards/%s/%s/%s", setCode, collectorNumber, language), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given Multiverse ID.
   *
   * @param multiverseId the card's Multiverse ID
   * @return a {@link Card} with the given Multiverse ID
   */
  public Mono<Card> getCardByMultiverseId(int multiverseId) {
    return getSingle(String.format("/cards/multiverse/%d", multiverseId), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given MTG Online ID.
   *
   * @param mtgoId the card's MTG Online ID
   * @return a {@link Card} with the given MTG Online ID
   */
  public Mono<Card> getCardByMtgoId(int mtgoId) {
    return getSingle(String.format("/cards/mtgoid/%d", mtgoId), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given MTG Arena ID.
   *
   * @param arenaId the card's MTG Arena ID
   * @return a {@link Card} with the given MTG Arena ID
   */
  public Mono<Card> getCardByArenaId(int arenaId) {
    return getSingle(String.format("/cards/arena/%d", arenaId), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given TCGPlayer ID.
   *
   * @param tcgplayerId the card's TCGPlayer ID
   * @return a {@link Card} with the given TCGPlayer ID
   */
  public Mono<Card> getCardByTcgplayerId(int tcgplayerId) {
    return getSingle(String.format("/cards/tcgplayer/%d", tcgplayerId), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given Cardmarket ID.
   *
   * @param cardmarketId the card's Cardmarket ID
   * @return a {@link Card} with the given Cardmarket ID
   */
  public Mono<Card> getCardByCardmarketId(int cardmarketId) {
    return getSingle(String.format("/cards/cardmarket/%d", cardmarketId), Card.class);
  }

  /**
   * Fetches a single {@link Card} with the given ID.
   *
   * @param id the card's ID
   * @return a {@link Card} with the given ID
   */
  public Mono<Card> getCardById(UUID id) {
    return getSingle(String.format("/cards/%s", id), Card.class);
  }

  void setBaseUrl(String baseUrl) {
    BASE_URL = baseUrl;
  }

  private <T> Mono<T> getSingle(String endpoint, Class<T> clazz) {
    return getClient()
        .get()
        .uri(endpoint)
        .responseContent()
        .aggregate()
        .asString()
        .flatMap(
            body -> {
              try {
                return Mono.just(OBJECT_MAPPER.readerFor(clazz).readValue(body));
              } catch (JsonProcessingException ex) {
                return Mono.error(ex);
              }
            });
  }

  private static HttpClient getClient() {
    return ClientHolder.CLIENT;
  }

  private static final class ClientHolder {

    private static final HttpClient CLIENT =
        HttpClient.create()
            .baseUrl(BASE_URL)
            .headers(
                headers -> {
                  headers.add("Accept", "application/json");
                  headers.add("Content-Type", "application/json");
                });
  }
}

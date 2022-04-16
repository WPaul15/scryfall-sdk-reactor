package com.wpaul15.scryfall.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wpaul15.scryfall.api.model.Card;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public final class ScryfallApi {

  private static String BASE_URL = "https://api.scryfall.com";

  private static final ObjectMapper OBJECT_MAPPER =
      JsonMapper.builder()
          .addModule(new JavaTimeModule())
          /*
          TODO: Use the property naming strategy. There is a known issue regarding the naming
                strategy and records.
          See: https://github.com/FasterXML/jackson-databind/issues/3102
           */
          //          .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
          .build();

  public Mono<Card> getCardByMultiverseId(int multiverseId) {
    return getSingle("/cards/multiverse/" + multiverseId, Card.class);
  }

  public Mono<Card> getCardByMtgoId(int mtgoId) {
    return getSingle("/cards/mtgoid/" + mtgoId, Card.class);
  }

  public Mono<Card> getCardByArenaId(int arenaId) {
    return getSingle("/cards/arena/" + arenaId, Card.class);
  }

  public Mono<Card> getCardByTcgplayerId(int tcgplayerId) {
    return getSingle("/cards/tcgplayer/" + tcgplayerId, Card.class);
  }

  public Mono<Card> getCardByCardmarketId(int cardmarketId) {
    return getSingle("/cards/cardmarket/" + cardmarketId, Card.class);
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

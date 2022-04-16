package com.wpaul15.scryfall.api;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wpaul15.scryfall.api.model.Card;
import com.wpaul15.scryfall.api.model.Language;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class CardApiTest extends ScryfallApiTest {

  @Test
  void shouldGetCardByExactName() throws JsonProcessingException {
    String name = "Archetype of Courage";

    Card expected = Card.builder().name(name).build();

    stubFor(
        get(urlPathEqualTo("/cards/named"))
            .withQueryParam("exact", equalTo(name))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByExactName(name))
        .assertNext(card -> assertThat(card.name()).isEqualTo(name))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByExactNameWithSet() throws JsonProcessingException {
    String name = "Steel Hellkite";
    String setCode = "som";

    Card expected = Card.builder().name(name).set(setCode).build();

    stubFor(
        get(urlPathEqualTo("/cards/named"))
            .withQueryParams(
                Map.ofEntries(entry("exact", equalTo(name)), entry("set", equalTo(setCode))))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByExactName(name, setCode))
        .assertNext(
            card -> {
              assertThat(card.name()).isEqualTo(name);
              assertThat(card.set()).isEqualTo(setCode);
            })
        .verifyComplete();
  }

  @Test
  void shouldGetCardByFuzzyName() throws JsonProcessingException {
    String partialName = "archet cour";
    String name = "Archetype of Courage";

    Card expected = Card.builder().name(name).build();

    stubFor(
        get(urlPathEqualTo("/cards/named"))
            .withQueryParam("fuzzy", equalTo(partialName))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByFuzzyName(partialName))
        .assertNext(card -> assertThat(card.name()).isEqualTo(name))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByFuzzyNameWithSet() throws JsonProcessingException {
    String partialName = "Steel hell";
    String name = "Steel Hellkite";
    String setCode = "som";

    Card expected = Card.builder().name(name).set(setCode).build();

    stubFor(
        get(urlPathEqualTo("/cards/named"))
            .withQueryParams(
                Map.ofEntries(entry("fuzzy", equalTo(partialName)), entry("set", equalTo(setCode))))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByFuzzyName(partialName, setCode))
        .assertNext(
            card -> {
              assertThat(card.name()).isEqualTo(name);
              assertThat(card.set()).isEqualTo(setCode);
            })
        .verifyComplete();
  }

  @Test
  void shouldGetCardByCollectorNumber() throws JsonProcessingException {
    String setCode = "xln";
    String collectorNumber = "96";

    Card expected =
        Card.builder().name("Costly Plunder").set(setCode).collectorNumber(collectorNumber).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/%s/%s", setCode, collectorNumber)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByCollectorNumber(setCode, collectorNumber))
        .assertNext(
            card -> {
              assertThat(card.set()).isEqualTo(setCode);
              assertThat(card.collectorNumber()).isEqualTo(collectorNumber);
            })
        .verifyComplete();
  }

  @Test
  void shouldGetCardByCollectorNumberWithLanguage() throws JsonProcessingException {
    String setCode = "dom";
    String collectorNumber = "1";
    Language language = Language.GERMAN;
    String printedName = "Karn, Urzas Spross";

    Card expected =
        Card.builder()
            .name("Karn, Scion of Urza")
            .printedName(printedName)
            .lang(language)
            .set(setCode)
            .collectorNumber(collectorNumber)
            .build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/%s/%s/%s", setCode, collectorNumber, language)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByCollectorNumber(setCode, collectorNumber, language))
        .assertNext(
            card -> {
              assertThat(card.lang()).isEqualTo(language);
              assertThat(card.printedName()).isEqualTo(printedName);
              assertThat(card.set()).isEqualTo(setCode);
              assertThat(card.collectorNumber()).isEqualTo(collectorNumber);
            })
        .verifyComplete();
  }

  @Test
  void shouldGetCardByMultiverseId() throws JsonProcessingException {
    int multiverseId = 409574;

    Card expected = Card.builder().name("Strip Mine").multiverseIds(List.of(multiverseId)).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/multiverse/%s", multiverseId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByMultiverseId(multiverseId))
        .assertNext(card -> assertThat(card.multiverseIds()).contains(multiverseId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByMtgoId() throws JsonProcessingException {
    int mtgoId = 54957;

    Card expected = Card.builder().name("Ghost Quarter").mtgoId(mtgoId).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/mtgo/%s", mtgoId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByMtgoId(mtgoId))
        .assertNext(card -> assertThat(card.mtgoId()).isEqualTo(mtgoId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByArenaId() throws JsonProcessingException {
    int arenaId = 67330;

    Card expected = Card.builder().name("Yargle, Glutton of Urborg").arenaId(arenaId).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/arena/%s", arenaId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByArenaId(arenaId))
        .assertNext(card -> assertThat(card.arenaId()).isEqualTo(arenaId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByTcgplayerId() throws JsonProcessingException {
    int tcgplayerId = 162145;

    Card expected = Card.builder().name("Rona, Disciple of Gix").tcgplayerId(tcgplayerId).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/tcgplayer/%s", tcgplayerId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByTcgplayerId(tcgplayerId))
        .assertNext(card -> assertThat(card.tcgplayerId()).isEqualTo(tcgplayerId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByCardmarketId() throws JsonProcessingException {
    int cardmarketId = 379041;

    Card expected = Card.builder().name("Embodiment of Agonies").cardmarketId(cardmarketId).build();

    stubFor(
        get(urlPathEqualTo(String.format("/cards/cardmarket/%s", cardmarketId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardByCardmarketId(cardmarketId))
        .assertNext(card -> assertThat(card.cardmarketId()).isEqualTo(cardmarketId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardById() throws JsonProcessingException {
    UUID id = UUID.fromString("e9d5aee0-5963-41db-a22b-cfea40a967a3");

    Card expected = Card.builder().name("Dusk // Dawn").id(id).build();

    stubFor(get(urlPathEqualTo(String.format("/cards/%s", id))).willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getCardById(id))
        .assertNext(card -> assertThat(card.id()).isEqualTo(id))
        .verifyComplete();
  }
}

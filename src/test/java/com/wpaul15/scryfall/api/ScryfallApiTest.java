package com.wpaul15.scryfall.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wpaul15.scryfall.api.model.Card;
import com.wpaul15.scryfall.api.model.Language;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ScryfallApiTest {

  private static final ObjectMapper OBJECT_MAPPER =
      JsonMapper.builder().addModule(new JavaTimeModule()).build();

  private static MockWebServer mockWebServer;
  private static ScryfallApi scryfallApi;

  @BeforeAll
  static void beforeAll() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();

    scryfallApi = new ScryfallApi();
    scryfallApi.setBaseUrl(mockWebServer.url("/").toString());
  }

  @AfterAll
  static void afterAll() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  void shouldGetCardByExactName() throws JsonProcessingException {
    String name = "Archetype of Courage";

    Card expected = Card.builder().name(name).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByExactName(name))
        .assertNext(card -> assertThat(card.name()).isEqualTo(name))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByExactNameWithSet() throws JsonProcessingException {
    String name = "Steel Hellkite";
    String setCode = "som";

    Card expected = Card.builder().name(name).set(setCode).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByExactName(name, setCode))
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

    mockWebServer.enqueue(getMockResponse(expected));

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

    mockWebServer.enqueue(getMockResponse(expected));

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

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByMultiverseId(multiverseId))
        .assertNext(card -> assertThat(card.multiverseIds()).contains(multiverseId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByMtgoId() throws JsonProcessingException {
    int mtgoId = 54957;

    Card expected = Card.builder().name("Ghost Quarter").mtgoId(mtgoId).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByMtgoId(mtgoId))
        .assertNext(card -> assertThat(card.mtgoId()).isEqualTo(mtgoId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByArenaId() throws JsonProcessingException {
    int arenaId = 67330;

    Card expected = Card.builder().name("Yargle, Glutton of Urborg").arenaId(arenaId).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByArenaId(arenaId))
        .assertNext(card -> assertThat(card.arenaId()).isEqualTo(arenaId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByTcgplayerId() throws JsonProcessingException {
    int tcgplayerId = 162145;

    Card expected = Card.builder().name("Rona, Disciple of Gix").tcgplayerId(tcgplayerId).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByTcgplayerId(tcgplayerId))
        .assertNext(card -> assertThat(card.tcgplayerId()).isEqualTo(tcgplayerId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardByCardmarketId() throws JsonProcessingException {
    int cardmarketId = 379041;

    Card expected = Card.builder().name("Embodiment of Agonies").cardmarketId(cardmarketId).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByCardmarketId(cardmarketId))
        .assertNext(card -> assertThat(card.cardmarketId()).isEqualTo(cardmarketId))
        .verifyComplete();
  }

  @Test
  void shouldGetCardById() throws JsonProcessingException {
    UUID id = UUID.fromString("e9d5aee0-5963-41db-a22b-cfea40a967a3");

    Card expected = Card.builder().name("Dusk // Dawn").id(id).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardById(id))
        .assertNext(card -> assertThat(card.id()).isEqualTo(id))
        .verifyComplete();
  }

  private static MockResponse getMockResponse(Object body) throws JsonProcessingException {
    return new MockResponse()
        .setResponseCode(200)
        .setBody(OBJECT_MAPPER.writeValueAsString(body))
        .addHeader("Content-Type", "application/json");
  }
}

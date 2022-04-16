package com.wpaul15.scryfall.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wpaul15.scryfall.api.model.Card;
import java.io.IOException;
import java.util.List;
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

  private static MockResponse getMockResponse(Object body) throws JsonProcessingException {
    return new MockResponse()
        .setResponseCode(200)
        .setBody(OBJECT_MAPPER.writeValueAsString(body))
        .addHeader("Content-Type", "application/json");
  }
}

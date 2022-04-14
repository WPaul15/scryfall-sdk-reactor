package com.wpaul15.scryfall.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wpaul15.scryfall.api.model.Card;
import java.io.IOException;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScryfallApiTest {

  private static final ObjectMapper OBJECT_MAPPER =
      JsonMapper.builder().addModule(new JavaTimeModule()).build();
  MockWebServer mockWebServer;
  ScryfallApi scryfallApi;

  @BeforeEach
  void beforeEach() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();

    scryfallApi = new ScryfallApi();
    scryfallApi.setBaseUrl("http://localhost:" + mockWebServer.getPort());
  }

  @AfterEach
  void afterEach() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  void shouldGetCardByMultiverseId() throws JsonProcessingException {
    int multiverseId = 409574;

    Card expected = Card.builder().multiverseIds(List.of(multiverseId)).build();

    mockWebServer.enqueue(getMockResponse(expected));

    StepVerifier.create(scryfallApi.getCardByMultiverseId(multiverseId))
        .expectNextMatches(card -> card.multiverseIds().contains(multiverseId))
        .verifyComplete();
  }

  private static MockResponse getMockResponse(Object body) throws JsonProcessingException {
    return new MockResponse()
        .setBody(OBJECT_MAPPER.writeValueAsString(body))
        .addHeader("Content-Type", "application/json");
  }
}

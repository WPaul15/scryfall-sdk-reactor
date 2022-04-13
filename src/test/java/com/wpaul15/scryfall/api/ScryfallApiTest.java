package com.wpaul15.scryfall.api;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScryfallApiTest {

  ScryfallApi scryfallApi;

  @BeforeEach
  void beforeEach() {
    scryfallApi = new ScryfallApi();
  }

  @Test
  void shouldGetCardByMultiverseId() {
    int multiverseId = 409574;

    StepVerifier.create(scryfallApi.getCardByMultiverseId(multiverseId))
        .expectNextMatches(card -> card.multiverseIds().contains(multiverseId))
        .verifyComplete();
  }
}

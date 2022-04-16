package com.wpaul15.scryfall.api;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wpaul15.scryfall.api.model.MtgSet;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class SetApiTest extends ScryfallApiTest {

  @Test
  void shouldGetSetByCode() throws JsonProcessingException {
    String setCode = "mmq";

    MtgSet expected = MtgSet.builder().name("Mercadian Masques").code(setCode).build();

    stubFor(
        get(urlPathEqualTo(String.format("/sets/%s", setCode))).willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getSetByCode(setCode))
        .assertNext(mtgSet -> assertThat(mtgSet.code()).isEqualTo(setCode))
        .verifyComplete();
  }
}

package com.wpaul15.scryfall.api;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wpaul15.scryfall.api.model.MtgSet;
import java.util.UUID;
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

  @Test
  void shouldGetSetByTcgplayerId() throws JsonProcessingException {
    int tcgplayerId = 1909;

    MtgSet expected =
        MtgSet.builder().name("Amonkhet Invocations").tcgplayerId(tcgplayerId).build();

    stubFor(
        get(urlPathEqualTo(String.format("/sets/tcgplayer/%d", tcgplayerId)))
            .willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getSetByTcgplayerId(tcgplayerId))
        .assertNext(mtgSet -> assertThat(mtgSet.tcgplayerId()).isEqualTo(tcgplayerId))
        .verifyComplete();
  }

  @Test
  void shouldGetSetById() throws JsonProcessingException {
    UUID id = UUID.fromString("2ec77b94-6d47-4891-a480-5d0b4e5c9372");

    MtgSet expected = MtgSet.builder().name("Ultimate Masters").id(id).build();

    stubFor(get(urlPathEqualTo(String.format("/sets/%s", id))).willReturn(mockResponse(expected)));

    StepVerifier.create(scryfallApi.getSetById(id))
        .assertNext(mtgSet -> assertThat(mtgSet.id()).isEqualTo(id))
        .verifyComplete();
  }
}

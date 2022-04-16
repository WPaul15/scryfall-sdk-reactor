package com.wpaul15.scryfall.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;

@WireMockTest
public class ScryfallApiTest {

  private static final ObjectMapper OBJECT_MAPPER =
      JsonMapper.builder().addModule(new JavaTimeModule()).build();

  protected ScryfallApi scryfallApi;

  @BeforeEach
  void beforeEach(WireMockRuntimeInfo wireMockRuntimeInfo) {
    scryfallApi = new ScryfallApi(wireMockRuntimeInfo.getHttpBaseUrl());
  }

  protected ResponseDefinitionBuilder mockResponse(Object body) throws JsonProcessingException {
    return aResponse()
        .withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody(OBJECT_MAPPER.writeValueAsString(body));
  }
}

package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.List;
import lombok.Builder;

@Builder
public record ListResponse<T>(
    String object,
    List<T> data,
    @JsonProperty("has_more") boolean hasMore,
    @JsonProperty("next_page") URI nextPage,
    @JsonProperty("total_cards") int totalCards,
    List<String> warnings) {}

package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.time.LocalDate;

public record Preview(
    @JsonProperty("previewed_at") LocalDate previewedAt,
    @JsonProperty("source_uri") URI sourceUri,
    String source) {}

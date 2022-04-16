package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record ImageUris(
    URI small,
    URI normal,
    URI large,
    URI png,
    @JsonProperty("art_crop") URI artCrop,
    @JsonProperty("border_crop") URI borderCrop) {}

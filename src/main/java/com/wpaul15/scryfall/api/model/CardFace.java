package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

public record CardFace(
    String artist,
    double cmc,
    @JsonProperty("color_indicator") List<Color> colorIndicator,
    List<Color> colors,
    @JsonProperty("flavor_text") String flavorText,
    @JsonProperty("illustration_id") UUID illustrationId,
    @JsonProperty("image_uris") ImageUris imageUris,
    Layout layout,
    String loyalty,
    @JsonProperty("mana_cost") String manaCost,
    String name,
    String object,
    @JsonProperty("oracle_id") UUID oracleId,
    @JsonProperty("oracle_text") String oracleText,
    String power,
    @JsonProperty("printed_name") String printedName,
    @JsonProperty("printed_text") String printedText,
    @JsonProperty("printed_type_line") String printedTypeLine,
    String toughness,
    @JsonProperty("type_line") String typeLine,
    String watermark) {}

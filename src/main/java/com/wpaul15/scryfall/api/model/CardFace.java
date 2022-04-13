package com.wpaul15.scryfall.api.model;

import java.util.List;
import java.util.UUID;

public record CardFace(
    String artist,
    double cmc,
    List<Color> colorIndicator,
    List<Color> colors,
    String flavorText,
    UUID illustrationId,
    ImageUris imageUris,
    Layout layout,
    String loyalty,
    String manaCost,
    String name,
    String object,
    UUID oracleId,
    String oracleText,
    String power,
    String printedName,
    String printedText,
    String printedTypeLine,
    String toughness,
    String typeLine,
    String watermark) {}

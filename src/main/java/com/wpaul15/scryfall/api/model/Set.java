package com.wpaul15.scryfall.api.model;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

public record Set(
    UUID id,
    String code,
    String mtgoCode,
    int tcgplayerId,
    String name,
    SetType setType,
    LocalDate releasedAt,
    String blockCode,
    String block,
    String parentSetCode,
    int cardCount,
    int printedSize,
    boolean digital,
    boolean foilOnly,
    boolean nonfoilOnly,
    URI scryfallUri,
    URI uri,
    URI iconSvgUri,
    URI searchUri) {}

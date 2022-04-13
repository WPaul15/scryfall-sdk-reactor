package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

public record Set(
    UUID id,
    String code,
    @JsonProperty("mtgo_code") String mtgoCode,
    @JsonProperty("tcgplayer_id") int tcgplayerId,
    String name,
    @JsonProperty("set_type") SetType setType,
    @JsonProperty("released_at") LocalDate releasedAt,
    @JsonProperty("block_code") String blockCode,
    String block,
    @JsonProperty("parent_set_code") String parentSetCode,
    @JsonProperty("card_count") int cardCount,
    @JsonProperty("printed_size") int printedSize,
    boolean digital,
    @JsonProperty("foil_only") boolean foilOnly,
    @JsonProperty("nonfoil_only") boolean nonfoilOnly,
    @JsonProperty("scryfall_uri") URI scryfallUri,
    URI uri,
    @JsonProperty("icon_svg_uri") URI iconSvgUri,
    @JsonProperty("search_uri") URI searchUri) {}

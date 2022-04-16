package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record RelatedUris(
    URI gatherer,
    @JsonProperty("tcgplayer_infinite_articles") URI tcgplayerInfiniteArticles,
    @JsonProperty("tcgplayer_infinite_decks") URI tcgplayerInfiniteDecks,
    URI edhrec,
    URI mtgtop8) {}

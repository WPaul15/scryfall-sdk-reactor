package com.wpaul15.scryfall.api.model;

import java.net.URI;

public record RelatedUris(
    URI gatherer,
    URI tcgplayerInfiniteArticles,
    URI tcgplayerInfiniteDecks,
    URI edhrec,
    URI mtgtop8) {}

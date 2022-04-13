package com.wpaul15.scryfall.api.model;

import java.net.URI;

public record ImageUris(URI small, URI normal, URI large, URI png, URI artCrop, URI borderCrop) {}

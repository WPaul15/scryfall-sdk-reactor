package com.wpaul15.scryfall.api.model;

import java.net.URI;
import java.time.LocalDate;

public record Preview(LocalDate previewedAt, URI sourceUri, String source) {}

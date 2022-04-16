package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Prices(
    String usd,
    @JsonProperty("usd_foil") String usdFoil,
    @JsonProperty("usd_etched") String usdEtched,
    String eur,
    @JsonProperty("eur_foil") String eurFoil,
    String tix) {}

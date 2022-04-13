package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Layout {
  NORMAL("normal"),
  SPLIT("split"),
  FLIP("flip"),
  TRANSFORM("transform"),
  MODAL_DFC("modal_dfc"),
  MELD("meld"),
  LEVELER("leveler"),
  CLASS("class"),
  SAGA("saga"),
  ADVENTURE("adventure"),
  PLANAR("planar"),
  SCHEME("scheme"),
  VANGUARD("vanguard"),
  TOKEN("token"),
  DOUBLE_FACED_TOKEN("double_faced_token"),
  EMBLEM("emblem"),
  AUGMENT("augment"),
  HOST("host"),
  ART_SERIES("art_series"),
  REVERSIBLE_CARD("reversible_card");

  String code;

  @JsonCreator
  private static Layout create(String jsonValue) {
    for (Layout layout : values()) {
      if (jsonValue.equals(layout.code)) {
        return layout;
      }
    }

    return null;
  }
}

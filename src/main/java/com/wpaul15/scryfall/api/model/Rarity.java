package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Rarity {
  COMMON("common"),
  UNCOMMON("uncommon"),
  RARE("rare"),
  MYTHIC("mythic"),
  SPECIAL("special"),
  BONUS("bonus");

  String displayName;

  @JsonCreator
  private static Rarity create(String jsonValue) {
    for (Rarity rarity : values()) {
      if (jsonValue.equals(rarity.displayName)) {
        return rarity;
      }
    }

    return null;
  }
}

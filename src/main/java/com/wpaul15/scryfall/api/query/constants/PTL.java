package com.wpaul15.scryfall.api.query.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PTL {
  /** A card's power value. */
  POWER("pow"),
  /** A card's toughness value. */
  TOUGHNESS("tou"),
  /** A card's loyalty value. */
  LOYALTY("loy");

  String displayName;

  @Override
  public String toString() {
    return displayName;
  }
}

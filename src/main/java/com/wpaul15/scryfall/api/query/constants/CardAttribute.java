package com.wpaul15.scryfall.api.query.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CardAttribute {
  /** The card's artwork. */
  ART("art"),
  /** The card's artist. */
  ARTIST("artist"),
  /** The card's flavor text. */
  FLAVOR_TEXT("flavor"),
  /** The card's frame. */
  FRAME("frame"),
  /** The card's language. */
  LANGUAGE("language"),
  /** The card's rarity. */
  RARITY("rarity");

  String displayName;

  @Override
  public String toString() {
    return displayName;
  }
}

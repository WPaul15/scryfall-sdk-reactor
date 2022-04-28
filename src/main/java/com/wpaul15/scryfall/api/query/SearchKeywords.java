package com.wpaul15.scryfall.api.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SearchKeywords {
  /** An alias for all creatures with a CMC of 2, a power of 2, and a toughness of 2. */
  BEAR("bear"),
  /** An alias for all creatures whose rules text contains only keyword abilities. */
  FRENCH_VANILLA("frenchvanilla"),
  /** An alias for all "Un-" cards, holiday cards, and other funny cards. */
  FUNNY("funny"),
  /**
   * An alias for all permanents (i.e. artifacts, creatures, enchantments, lands, and
   * planeswalkers).
   */
  PERMANENT("permanent"),
  /** An alias for all cards that may be cast as a spell. */
  SPELL("spell"),
  /** An alias for creatures with no abilities (i.e. no rules text). */
  VANILLA("vanilla");

  String displayName;

  @Override
  public String toString() {
    return displayName;
  }
}

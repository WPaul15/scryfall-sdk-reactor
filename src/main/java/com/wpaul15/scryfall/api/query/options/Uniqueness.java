package com.wpaul15.scryfall.api.query.options;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Uniqueness {
  /** Return only a single instance each card. */
  CARDS("cards"),
  /** Return each unique printing. */
  PRINTS("prints"),
  /** Return each printing with a unique artwork. */
  ART("art");

  String filterText;

  @Override
  public String toString() {
    return filterText;
  }
}

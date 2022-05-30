package com.wpaul15.scryfall.api.query.options;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Printing {
  /** Prefer the oldest printing. */
  OLDEST("oldest"),
  /** Prefer the newest printing. */
  NEWEST("newest"),
  /** Prefer the printing with the lowest price in USD. */
  USD_LOW("usd-low"),
  /** Prefer the printing with the highest price in USD. */
  USD_HIGH("usd-high"),
  /** Prefer the printing with the lowest price in EUR. */
  EUR_LOW("eur-low"),
  /** Prefer the printing with the highest price in EUR. */
  EUR_HIGH("eur-high"),
  /** Prefer the printing with the lowest price in tix. */
  TIX_LOW("tix-low"),
  /** Prefer the printing with the highest price in tix. */
  TIX_HIGH("tix-high"),
  /** Prefer promotional printings. */
  PROMO("promo");

  String filterText;

  @Override
  public String toString() {
    return filterText;
  }
}

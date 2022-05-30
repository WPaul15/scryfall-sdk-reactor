package com.wpaul15.scryfall.api.query.options;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SortField {
  /** Sort the results by artist name(s). */
  ARTIST("artist"),
  /** Sort the results by converted mana cost. */
  CMC("cmc"),
  /** Sort the results by power. */
  POWER("power"),
  /** Sort the results by toughness. */
  TOUGHNESS("toughness"),
  /** Sort the results by set. */
  SET("set"),
  /** Sort the results by card name. */
  NAME("name"),
  /** Sort the results by price in USD. */
  PRICE_USD("usd"),
  /** Sort the results by price in EUR. */
  PRICE_EUR("eur"),
  /** Sort the results by price in tix. */
  PRICE_TIX("tix"),
  /** Sort the results by rarity. */
  RARITY("rarity"),
  /** Sort the results by color. */
  COLOR("color"),
  /** Sort the results by release date. */
  RELEASE_DATE("released"),
  /** Sort the results by whether the printing is a spoiler. */
  SPOILED("spoiled"),
  /** Sort the results by EDHREC rating. */
  EDHREC("edhrec"),
  /**
   * Sort the results by legality in the Magic Online Penny Dreadful format (i.e. cards that cost
   * 0.02 tix or less).
   */
  PENNY("penny"),
  /** Sort the results by review. */
  REVIEW("review");

  String filterText;

  @Override
  public String toString() {
    return filterText;
  }
}

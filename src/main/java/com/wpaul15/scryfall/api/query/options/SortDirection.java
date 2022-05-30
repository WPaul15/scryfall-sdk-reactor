package com.wpaul15.scryfall.api.query.options;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SortDirection {
  /** Order the results from least to greatest. */
  ASCENDING("asc"),
  /** Order the results from greatest to least. */
  DESCENDING("desc");

  String filterText;

  @Override
  public String toString() {
    return filterText;
  }
}

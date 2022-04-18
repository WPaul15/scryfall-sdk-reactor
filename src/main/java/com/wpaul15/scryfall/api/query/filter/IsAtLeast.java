package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

class IsAtLeast<T> extends ComparingFilter<T> {

  IsAtLeast(Iterable<T> entries) {
    super(entries, ">=");
  }

  @SafeVarargs
  static <T> IsAtLeast<T> isAtLeast(T... entries) {
    return isAtLeast(Arrays.asList(entries));
  }

  static <T> IsAtLeast<T> isAtLeast(Iterable<T> entries) {
    return new IsAtLeast<>(entries);
  }
}

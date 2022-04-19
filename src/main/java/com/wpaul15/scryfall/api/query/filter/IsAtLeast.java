package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class IsAtLeast<T> extends ComparingFilter<T> {

  IsAtLeast(Iterable<T> values) {
    super(values, ">=");
  }

  @SafeVarargs
  static <T> IsAtLeast<T> isAtLeast(T... values) {
    return isAtLeast(Arrays.asList(values));
  }

  static <T> IsAtLeast<T> isAtLeast(Iterable<T> values) {
    return new IsAtLeast<>(values);
  }
}

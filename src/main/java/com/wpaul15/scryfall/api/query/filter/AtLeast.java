package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class AtLeast<T> extends ComparingFilter<T> {

  AtLeast(Iterable<T> values) {
    super(values, ">=");
  }

  @SafeVarargs
  static <T> AtLeast<T> atLeast(T... values) {
    return atLeast(Arrays.asList(values));
  }

  static <T> AtLeast<T> atLeast(Iterable<T> values) {
    return new AtLeast<>(values);
  }
}

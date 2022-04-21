package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class LessThan<T> extends ComparingFilter<T> {

  LessThan(Iterable<T> values) {
    super(values, "<");
  }

  @SafeVarargs
  static <T> LessThan<T> lessThan(T... values) {
    return lessThan(Arrays.asList(values));
  }

  static <T> LessThan<T> lessThan(Iterable<T> values) {
    return new LessThan<>(values);
  }
}

package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class MoreThan<T> extends ComparingFilter<T> {

  MoreThan(Iterable<T> values) {
    super(values, ">");
  }

  @SafeVarargs
  static <T> MoreThan<T> moreThan(T... values) {
    return moreThan(Arrays.asList(values));
  }

  static <T> MoreThan<T> moreThan(Iterable<T> values) {
    return new MoreThan<>(values);
  }
}

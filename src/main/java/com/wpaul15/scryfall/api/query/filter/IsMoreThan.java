package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

public class IsMoreThan<T> extends ComparingFilter<T> {

  IsMoreThan(Iterable<T> values) {
    super(values, ">");
  }

  @SafeVarargs
  static <T> IsMoreThan<T> isMoreThan(T... values) {
    return isMoreThan(Arrays.asList(values));
  }

  static <T> IsMoreThan<T> isMoreThan(Iterable<T> values) {
    return new IsMoreThan<>(values);
  }
}

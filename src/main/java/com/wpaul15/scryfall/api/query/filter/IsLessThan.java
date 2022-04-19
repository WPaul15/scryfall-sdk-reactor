package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

public class IsLessThan<T> extends ComparingFilter<T> {

  IsLessThan(Iterable<T> values) {
    super(values, "<");
  }

  @SafeVarargs
  static <T> IsLessThan<T> isLessThan(T... values) {
    return isLessThan(Arrays.asList(values));
  }

  static <T> IsLessThan<T> isLessThan(Iterable<T> values) {
    return new IsLessThan<>(values);
  }
}

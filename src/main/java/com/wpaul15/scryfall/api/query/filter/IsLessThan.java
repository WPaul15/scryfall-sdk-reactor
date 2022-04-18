package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

public class IsLessThan<T> extends ComparingFilter<T> {

  IsLessThan(Iterable<T> entries) {
    super(entries, "<");
  }

  @SafeVarargs
  static <T> IsLessThan<T> isLessThan(T... entries) {
    return isLessThan(Arrays.asList(entries));
  }

  static <T> IsLessThan<T> isLessThan(Iterable<T> entries) {
    return new IsLessThan<>(entries);
  }
}

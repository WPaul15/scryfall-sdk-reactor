package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

class IsAtMost<T> extends ComparingFilter<T> {

  IsAtMost(Iterable<T> values) {
    super(values, "<=");
  }

  @SafeVarargs
  static <T> IsAtMost<T> isAtMost(T... values) {
    return isAtMost(Arrays.asList(values));
  }

  static <T> IsAtMost<T> isAtMost(Iterable<T> values) {
    return new IsAtMost<>(values);
  }
}

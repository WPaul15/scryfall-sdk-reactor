package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class IsNotExactly<T> extends ComparingFilter<T> {

  IsNotExactly(Iterable<T> values) {
    super(values, "!=");
  }

  @SafeVarargs
  static <T> IsNotExactly<T> isNotExactly(T... values) {
    return isNotExactly(Arrays.asList(values));
  }

  static <T> IsNotExactly<T> isNotExactly(Iterable<T> values) {
    return new IsNotExactly<>(values);
  }
}

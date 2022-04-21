package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class AtMost<T> extends ComparingFilter<T> {

  AtMost(Iterable<T> values) {
    super(values, "<=");
  }

  @SafeVarargs
  static <T> AtMost<T> atMost(T... values) {
    return atMost(Arrays.asList(values));
  }

  static <T> AtMost<T> atMost(Iterable<T> values) {
    return new AtMost<>(values);
  }
}

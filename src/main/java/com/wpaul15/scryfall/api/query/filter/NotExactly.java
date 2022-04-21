package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class NotExactly<T> extends ComparingFilter<T> {

  NotExactly(Iterable<T> values) {
    super(values, "!=");
  }

  @SafeVarargs
  static <T> NotExactly<T> notExactly(T... values) {
    return notExactly(Arrays.asList(values));
  }

  static <T> NotExactly<T> notExactly(Iterable<T> values) {
    return new NotExactly<>(values);
  }
}

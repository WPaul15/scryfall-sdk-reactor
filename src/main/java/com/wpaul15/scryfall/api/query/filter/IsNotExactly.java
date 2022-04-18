package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

class IsNotExactly<T> extends AbstractFilter<T> {

  IsNotExactly(Iterable<T> entries) {
    super(entries);
  }

  @SafeVarargs
  static <T> IsNotExactly<T> isNotExactly(T... entries) {
    return isNotExactly(Arrays.asList(entries));
  }

  static <T> IsNotExactly<T> isNotExactly(Iterable<T> entries) {
    return new IsNotExactly<>(entries);
  }

  public String toQueryParams() {
    return super.toQueryParams("!=");
  }
}

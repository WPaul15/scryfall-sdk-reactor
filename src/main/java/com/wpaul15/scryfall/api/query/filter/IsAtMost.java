package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

class IsAtMost<T> extends AbstractFilter<T> {

  IsAtMost(Iterable<T> entries) {
    super(entries);
  }

  @SafeVarargs
  static <T> IsAtMost<T> isAtMost(T... entries) {
    return isAtMost(Arrays.asList(entries));
  }

  static <T> IsAtMost<T> isAtMost(Iterable<T> entries) {
    return new IsAtMost<>(entries);
  }

  public String toQueryParams() {
    return super.toQueryParams("<=");
  }
}

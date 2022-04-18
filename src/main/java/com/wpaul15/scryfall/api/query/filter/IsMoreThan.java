package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

public class IsMoreThan<T> extends AbstractFilter<T> {

  IsMoreThan(Iterable<T> entries) {
    super(entries);
  }

  @SafeVarargs
  static <T> IsMoreThan<T> isMoreThan(T... entries) {
    return isMoreThan(Arrays.asList(entries));
  }

  static <T> IsMoreThan<T> isMoreThan(Iterable<T> entries) {
    return new IsMoreThan<>(entries);
  }

  public String toQueryParams() {
    return super.toQueryParams(">");
  }
}

package com.wpaul15.scryfall.api.query.expression;

import java.util.Arrays;

class IsAtLeast<T> extends AbstractExpression<T> {

  IsAtLeast(Iterable<T> entries) {
    super(entries);
  }

  @SafeVarargs
  static <T> IsAtLeast<T> isAtLeast(T... entries) {
    return isAtLeast(Arrays.asList(entries));
  }

  static <T> IsAtLeast<T> isAtLeast(Iterable<T> entries) {
    return new IsAtLeast<>(entries);
  }

  @Override
  public String toQueryParams() {
    StringBuilder builder = new StringBuilder(">=");

    entries.forEach(entry -> builder.append(entry.toString()));

    return builder.toString();
  }
}

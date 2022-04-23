package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;

final class Exactly<T> extends ComparingFilter<T> {

  private static final String OPERATOR = "=";

  Exactly(Iterable<T> values) {
    super(values, OPERATOR);
  }

  @SafeVarargs
  static <T> Exactly<T> exactly(T... values) {
    return exactly(Arrays.asList(values));
  }

  static <T> Exactly<T> exactly(Iterable<T> values) {
    return new Exactly<>(values);
  }

  static final class ExactlyMono<T> extends ComparingMonoFilter<T> {

    ExactlyMono(T value) {
      super(value, OPERATOR);
    }

    static <T> ExactlyMono<T> exactly(T value) {
      return new ExactlyMono<>(value);
    }
  }
}

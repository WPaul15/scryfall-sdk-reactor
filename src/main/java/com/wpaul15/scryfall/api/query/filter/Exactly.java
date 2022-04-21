package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class Exactly<T> extends ComparingFilter<T> {

  Exactly(Iterable<T> values) {
    super(values, "=");
  }

  @SafeVarargs
  static <T> Exactly<T> exactly(T... values) {
    return exactly(Arrays.asList(values));
  }

  static <T> Exactly<T> exactly(Iterable<T> values) {
    return new Exactly<>(values);
  }
}

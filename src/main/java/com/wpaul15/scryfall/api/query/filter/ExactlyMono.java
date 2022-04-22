package com.wpaul15.scryfall.api.query.filter;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class ExactlyMono<T> extends ComparingMonoFilter<T> {

  ExactlyMono(T value) {
    super(value, "=");
  }

  static <T> ExactlyMono<T> exactly(T value) {
    return new ExactlyMono<>(value);
  }
}

package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class IsExactly<T> extends ComparingFilter<T> {

  IsExactly(Iterable<T> values) {
    super(values, "=");
  }

  @SafeVarargs
  static <T> IsExactly<T> isExactly(T... values) {
    return isExactly(Arrays.asList(values));
  }

  static <T> IsExactly<T> isExactly(Iterable<T> values) {
    return new IsExactly<>(values);
  }
}

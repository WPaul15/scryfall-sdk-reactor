package com.wpaul15.scryfall.api.query.filter;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class IsExactly<T> extends AbstractFilter<T> {

  IsExactly(Iterable<T> entries) {
    super(entries);
  }

  @SafeVarargs
  static <T> IsExactly<T> isExactly(T... entries) {
    return isExactly(Arrays.asList(entries));
  }

  static <T> IsExactly<T> isExactly(Iterable<T> entries) {
    return new IsExactly<>(entries);
  }

  public String toQueryParams() {
    return super.toQueryParams("=");
  }
}

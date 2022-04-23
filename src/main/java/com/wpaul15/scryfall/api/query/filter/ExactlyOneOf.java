package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class ExactlyOneOf<T> extends MultiFilter<T> {

  ExactlyOneOf(Iterable<IFilter<T>> values) {
    super(values);
  }

  @SafeVarargs
  static <T> ExactlyOneOf<T> exactlyOneOf(T... values) {
    return exactlyOneOf(Arrays.asList(values));
  }

  static <T> ExactlyOneOf<T> exactlyOneOf(Iterable<T> values) {
    return new ExactlyOneOf<>(
        StreamSupport.stream(values.spliterator(), false)
            .distinct()
            .map(Exactly::exactly)
            .collect(Collectors.toList()));
  }
}

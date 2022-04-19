package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class IsExactlyOneOf<T> extends CombiningFilter<T> {

  IsExactlyOneOf(Iterable<IFilter<T>> values) {
    super(values);
  }

  @SafeVarargs
  static <T> IsExactlyOneOf<T> isExactlyOneOf(T... values) {
    return isExactlyOneOf(Arrays.asList(values));
  }

  static <T> IsExactlyOneOf<T> isExactlyOneOf(Iterable<T> values) {
    return new IsExactlyOneOf<>(
        StreamSupport.stream(values.spliterator(), false)
            .map(IsExactly::isExactly)
            .collect(Collectors.toList()));
  }
}

package com.wpaul15.scryfall.api.query;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MultiFilter<T> extends Filter<T> {

  Iterable<T> values;
  Function<T, Filter<T>> filterType;

  @Override
  public String toQueryParams(String key) {
    return "("
        + StreamSupport.stream(values.spliterator(), false)
            .distinct()
            .map(filterType)
            .map(filter -> filter.toQueryParams(key))
            .collect(Collectors.joining(" or "))
        + ")";
  }
}

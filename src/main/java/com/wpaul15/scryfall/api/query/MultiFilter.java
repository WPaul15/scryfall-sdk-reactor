package com.wpaul15.scryfall.api.query;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MultiFilter<T> extends Filter<T> {

  Collection<T> values;
  Function<T, Filter<T>> filterType;

  @Override
  public String toQueryParams(String key) {
    return "("
        + values.stream()
            .distinct()
            .map(filterType)
            .map(filter -> filter.toQueryParams(key))
            .collect(Collectors.joining(" or "))
        + ")";
  }
}

package com.wpaul15.scryfall.api.query;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MultiFilter<T> extends Filter<T> {

  Function<T, Filter<T>> filterFunction;
  boolean disjoining;

  protected MultiFilter(
      Collection<T> values, Function<T, Filter<T>> filterFunction, boolean disjoining) {
    super(values, Operator.NONE);
    this.filterFunction = filterFunction;
    this.disjoining = disjoining;
  }

  @Override
  protected String toFilterString(String key) {
    return (negated ? "-" : "")
        + "("
        + values.stream()
            .distinct()
            .map(filterFunction)
            .map(filter -> filter.toFilterString(key))
            .collect(Collectors.joining(disjoining ? " or " : " "))
        + ")";
  }
}

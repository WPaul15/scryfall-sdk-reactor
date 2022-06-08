package com.wpaul15.scryfall.api.query;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

class MultiFilter<T> extends Filter<Collection<Filter<T>>> {

  boolean disjoining;

  protected MultiFilter(
      Collection<T> values, Function<T, Filter<T>> filterType, boolean disjoining) {
    super(values.stream().map(filterType).toList(), Operator.NONE);
    this.disjoining = disjoining;
  }

  protected MultiFilter(MultiFilter<T> filter) {
    super(filter);
    this.disjoining = filter.disjoining;
  }

  @Override
  protected String toFilterString(String key) {
    return (negated ? "-" : "")
        + "("
        + value.stream()
            .distinct()
            .map(filter -> filter.toFilterString(key))
            .collect(Collectors.joining(disjoining ? " or " : " "))
        + ")";
  }
}

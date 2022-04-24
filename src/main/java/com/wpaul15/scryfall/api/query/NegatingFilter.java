package com.wpaul15.scryfall.api.query;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NegatingFilter<T> extends Filter<T> {

  Filter<T> filter;
  boolean negated;

  NegatingFilter(Filter<T> filter) {
    this.filter = filter;
    this.negated =
        !(filter instanceof NegatingFilter<T> negatingFilter) || !negatingFilter.isNegated();
  }

  protected boolean isNegated() {
    return negated;
  }

  @Override
  protected String toQueryParams(String key) {
    return filter.toQueryParams(key);
  }
}

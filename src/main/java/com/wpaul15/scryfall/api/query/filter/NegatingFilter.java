package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import com.wpaul15.scryfall.api.query.INegatingFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class NegatingFilter<T> implements INegatingFilter<T> {

  IFilter<T> filter;
  boolean negated;

  NegatingFilter(IFilter<T> filter) {
    this.filter = filter;
    this.negated =
        !(filter instanceof INegatingFilter<T> negatingFilter) || !negatingFilter.isNegated();
  }

  @Override
  public boolean isNegated() {
    return negated;
  }

  @Override
  public String toQueryParams(String key) {
    return filter.toQueryParams(key);
  }
}

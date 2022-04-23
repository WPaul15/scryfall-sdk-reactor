package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IMultiFilter;
import com.wpaul15.scryfall.api.query.INegatingMultiFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class NegatingMultiFilter<T> implements INegatingMultiFilter<T> {

  IMultiFilter<T> filter;
  boolean negated;

  NegatingMultiFilter(IMultiFilter<T> filter) {
    this.filter = filter;
    this.negated =
        !(filter instanceof INegatingMultiFilter<T> negatingFilter) || !negatingFilter.isNegated();
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

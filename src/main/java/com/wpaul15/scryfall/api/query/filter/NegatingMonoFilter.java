package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IMonoFilter;
import com.wpaul15.scryfall.api.query.INegatingMonoFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class NegatingMonoFilter<T> implements INegatingMonoFilter<T> {

  IMonoFilter<T> filter;
  boolean negated;

  NegatingMonoFilter(IMonoFilter<T> filter) {
    this.filter = filter;
    this.negated =
        !(filter instanceof INegatingMonoFilter<T> negatingFilter) || !negatingFilter.isNegated();
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

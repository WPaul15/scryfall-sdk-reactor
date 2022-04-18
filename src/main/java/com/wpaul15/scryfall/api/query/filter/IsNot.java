package com.wpaul15.scryfall.api.query.filter;

import static com.wpaul15.scryfall.api.query.filter.IsExactly.isExactly;

import com.wpaul15.scryfall.api.query.IFilter;

class IsNot<T> extends NegatingFilter<T> {

  IsNot(IFilter<T> filter) {
    super(filter);
  }

  static <T> IsNot<T> not(IFilter<T> filter) {
    return new IsNot<>(filter);
  }

  static <T> IsNot<T> not(T value) {
    return new IsNot<>(isExactly(value));
  }

  @Override
  public String toQueryParams() {
    return super.toQueryParams("");
  }
}

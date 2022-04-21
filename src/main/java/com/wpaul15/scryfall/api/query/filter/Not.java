package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;

final class Not<T> extends NegatingFilter<T> {

  Not(IFilter<T> filter) {
    super(filter);
  }

  static <T> Not<T> not(IFilter<T> filter) {
    return new Not<>(filter);
  }

  static <T> Not<T> not(T value) {
    return new Not<>(Exactly.exactly(value));
  }
}

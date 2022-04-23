package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IMultiFilter;

final class NotMulti<T> extends NegatingMultiFilter<T> {

  NotMulti(IMultiFilter<T> filter) {
    super(filter);
  }

  static <T> NotMulti<T> not(IMultiFilter<T> filter) {
    return new NotMulti<>(filter);
  }
}

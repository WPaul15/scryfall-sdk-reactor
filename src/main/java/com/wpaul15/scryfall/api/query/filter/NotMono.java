package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IMonoFilter;

final class NotMono<T> extends NegatingMonoFilter<T> {

  NotMono(IMonoFilter<T> filter) {
    super(filter);
  }

  static <T> NotMono<T> not(IMonoFilter<T> filter) {
    return new NotMono<>(filter);
  }
}

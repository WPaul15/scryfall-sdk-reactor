package com.wpaul15.scryfall.api.query;

class NegatingMonoFilter<T> extends NegatingFilter<T> {

  public NegatingMonoFilter(MonoFilter<T> filter) {
    super(filter);
  }
}

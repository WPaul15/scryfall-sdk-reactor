package com.wpaul15.scryfall.api.query;

class NegatingMultiFilter<T> extends NegatingFilter<T> {

  public NegatingMultiFilter(MultiFilter<T> filter) {
    super(filter);
  }
}

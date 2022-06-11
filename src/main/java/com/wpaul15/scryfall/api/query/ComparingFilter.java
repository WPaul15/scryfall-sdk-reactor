package com.wpaul15.scryfall.api.query;

import java.util.Collection;

class ComparingFilter<T> extends Filter<T> {

  protected ComparingFilter(Collection<T> values, Operator operator) {
    super(values, operator);
  }
}

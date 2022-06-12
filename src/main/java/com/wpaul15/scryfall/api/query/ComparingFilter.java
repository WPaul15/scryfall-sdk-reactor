package com.wpaul15.scryfall.api.query;

import java.util.List;

class ComparingFilter<T> extends Filter<T> {

  protected ComparingFilter(List<T> values, Operator operator) {
    super(values, operator);
  }
}

package com.wpaul15.scryfall.api.query;

import java.util.List;

class SingleFilter<T> extends Filter<T> {

  protected SingleFilter(T value) {
    super(List.of(value), Operator.COLON);
  }
}

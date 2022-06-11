package com.wpaul15.scryfall.api.query;

import java.util.List;

class ComparingSingleFilter<T> extends ComparingFilter<T> {

  ComparingSingleFilter(T value, Operator operator) {
    super(List.of(value), operator);
  }
}

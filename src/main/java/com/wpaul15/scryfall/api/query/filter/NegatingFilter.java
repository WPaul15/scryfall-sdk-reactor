package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import com.wpaul15.scryfall.api.query.INegatingFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
abstract class NegatingFilter<T> implements INegatingFilter<T> {

  IFilter<T> filter;

  @Override
  public String toQueryParams(String operator) {
    return filter.toQueryParams();
  }
}
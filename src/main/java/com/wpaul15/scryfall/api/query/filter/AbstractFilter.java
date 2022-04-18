package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
abstract class AbstractFilter<T> implements IFilter<T> {

  Iterable<T> entries;

  @Override
  public String toQueryParams(String operator) {
    StringBuilder builder = new StringBuilder(operator);

    entries.forEach(entry -> builder.append(entry.toString()));

    return builder.toString();
  }
}

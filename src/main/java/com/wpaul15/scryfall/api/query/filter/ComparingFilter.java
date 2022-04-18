package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IComparingFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class ComparingFilter<T> implements IComparingFilter<T> {

  Iterable<T> entries;
  String operator;

  @Override
  public String toQueryParams() {
    StringBuilder builder = new StringBuilder(operator);

    entries.forEach(entry -> builder.append(entry.toString()));

    return builder.toString();
  }
}

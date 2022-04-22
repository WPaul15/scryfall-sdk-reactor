package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IMonoFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class ComparingMonoFilter<T> implements IMonoFilter<T> {

  T value;
  String operator;

  @Override
  public String toQueryParams(String key) {
    StringBuilder builder = new StringBuilder(key).append(operator);

    if (value instanceof String) {
      builder.append("\"").append(value).append("\"");
    } else {
      builder.append(value.toString());
    }

    return builder.toString();
  }
}

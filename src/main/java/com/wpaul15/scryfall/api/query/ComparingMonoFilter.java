package com.wpaul15.scryfall.api.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ComparingMonoFilter<T> extends MonoFilter<T> {

  T value;
  String operator;

  @Override
  protected String toQueryParams(String key) {
    StringBuilder builder = new StringBuilder(key).append(operator);

    if (value instanceof String) {
      builder.append("\"").append(value).append("\"");
    } else {
      builder.append(value.toString());
    }

    return builder.toString();
  }
}

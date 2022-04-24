package com.wpaul15.scryfall.api.query;

import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ComparingFilter<T> extends Filter<T> {

  Iterable<T> values;
  String operator;

  @Override
  protected String toQueryParams(String key) {
    StringBuilder builder = new StringBuilder(key).append(operator);

    StreamSupport.stream(values.spliterator(), false)
        .distinct()
        .forEach(
            value -> {
              if (value instanceof String) {
                builder.append("\"").append(value).append("\"");
              } else {
                builder.append(value.toString());
              }
            });

    return builder.toString();
  }
}

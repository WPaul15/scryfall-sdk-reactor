package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IComparingFilter;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class ComparingFilter<T> implements IComparingFilter<T> {

  Iterable<T> values;
  String operator;

  @Override
  public String toQueryParams(String key) {
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

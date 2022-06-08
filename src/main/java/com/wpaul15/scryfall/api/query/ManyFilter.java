package com.wpaul15.scryfall.api.query;

import java.util.Collection;

class ManyFilter<T> extends Filter<Collection<T>> {

  protected ManyFilter(Collection<T> values, Operator operator) {
    super(values, operator);
  }

  protected ManyFilter(ManyFilter<T> filter) {
    super(filter);
  }

  @Override
  protected String toFilterString(String key) {
    StringBuilder builder = new StringBuilder(negated ? "-" : "").append(key).append(operator);

    value.stream()
        .distinct()
        .forEach(
            value -> {
              if (value instanceof String s && WHITESPACE.matcher(s).matches()) {
                builder.append('"').append(value).append('"');
              } else {
                builder.append(value.toString());
              }
            });

    return builder.toString();
  }
}

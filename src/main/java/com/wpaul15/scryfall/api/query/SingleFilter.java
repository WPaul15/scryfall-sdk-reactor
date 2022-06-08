package com.wpaul15.scryfall.api.query;

class SingleFilter<T> extends Filter<T> {

  protected SingleFilter(T value, Operator operator) {
    super(value, operator);
  }

  protected SingleFilter(SingleFilter<T> filter) {
    super(filter);
  }

  @Override
  protected String toFilterString(String key) {
    StringBuilder builder = new StringBuilder(negated ? "-" : "").append(key).append(operator);

    if (value instanceof String stringValue && WHITESPACE.matcher(stringValue).matches()) {
      builder.append('"').append(value).append('"');
    } else {
      builder.append(value);
    }

    return builder.toString();
  }
}

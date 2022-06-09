package com.wpaul15.scryfall.api.query;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
abstract class Filter<T> {

  T value;
  Operator operator;
  boolean negated;

  protected Filter(T value, Operator operator) {
    this.value = value;
    this.operator = operator;
    this.negated = false;
  }

  protected Filter(Filter<T> filter) {
    this.value = filter.value;
    this.operator = filter.operator;
    this.negated = !filter.negated;
  }

  protected static final Pattern WHITESPACE = Pattern.compile(".*?\\s.*?");

  protected abstract String toFilterString(String key);
}

package com.wpaul15.scryfall.api.query;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
abstract class Filter<T> {

  protected static final Pattern WHITESPACE = Pattern.compile(".*?\\s.*?");

  final T value;
  final Operator operator;
  boolean negated;

  protected Filter(T value, Operator operator) {
    this.value = value;
    this.operator = operator;
    this.negated = false;
  }

  protected abstract String toFilterString(String key);

  protected void negate() {
    negated = !negated;
  }
}

package com.wpaul15.scryfall.api.query;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
class Filter<T> {

  protected static final Pattern WHITESPACE = Pattern.compile(".*?\\s.*?");

  final List<T> values;
  final Operator operator;
  boolean negated;

  protected Filter(List<T> values, Operator operator) {
    this.values = values;
    this.operator = operator;
    this.negated = false;
  }

  protected void negate() {
    negated = !negated;
  }

  protected boolean containsInvalidValue(Predicate<T> invalidCondition) {
    return values.stream().anyMatch(invalidCondition);
  }

  protected boolean containsInvalidCombination(Predicate<List<T>> invalidCondition) {
    return invalidCondition.test(values);
  }

  protected String toFilterString(String key) {
    List<T> nonEmpty =
        values.stream()
            .filter(Objects::nonNull)
            .filter(
                value -> {
                  if (value instanceof String s && !s.isBlank()) {
                    return true;
                  } else {
                    return !(value instanceof String);
                  }
                })
            .toList();

    if (nonEmpty.isEmpty()) {
      return "";
    }

    StringBuilder builder = new StringBuilder(negated ? "-" : "").append(key).append(operator);

    nonEmpty.stream()
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

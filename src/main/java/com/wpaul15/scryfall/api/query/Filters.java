package com.wpaul15.scryfall.api.query;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Filters {

  /**
   * Creates a filter that matches if and only if the card property is exactly the given value.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> SingleFilter<T> equalTo(T value) {
    Operator operator = value instanceof String ? Operator.COLON : Operator.EQUALS;

    return new SingleFilter<>(value, operator);
  }

  /**
   * Creates a filter that matches if and only if the card property is exactly the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ManyFilter<T> equalTo(T... values) {
    return new ManyFilter<>(Arrays.asList(values), Operator.EQUALS);
  }

  /**
   * Creates a filter that matches if the card property is exactly one or more of the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> MultiFilter<T> anyOf(T... values) {
    return new MultiFilter<>(Arrays.asList(values), Filters::equalTo);
  }

  /**
   * Creates a filter that negates the given filter.
   *
   * @param filter the filter to negate
   * @return a negated filter
   * @param <T> the type of the value in {@code filter}
   */
  public static <T> SingleFilter<T> not(SingleFilter<T> filter) {
    return new SingleFilter<>(filter);
  }

  /**
   * Creates a filter that negates the given filter.
   *
   * @param filter the filter to negate
   * @return a negated filter
   * @param <T> the type of the values in {@code filter}
   */
  public static <T> ManyFilter<T> not(ManyFilter<T> filter) {
    return new ManyFilter<>(filter);
  }

  /**
   * Creates a filter that negates the given filter.
   *
   * @param filter the filter to negate
   * @return a negated filter
   * @param <T> the type of the values in {@code filter}
   */
  public static <T> MultiFilter<T> not(MultiFilter<T> filter) {
    return new MultiFilter<>(filter);
  }

  /**
   * Creates a filter that matches if the card property is none of the given values.
   *
   * <p>This is a shorthand convenience method for {@code not(anyOf())}.
   *
   * @param values the values not to match
   * @return a negated filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> MultiFilter<T> noneOf(T... values) {
    return not(anyOf(values));
  }
}

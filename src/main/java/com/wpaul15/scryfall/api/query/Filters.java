package com.wpaul15.scryfall.api.query;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Filters {

  /**
   * Creates a filter that matches if and only if the card property is exactly the given value.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> ComparingSingleFilter<T> equalTo(T value) {
    return new ComparingSingleFilter<>(value, Operator.EQUALS);
  }

  /**
   * Creates a filter that matches if and only if the card property is exactly the combination of
   * the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ComparingFilter<T> equalTo(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), Operator.EQUALS);
  }

  /**
   * Creates a filter that matches if and only if the card property is less than the given value.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> ComparingSingleFilter<T> lessThan(T value) {
    return new ComparingSingleFilter<>(value, Operator.LESS_THAN);
  }

  /**
   * Creates a filter that matches if and only if the card property is less than the combination of
   * the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ComparingFilter<T> lessThan(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), Operator.LESS_THAN);
  }

  /**
   * Creates a filter that matches if and only if the card property is greater than the given
   * values.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> ComparingSingleFilter<T> greaterThan(T value) {
    return new ComparingSingleFilter<>(value, Operator.GREATER_THAN);
  }

  /**
   * Creates a filter that matches if and only if the card property is greater than the combination
   * of the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ComparingFilter<T> greaterThan(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), Operator.GREATER_THAN);
  }

  /**
   * Creates a filter that matches if and only if the card property is less than or equal to the
   * given value.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> ComparingSingleFilter<T> lessThanOrEqualTo(T value) {
    return new ComparingSingleFilter<>(value, Operator.LESS_THAN_OR_EQUAL);
  }

  /**
   * Creates a filter that matches if and only if the card property is less than or equal to the
   * combination of the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ComparingFilter<T> lessThanOrEqualTo(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), Operator.LESS_THAN_OR_EQUAL);
  }

  /**
   * Creates a filter that matches if and only if the card property is greater than or equal to the
   * given value.
   *
   * @param value the value to match
   * @return a filter
   * @param <T> the type of the value
   */
  public static <T> ComparingSingleFilter<T> greaterThanOrEqualTo(T value) {
    return new ComparingSingleFilter<>(value, Operator.GREATER_THAN_OR_EQUAL);
  }

  /**
   * Creates a filter that matches if and only if the card property is greater than or equal to the
   * combination of the given values.
   *
   * @param values the values to match
   * @return a filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> ComparingFilter<T> greaterThanOrEqualTo(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), Operator.GREATER_THAN_OR_EQUAL);
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
    return new MultiFilter<>(Arrays.asList(values), Filters::equalTo, true);
  }

  /**
   * Creates a filter that matches if the card property is exactly one or more of the given values.
   *
   * @param values the values to match
   * @return a filter
   */
  public static MultiFilter<String> anyOf(String... values) {
    return new MultiFilter<>(Arrays.asList(values), Filters::is, true);
  }

  /**
   * Creates a filter that matches if and only if the card property is not equal to the given value.
   *
   * @param value the value to not match
   * @return a negated filter
   * @param <T> the type of the value
   */
  public static <T> SingleFilter<T> not(T value) {
    SingleFilter<T> filter = is(value);
    filter.negate();
    return filter;
  }

  /**
   * Negates the given filter.
   *
   * @param filter the filter to negate
   * @return a negated filter
   * @param <F> the type of the filter
   */
  public static <F extends Filter<?>> F not(F filter) {
    filter.negate();
    return filter;
  }

  /**
   * Creates a filter that matches if the card property is not equal to any of the given values. For
   * instance
   *
   * <pre>type(noneOf("Human", "Warrior"))</pre>
   *
   * will match all cards with a type that does not contain either "Human" or "Warrior".
   *
   * <p>This is a shorthand convenience method for
   *
   * <pre>not(anyOf(values))</pre>
   *
   * @param values the values not to match
   * @return a negated filter
   * @param <T> the type of the values
   */
  @SafeVarargs
  public static <T> MultiFilter<T> noneOf(T... values) {
    return not(anyOf(values));
  }

  /**
   * Creates a filter that matches if the card property is not equal to any of the given values. For
   * instance
   *
   * <pre>type(noneOf("Human", "Warrior"))</pre>
   *
   * will match all cards with a type that does not contain either "Human" or "Warrior".
   *
   * <p>This is a shorthand convenience method for
   *
   * <pre>not(anyOf(values))</pre>
   *
   * @param values the values not to match
   * @return a negated filter
   */
  public static MultiFilter<String> noneOf(String... values) {
    return not(anyOf(values));
  }

  static <T> SingleFilter<T> is(T value) {
    return new SingleFilter<>(value);
  }
}

package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;

public class Filters {

  @SafeVarargs
  public static <T> IFilter<T> exactly(T... values) {
    return Exactly.exactly(values);
  }

  public static <T> IFilter<T> exactly(Iterable<T> values) {
    return Exactly.exactly(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> exactlyOneOf(T... values) {
    return ExactlyOneOf.exactlyOneOf(values);
  }

  public static <T> IFilter<T> exactlyOneOf(Iterable<T> values) {
    return ExactlyOneOf.exactlyOneOf(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> atMost(T... values) {
    return AtMost.atMost(values);
  }

  public static <T> IFilter<T> atMost(Iterable<T> values) {
    return AtMost.atMost(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> moreThan(T... values) {
    return MoreThan.moreThan(values);
  }

  public static <T> IFilter<T> moreThan(Iterable<T> values) {
    return MoreThan.moreThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> atLeast(T... values) {
    return AtLeast.atLeast(values);
  }

  public static <T> IFilter<T> atLeast(Iterable<T> values) {
    return AtLeast.atLeast(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> lessThan(T... values) {
    return LessThan.lessThan(values);
  }

  public static <T> IFilter<T> lessThan(Iterable<T> values) {
    return LessThan.lessThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> notExactly(T... values) {
    return NotExactly.notExactly(values);
  }

  public static <T> IFilter<T> notExactly(Iterable<T> values) {
    return NotExactly.notExactly(values);
  }

  public static <T> IFilter<T> not(IFilter<T> filter) {
    return Not.not(filter);
  }

  public static <T> IFilter<T> not(T value) {
    return Not.not(value);
  }
}

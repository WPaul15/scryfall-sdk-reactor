package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;

public class Filters {

  @SafeVarargs
  public static <T> IFilter<T> isExactly(T... values) {
    return IsExactly.isExactly(values);
  }

  public static <T> IFilter<T> isExactly(Iterable<T> values) {
    return IsExactly.isExactly(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> exactly(T... values) {
    return IsExactly.isExactly(values);
  }

  public static <T> IFilter<T> exactly(Iterable<T> values) {
    return IsExactly.isExactly(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> isAtMost(T... values) {
    return IsAtMost.isAtMost(values);
  }

  public static <T> IFilter<T> isAtMost(Iterable<T> values) {
    return IsAtMost.isAtMost(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> atMost(T... values) {
    return IsAtMost.isAtMost(values);
  }

  public static <T> IFilter<T> atMost(Iterable<T> values) {
    return IsAtMost.isAtMost(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> isMoreThan(T... values) {
    return IsMoreThan.isMoreThan(values);
  }

  public static <T> IFilter<T> isMoreThan(Iterable<T> values) {
    return IsMoreThan.isMoreThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> moreThan(T... values) {
    return IsMoreThan.isMoreThan(values);
  }

  public static <T> IFilter<T> moreThan(Iterable<T> values) {
    return IsMoreThan.isMoreThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> isAtLeast(T... values) {
    return IsAtLeast.isAtLeast(values);
  }

  public static <T> IFilter<T> isAtLeast(Iterable<T> values) {
    return IsAtLeast.isAtLeast(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> atLeast(T... values) {
    return IsAtLeast.isAtLeast(values);
  }

  public static <T> IFilter<T> atLeast(Iterable<T> values) {
    return IsAtLeast.isAtLeast(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> isLessThan(T... values) {
    return IsLessThan.isLessThan(values);
  }

  public static <T> IFilter<T> isLessThan(Iterable<T> values) {
    return IsLessThan.isLessThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> lessThan(T... values) {
    return IsLessThan.isLessThan(values);
  }

  public static <T> IFilter<T> lessThan(Iterable<T> values) {
    return IsLessThan.isLessThan(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> isNotExactly(T... values) {
    return IsNotExactly.isNotExactly(values);
  }

  public static <T> IFilter<T> isNotExactly(Iterable<T> values) {
    return IsNotExactly.isNotExactly(values);
  }

  @SafeVarargs
  public static <T> IFilter<T> notExactly(T... values) {
    return IsNotExactly.isNotExactly(values);
  }

  public static <T> IFilter<T> notExactly(Iterable<T> values) {
    return IsNotExactly.isNotExactly(values);
  }

  public static <T> IFilter<T> isNot(IFilter<T> filter) {
    return IsNot.not(filter);
  }

  public static <T> IFilter<T> isNot(T value) {
    return IsNot.not(value);
  }

  public static <T> IFilter<T> not(IFilter<T> filter) {
    return IsNot.not(filter);
  }

  public static <T> IFilter<T> not(T value) {
    return IsNot.not(value);
  }
}

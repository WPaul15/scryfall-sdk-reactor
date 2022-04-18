package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;

public class Filters {

  @SafeVarargs
  public static <T> IFilter<T> isExactly(T... entries) {
    return IsExactly.isExactly(entries);
  }

  public static <T> IFilter<T> isExactly(Iterable<T> entries) {
    return IsExactly.isExactly(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> exactly(T... entries) {
    return IsExactly.isExactly(entries);
  }

  public static <T> IFilter<T> exactly(Iterable<T> entries) {
    return IsExactly.isExactly(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> isAtMost(T... entries) {
    return IsAtMost.isAtMost(entries);
  }

  public static <T> IFilter<T> isAtMost(Iterable<T> entries) {
    return IsAtMost.isAtMost(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> atMost(T... entries) {
    return IsAtMost.isAtMost(entries);
  }

  public static <T> IFilter<T> atMost(Iterable<T> entries) {
    return IsAtMost.isAtMost(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> isMoreThan(T... entries) {
    return IsMoreThan.isMoreThan(entries);
  }

  public static <T> IFilter<T> isMoreThan(Iterable<T> entries) {
    return IsMoreThan.isMoreThan(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> moreThan(T... entries) {
    return IsMoreThan.isMoreThan(entries);
  }

  public static <T> IFilter<T> moreThan(Iterable<T> entries) {
    return IsMoreThan.isMoreThan(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> isAtLeast(T... entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  public static <T> IFilter<T> isAtLeast(Iterable<T> entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> atLeast(T... entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  public static <T> IFilter<T> atLeast(Iterable<T> entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> isLessThan(T... entries) {
    return IsLessThan.isLessThan(entries);
  }

  public static <T> IFilter<T> isLessThan(Iterable<T> entries) {
    return IsLessThan.isLessThan(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> lessThan(T... entries) {
    return IsLessThan.isLessThan(entries);
  }

  public static <T> IFilter<T> lessThan(Iterable<T> entries) {
    return IsLessThan.isLessThan(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> isNotExactly(T... entries) {
    return IsNotExactly.isNotExactly(entries);
  }

  public static <T> IFilter<T> isNotExactly(Iterable<T> entries) {
    return IsNotExactly.isNotExactly(entries);
  }

  @SafeVarargs
  public static <T> IFilter<T> notExactly(T... entries) {
    return IsNotExactly.isNotExactly(entries);
  }

  public static <T> IFilter<T> notExactly(Iterable<T> entries) {
    return IsNotExactly.isNotExactly(entries);
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

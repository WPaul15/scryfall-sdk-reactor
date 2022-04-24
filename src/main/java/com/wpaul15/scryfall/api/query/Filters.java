package com.wpaul15.scryfall.api.query;

import java.util.Arrays;
import java.util.Collection;

public final class Filters {

  private static final String EXACTLY = "=";
  private static final String AT_MOST = "<=";
  private static final String MORE_THAN = ">";
  private static final String AT_LEAST = ">=";
  private static final String LESS_THAN = "<";

  public static <T> MonoFilter<T> exactly(T value) {
    return new ComparingMonoFilter<>(value, EXACTLY);
  }

  @SafeVarargs
  public static <T> ComparingFilter<T> exactly(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), EXACTLY);
  }

  public static <T> ComparingFilter<T> exactly(Collection<T> values) {
    return new ComparingFilter<>(values, EXACTLY);
  }

  @SafeVarargs
  public static <T> ComparingFilter<T> atMost(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), AT_MOST);
  }

  public static <T> ComparingFilter<T> atMost(Collection<T> values) {
    return new ComparingFilter<>(values, AT_MOST);
  }

  @SafeVarargs
  public static <T> ComparingFilter<T> moreThan(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), MORE_THAN);
  }

  public static <T> ComparingFilter<T> moreThan(Collection<T> values) {
    return new ComparingFilter<>(values, MORE_THAN);
  }

  @SafeVarargs
  public static <T> ComparingFilter<T> atLeast(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), AT_LEAST);
  }

  public static <T> ComparingFilter<T> atLeast(Collection<T> values) {
    return new ComparingFilter<>(values, AT_LEAST);
  }

  @SafeVarargs
  public static <T> ComparingFilter<T> lessThan(T... values) {
    return new ComparingFilter<>(Arrays.asList(values), LESS_THAN);
  }

  public static <T> ComparingFilter<T> lessThan(Collection<T> values) {
    return new ComparingFilter<>(values, LESS_THAN);
  }

  @SafeVarargs
  public static <T> MultiFilter<T> exactlyOneOf(T... values) {
    return new MultiFilter<>(Arrays.asList(values), Filters::exactly);
  }

  public static <T> MultiFilter<T> exactlyOneOf(Collection<T> values) {
    return new MultiFilter<>(values, Filters::exactly);
  }

  public static <T> NegatingFilter<T> not(Filter<T> filter) {
    return new NegatingFilter<>(filter);
  }

  public static <T> NegatingMonoFilter<T> not(T value) {
    return new NegatingMonoFilter<>(exactly(value));
  }

  public static <T> NegatingMonoFilter<T> not(MonoFilter<T> filter) {
    return new NegatingMonoFilter<>(filter);
  }

  public static <T> NegatingMultiFilter<T> not(MultiFilter<T> filter) {
    return new NegatingMultiFilter<>(filter);
  }
}

package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.IFilter;
import com.wpaul15.scryfall.api.query.IMonoFilter;
import com.wpaul15.scryfall.api.query.IMultiFilter;
import com.wpaul15.scryfall.api.query.INegatingFilter;
import com.wpaul15.scryfall.api.query.INegatingMonoFilter;
import com.wpaul15.scryfall.api.query.INegatingMultiFilter;

public class Filters {

  public static <T> IMonoFilter<T> exactly(T value) {
    return Exactly.ExactlyMono.exactly(value);
  }

  @SafeVarargs
  public static <T> IFilter<T> exactly(T... values) {
    return Exactly.exactly(values);
  }

  public static <T> IFilter<T> exactly(Iterable<T> values) {
    return Exactly.exactly(values);
  }

  @SafeVarargs
  public static <T> IMultiFilter<T> exactlyOneOf(T... values) {
    return ExactlyOneOf.exactlyOneOf(values);
  }

  public static <T> IMultiFilter<T> exactlyOneOf(Iterable<T> values) {
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

  public static <T> INegatingFilter<T> not(IFilter<T> filter) {
    return Not.not(filter);
  }

  public static <T> INegatingMonoFilter<T> not(T value) {
    return Not.NotMono.not(value);
  }

  public static <T> INegatingMonoFilter<T> not(IMonoFilter<T> filter) {
    return Not.NotMono.not(filter);
  }

  public static <T> INegatingMultiFilter<T> not(IMultiFilter<T> filter) {
    return Not.NotMulti.not(filter);
  }
}

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
  public static <T> IFilter<T> isAtLeast(T... entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  public static <T> IFilter<T> isAtLeast(Iterable<T> entries) {
    return IsAtLeast.isAtLeast(entries);
  }
}

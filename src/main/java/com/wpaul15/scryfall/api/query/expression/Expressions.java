package com.wpaul15.scryfall.api.query.expression;

import com.wpaul15.scryfall.api.query.IExpression;

public class Expressions {

  @SafeVarargs
  public static <T> IExpression<T> isExactly(T... entries) {
    return IsExactly.isExactly(entries);
  }

  public static <T> IExpression<T> isExactly(Iterable<T> entries) {
    return IsExactly.isExactly(entries);
  }

  @SafeVarargs
  public static <T> IExpression<T> isAtLeast(T... entries) {
    return IsAtLeast.isAtLeast(entries);
  }

  public static <T> IExpression<T> isAtLeast(Iterable<T> entries) {
    return IsAtLeast.isAtLeast(entries);
  }
}

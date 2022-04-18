package com.wpaul15.scryfall.api.query.expression;

import com.wpaul15.scryfall.api.query.IExpression;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
abstract class AbstractExpression<T> implements IExpression<T> {

  Iterable<T> entries;

  @Override
  public abstract String toQueryParams();
}

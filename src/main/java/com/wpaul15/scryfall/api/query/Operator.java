package com.wpaul15.scryfall.api.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
enum Operator {
  NONE(""),
  COLON(":"),
  EQUALS("="),
  LESS_THAN("<"),
  GREATER_THAN(">"),
  AT_MOST("<="),
  AT_LEAST(">=");

  String displayValue;

  @Override
  public String toString() {
    return displayValue;
  }
}

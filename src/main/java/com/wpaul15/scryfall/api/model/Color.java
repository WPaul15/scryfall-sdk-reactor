package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Color {
  WHITE("W"),
  BLUE("U"),
  BLACK("B"),
  RED("R"),
  GREEN("G"),
  COLORLESS("");

  String displayName;

  @JsonCreator
  private static Color create(String jsonValue) {
    for (Color color : values()) {
      if (jsonValue.equals(color.displayName)) {
        return color;
      }
    }

    return COLORLESS;
  }
}

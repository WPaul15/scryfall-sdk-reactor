package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Finish {
  NONFOIL("nonfoil"),
  FOIL("foil"),
  ETCHED("etched"),
  GLOSSY("glossy");

  String displayName;

  @Override
  @JsonValue
  public String toString() {
    return displayName;
  }

  @JsonCreator
  private static Finish create(String jsonValue) {
    for (Finish finish : values()) {
      if (jsonValue.equals(finish.displayName)) {
        return finish;
      }
    }

    return null;
  }
}

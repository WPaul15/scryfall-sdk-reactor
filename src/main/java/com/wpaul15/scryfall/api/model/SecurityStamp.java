package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SecurityStamp {
  OVAL("oval"),
  TRIANGLE("triangle"),
  ACORN("acorn"),
  ARENA("arena");

  String displayName;

  @Override
  @JsonValue
  public String toString() {
    return displayName;
  }

  @JsonCreator
  private static SecurityStamp create(String jsonValue) {
    for (SecurityStamp securityStamp : values()) {
      if (jsonValue.equals(securityStamp.displayName)) {
        return securityStamp;
      }
    }

    return null;
  }
}

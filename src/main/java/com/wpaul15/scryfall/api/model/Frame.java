package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Frame {
  ALPHA_1993("1993"),
  MIRAGE_1997("1997"),
  MODERN_2003("2003"),
  M15_2015("2015"),
  FUTURE("future");

  String displayName;

  @JsonCreator
  private static Frame create(String jsonValue) {
    for (Frame frame : values()) {
      if (jsonValue.equals(frame.displayName)) {
        return frame;
      }
    }

    return null;
  }
}

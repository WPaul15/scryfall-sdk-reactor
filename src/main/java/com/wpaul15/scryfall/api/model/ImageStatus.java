package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ImageStatus {
  MISSING("missing"),
  PLACEHOLDER("placeholder"),
  LOW_RESOLUTION("lowres"),
  HIGH_RESOLUTION("highres_scan");

  String displayName;

  @Override
  @JsonValue
  public String toString() {
    return displayName;
  }

  @JsonCreator
  private static ImageStatus create(String jsonValue) {
    for (ImageStatus imageStatus : values()) {
      if (jsonValue.equals(imageStatus.displayName)) {
        return imageStatus;
      }
    }

    return null;
  }
}

package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Language {
  ENGLISH("en"),
  SPANISH("sp"),
  FRENCH("fr"),
  GERMAN("de"),
  ITALIAN("it"),
  PORTUGUESE("pt"),
  JAPANESE("jp"),
  KOREAN("kr"),
  RUSSIAN("ru"),
  SIMPLIFIED_CHINESE("cs"),
  TRADITIONAL_CHINESE("ct"),
  HEBREW("he"),
  LATIN("la"),
  ANCIENT_GREEK("grc"),
  ARABIC("ar"),
  SANSKRIT("sa"),
  PHYREXIAN("ph");

  String displayName;

  @Override
  @JsonValue
  public String toString() {
    return displayName;
  }

  @JsonCreator
  private static Language create(String jsonValue) {
    for (Language language : values()) {
      if (jsonValue.equals(language.displayName)) {
        return language;
      }
    }

    return null;
  }
}

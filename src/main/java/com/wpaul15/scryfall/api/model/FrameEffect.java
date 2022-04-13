package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FrameEffect {
  LEGENDARY("legendary"),
  MIRACLE("miracle"),
  NYXTOUCHED("nyxtouched"),
  DRAFT("draft"),
  DEVOID("devoid"),
  TOMBSTONE("tombstone"),
  COLORSHIFTED("colorshifted"),
  INVERTED("inverted"),
  SUN_MOON_DFC("sunmoondfc"),
  COMPASS_LAND_DFC("compasslanddfc"),
  ORIGIN_PLANESWALKER_DFC("originpwdfc"),
  MOON_ELDRAZI_DFC("mooneldrazidfc"),
  WAXING_AND_WANING_MOON_DFC("waxingandwaningmoondfc"),
  SHOWCASE("showcase"),
  EXTENDED_ART("extendedart"),
  COMPANION("companion"),
  ETCHED("etched"),
  SNOW("snow"),
  LESSON("lesson");

  String displayName;

  @JsonCreator
  private static FrameEffect create(String jsonValue) {
    for (FrameEffect frameEffect : values()) {
      if (jsonValue.equals(frameEffect.displayName)) {
        return frameEffect;
      }
    }

    return null;
  }
}

package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Colors {
  /** The color white. */
  WHITE("W"),
  /** The color blue. */
  BLUE("U"),
  /** The color black. */
  BLACK("B"),
  /** The color red. */
  RED("R"),
  /** The color green. */
  GREEN("G"),
  /** More than one color. */
  MULTICOLORED("M"),
  /** No color. */
  COLORLESS("C"),
  /** An alias for the colors blue and white. */
  AZORIUS("azorius"),
  /** An alias for the colors black and blue. */
  DIMIR("dimir"),
  /** An alias for the colors black and red. */
  RAKDOS("rakdos"),
  /** An alias for the colors green and red. */
  GRUUL("gruul"),
  /** An alias for the colors green and white. */
  SELESNYA("selesnya"),
  /** An alias for the colors black and white. */
  ORZHOV("orzhov"),
  /** An alias for the colors red and blue. */
  IZZET("izzet"),
  /** An alias for the colors black and green. */
  GOLGARI("golgari"),
  /** An alias for the colors red and white. */
  BOROS("boros"),
  /** An alias for the colors green and blue. */
  SIMIC("simic"),
  /** An alias for the colors green, blue, and white. */
  BANT("bant"),
  /** An alias for the colors black, blue, and white. */
  ESPER("esper"),
  /** An alias for the colors black, red, and blue. */
  GRIXIS("grixis"),
  /** An alias for the colors black, green, and red. */
  JUND("jund"),
  /** An alias for the colors green, red, and white. */
  NAYA("naya"),
  /** An alias for the colors black, green, and white. */
  ABZAN("abzan"),
  /** An alias for the colors red, blue, and white. */
  JESKAI("jeskai"),
  /** An alias for the colors black, green, and blue. */
  SULTAI("sultai"),
  /** An alias for the colors black, red, and white. */
  MARDU("mardu"),
  /** An alias for the colors green, red, and blue. */
  TEMUR("temur"),
  /** An alias for the colors black, green, red, and blue. */
  CHAOS("chaos"),
  /** An alias for the colors black, green, red, and white. */
  AGGRESSION("aggression"),
  /** An alias for the colors green, red, blue, and white. */
  ALTRUISM("altruism"),
  /** An alias for the colors black, green, blue, and white. */
  GROWTH("growth"),
  /** An alias for the colors black, red, blue, and white. */
  ARTIFICE("artifice");

  String displayName;

  @Override
  @JsonValue
  public String toString() {
    return displayName;
  }

  @JsonCreator
  private static Colors create(String jsonValue) {
    for (Colors colors : List.of(WHITE, BLUE, BLACK, RED, GREEN)) {
      if (jsonValue.equals(colors.displayName)) {
        return colors;
      }
    }

    return COLORLESS;
  }
}

package com.wpaul15.scryfall.api.query.constants;

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
  /** An alias for the colors white and blue. */
  AZORIUS("azorius"),
  /** An alias for the colors blue and black. */
  DIMIR("dimir"),
  /** An alias for the colors black and red. */
  RAKDOS("rakdos"),
  /** An alias for the colors red and green. */
  GRUUL("gruul"),
  /** An alias for the colors green and white. */
  SELESNYA("selesnya"),
  /** An alias for the colors white black. */
  ORZHOV("orzhov"),
  /** An alias for the colors blue and red. */
  IZZET("izzet"),
  /** An alias for the colors black and green. */
  GOLGARI("golgari"),
  /** An alias for the colors red and white. */
  BOROS("boros"),
  /** An alias for the colors green and blue. */
  SIMIC("simic"),
  /** An alias for the colors green, white, and blue. */
  BANT("bant"),
  /** An alias for the colors white, blue, and black. */
  ESPER("esper"),
  /** An alias for the colors blue, black, red. */
  GRIXIS("grixis"),
  /** An alias for the colors black, red, and green. */
  JUND("jund"),
  /** An alias for the colors red, green, and white. */
  NAYA("naya"),
  /** An alias for the colors white, black, and green. */
  ABZAN("abzan"),
  /** An alias for the colors blue, red, and white. */
  JESKAI("jeskai"),
  /** An alias for the colors black, green, and blue. */
  SULTAI("sultai"),
  /** An alias for the colors red, white, black. */
  MARDU("mardu"),
  /** An alias for the colors green, blue, and red. */
  TEMUR("temur"),
  /** An alias for the colors blue, black, red, and green. */
  CHAOS("chaos"),
  /** An alias for the colors black, red, green, and white. */
  AGGRESSION("aggression"),
  /** An alias for the colors red, green, white, and blue. */
  ALTRUISM("altruism"),
  /** An alias for the colors green, white, blue, and black. */
  GROWTH("growth"),
  /** An alias for the colors white, blue, black, and red. */
  ARTIFICE("artifice");

  String displayName;

  @Override
  public String toString() {
    return displayName;
  }
}

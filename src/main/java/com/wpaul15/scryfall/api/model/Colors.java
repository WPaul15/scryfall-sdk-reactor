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
  WHITE("W"),
  BLUE("U"),
  BLACK("B"),
  RED("R"),
  GREEN("G"),
  MULTICOLORED("M"),
  COLORLESS("C"),
  AZORIUS("azorius"),
  DIMIR("dimir"),
  RAKDOS("rakdos"),
  GRUUL("gruul"),
  SELESNYA("selesnya"),
  ORZHOV("orzhov"),
  IZZET("izzet"),
  GOLGARI("golgari"),
  BOROS("boros"),
  SIMIC("simic"),
  BANT("bant"),
  ESPER("esper"),
  GRIXIS("grixis"),
  JUND("jund"),
  NAYA("naya"),
  ABZAN("abzan"),
  JESKAI("jeskai"),
  SULTAI("sultai"),
  MARDU("mardu"),
  TEMUR("temur"),
  CHAOS("chaos"),
  AGGRESSION("aggression"),
  ALTRUISM("altruism"),
  GROWTH("growth"),
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

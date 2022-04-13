package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

public record Legalities(
    Legality standard,
    Legality future,
    Legality historic,
    Legality gladiator,
    Legality pioneer,
    Legality modern,
    Legality legacy,
    Legality pauper,
    Legality vintage,
    Legality penny,
    Legality commander,
    Legality brawl,
    Legality historicbrawl,
    Legality alchemy,
    Legality paupercommander,
    Legality duel,
    Legality oldschool,
    Legality premodern) {

  @AllArgsConstructor
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public enum Legality {
    LEGAL("legal"),
    NOT_LEGAL("not_legal"),
    RESTRICTED("restricted"),
    BANNED("banned");

    String displayName;

    @JsonCreator
    private static Legality create(String jsonValue) {
      for (Legality legality : values()) {
        if (jsonValue.equals(legality.displayName)) {
          return legality;
        }
      }

      return null;
    }
  }
}

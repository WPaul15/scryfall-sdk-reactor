package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SetType {
  CORE("core"),
  EXPANSION("expansion"),
  MASTERS("masters"),
  ALCHEMY("alchemy"),
  MASTERPIECE("masterpiece"),
  ARSENAL("arsenal"),
  FROM_THE_VAULT("from_the_vault"),
  SPELLBOOK("spellbook"),
  PREMIUM_DECK("premium_deck"),
  DUEL_DECK("duel_deck"),
  DRAFT_INNOVATION("draft_innovation"),
  TREASURE_CHEST("treasure_chest"),
  COMMANDER("commander"),
  PLANECHASE("planechase"),
  ARCHENEMY("archenemy"),
  VANGUARD("vanguard"),
  FUNNY("funny"),
  STARTER("starter"),
  BOX("box"),
  PROMO("promo"),
  TOKEN("token"),
  MEMORABILIA("memorabilia");

  String displayName;

  @JsonCreator
  private static SetType create(String jsonValue) {
    for (SetType setType : values()) {
      if (jsonValue.equals(setType.displayName)) {
        return setType;
      }
    }

    return null;
  }
}

package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Game {
  PAPER("paper"),
  ARENA("arena"),
  MTG_ONLINE("mtgo");

  String displayName;

  @JsonCreator
  private static Game create(String jsonValue) {
    for (Game game : values()) {
      if (jsonValue.equals(game.displayName)) {
        return game;
      }
    }

    return null;
  }
}

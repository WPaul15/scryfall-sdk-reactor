package com.wpaul15.scryfall.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

public record RelatedCard(
    UUID id,
    String object,
    Component component,
    String name,
    @JsonProperty("type_line") String typeLine,
    URI uri) {

  @AllArgsConstructor
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public enum Component {
    TOKEN("token"),
    MELD_PART("meld_part"),
    MELD_RESULT("meld_result"),
    COMBO_PIECE("combo_piece");

    String displayName;

    @JsonCreator
    private static Component create(String jsonValue) {
      for (Component component : values()) {
        if (jsonValue.equals(component.displayName)) {
          return component;
        }
      }

      return null;
    }
  }
}

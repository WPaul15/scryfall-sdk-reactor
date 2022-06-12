package com.wpaul15.scryfall.api.query.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum MultiFace {
  /**
   * Split cards, such as <a href=https://scryfall.com/card/dgm/121/alive-well>Alive // Well</a>.
   */
  SPLIT("split"),
  /**
   * Flip cards, such as <a
   * href=https://scryfall.com/card/chk/153/akki-lavarunner-tok-tok-volcano-born>Akki Lavarunner //
   * Tok-Tok, Volcano Born</a>.
   */
  FLIP("flip"),
  /**
   * Transform cards, such as <a
   * href=https://scryfall.com/card/soi/243/arlinn-kord-arlinn-embraced-by-the-moon>Arlinn Kord //
   * Arlinn, Embraced by the Moon</a>.
   */
  TRANSFORM("transform"),
  /**
   * Meld cards, such as <a href=https://scryfall.com/card/emn/204/hanweir-battlements>Hanweir
   * Battlements</a> + <a href=https://scryfall.com/card/emn/130a/hanweir-garrison>Hanweir
   * Garrison</a> = <a
   * href=https://scryfall.com/card/emn/130b/hanweir-the-writhing-township>Hanweir, the Writhing
   * Township</a>.
   */
  MELD("meld"),
  /**
   * Leveler cards, such as <a href=https://scryfall.com/card/roe/152/kargan-dragonlord>Kargan
   * Dragonlord</a>.
   */
  LEVELER("leveler"),
  /**
   * Double-faced cards, such as <a
   * href=https://scryfall.com/card/isd/90/bloodline-keeper-lord-of-lineage>Bloodline Keeper // Lord
   * of Lineage</a>.
   *
   * <p>Note that all modal double-faced cards are a subset of double-faced cards and will thus also
   * be included in results using this value.
   *
   * @see MultiFace#MODAL_DOUBLE_FACED
   */
  DOUBLE_FACED("dfc"),
  /**
   * Modal double-faced cards, such as <a
   * href=https://scryfall.com/card/znr/90/agadeems-awakening-agadeem-the-undercrypt>Agadeem's
   * Awakening // Agadeem, the Undercrypt</a>.
   *
   * <p>Note that modal double-faced cards are a subset of double-faced cards.
   *
   * @see MultiFace#DOUBLE_FACED
   */
  MODAL_DOUBLE_FACED("mdfc");

  String displayName;

  @Override
  public String toString() {
    return displayName;
  }
}

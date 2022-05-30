package com.wpaul15.scryfall.api.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class CardQuery {

  Map<String, List<?>> filterMap = new HashMap<>();
  CardQueryMetadata metadata;

  private CardQuery(CardQueryMetadata metadata) {
    this.metadata = metadata;
  }

  public static CardQueryMetadata withSettings() {
    return new CardQueryMetadata();
  }

  public static CardQuery findCardsWith() {
    return new CardQuery(null);
  }

  public CardQuery color() {
    return this;
  }

  public CardQuery colorIdentity() {
    return this;
  }

  public CardQuery colorIndicator() {
    return this;
  }

  public CardQuery type() {
    return this;
  }

  public CardQuery oracleText() {
    return this;
  }

  public CardQuery fullOracleText() {
    return this;
  }

  public CardQuery manaCost() {
    return this;
  }

  public CardQuery cmc() {
    return this;
  }

  public CardQuery evenCmc() {
    return this;
  }

  public CardQuery oddCmc() {
    return this;
  }

  public CardQuery devotion() {
    return this;
  }

  public CardQuery manaProduced() {
    return this;
  }

  public CardQuery hybridManaSymbols() {
    return this;
  }

  public CardQuery phyrexianManaSymbols() {
    return this;
  }

  public CardQuery power() {
    return this;
  }

  public CardQuery toughness() {
    return this;
  }

  public CardQuery loyalty() {
    return this;
  }

  public CardQuery multipleFacesOfType() {
    return this;
  }

  public CardQuery cardType() {
    return this;
  }

  public CardQuery funnyCards() {
    return this;
  }

  public CardQuery rarity() {
    return this;
  }

  public CardQuery set() {
    return this;
  }

  public CardQuery cardNumber() {
    return this;
  }

  public CardQuery block() {
    return this;
  }

  public CardQuery setType() {
    return this;
  }

  public CardQuery cube() {
    return this;
  }

  public CardQuery legality() {
    return this;
  }

  public CardQuery commanderType() {
    return this;
  }

  public CardQuery price() {
    return this;
  }

  public CardQuery cheapestPrintIn() {
    return this;
  }

  public CardQuery artist() {
    return this;
  }

  public CardQuery multipleArtists() {
    return this;
  }

  public CardQuery flavorText() {
    return this;
  }

  // TODO: Use real parameter type
  public CardQuery watermark(String watermark) {
    return this;
  }

  public CardQuery watermark() {
    return this;
  }

  public CardQuery numberOfIllustrations() {
    return this;
  }

  public CardQuery border() {
    return this;
  }

  public CardQuery frame() {
    return this;
  }

  public CardQuery stamp() {
    return this;
  }

  public CardQuery game() {
    return this;
  }

  public CardQuery year() {
    return this;
  }

  public CardQuery date() {
    return this;
  }

  public CardQuery artTags() {
    return this;
  }

  public CardQuery oracleTags() {
    return this;
  }

  public CardQuery numberOfPrints() {
    return this;
  }

  public CardQuery numberOfSets() {
    return this;
  }

  public CardQuery numberOfPaperPrints() {
    return this;
  }

  public CardQuery numberOfPaperSets() {
    return this;
  }

  public CardQuery reprints() {
    return this;
  }

  public CardQuery unique() {
    return this;
  }

  public CardQuery language() {
    return this;
  }

  public CardQuery firstPrintOf() {
    return this;
  }

  public CardQuery printsIn() {
    return this;
  }

  // TODO Figure out 'is' search term
  // public CardQuery cardIs() { return this; }

  /**
   * Syntactic sugar.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery and() {
    return this;
  }

  @Override
  public String toString() {
    return "";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  static final class CardQueryMetadata {

    Map<String, String> metadataMap = new HashMap<>();

    public CardQuery findCardsWith() {
      return new CardQuery(this);
    }

    public CardQueryMetadata includeExtras() {
      return this;
    }

    public CardQueryMetadata showUnique() {
      return this;
    }

    public CardQueryMetadata orderBy() {
      return this;
    }

    public CardQueryMetadata prefer() {
      return this;
    }

    public CardQueryMetadata sortDirection() {
      return this;
    }

    @Override
    public String toString() {
      return "";
    }
  }
}

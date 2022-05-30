package com.wpaul15.scryfall.api.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class CardQuery {

  private static final String COLOR_KEY = "c";
  private static final String COLOR_IDENTITY_KEY = "id";
  private static final String TYPE_KEY = "t";
  private static final String ORACLE_TEXT_KEY = "o";
  private static final String FULL_ORACLE_TEXT_KEY = "fo";
  private static final String KEYWORD_KEY = "keyword";
  private static final String IS_KEY = "is";
  private static final String MANA_COST_KEY = "m";
  private static final String CMC_KEY = "cmc";
  private static final String DEVOTION_KEY = "devotion";
  private static final String MANA_PRODUCED_KEY = "produces";
  private static final String POWER_KEY = "pow";
  private static final String TOUGHNESS_KEY = "tou";
  private static final String LOYALTY_KEY = "loy";
  private static final String RARITY_KEY = "r";
  private static final String IN_KEY = "in";
  private static final String SET_KEY = "s";
  private static final String CARD_NUMBER_KEY = "cn";
  private static final String BLOCK_KEY = "b";
  private static final String SET_TYPE_KEY = "st";
  private static final String CUBE_KEY = "cube";
  private static final String FORMAT_LEGALITY_KEY = "f";
  private static final String PRICE_USD_KEY = "usd";
  private static final String PRICE_EUR_KEY = "eur";
  private static final String PRICE_TIX_KEY = "tix";
  private static final String ARTIST_KEY = "a";
  private static final String FLAVOR_TEXT_KEY = "ft";
  private static final String WATERMARK_KEY = "wm";
  private static final String NUM_ILLUSTRATIONS_KEY = "illustrations";
  private static final String BORDER_KEY = "border";
  private static final String FRAME_KEY = "frame";
  private static final String STAMP_KEY = "stamp";
  private static final String GAME_KEY = "game";
  private static final String YEAR_KEY = "year";
  private static final String DATE_KEY = "date";
  private static final String ART_TAGS_KEY = "art";
  private static final String ORACLE_TAGS_KEY = "otag";
  private static final String NUM_PRINTS_KEY = "prints";
  private static final String NUM_SETS_KEY = "sets";
  private static final String NUM_PAPER_PRINTS_KEY = "paperprints";
  private static final String NUM_PAPER_SETS_KEY = "papersets";
  private static final String LANGUAGE_KEY = "language";
  private static final String NEW_KEY = "new";

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

    private static final String INCLUDE_KEY = "include";
    private static final String UNIQUE_KEY = "unique";
    private static final String ORDER_KEY = "order";
    private static final String PREFERENCE_KEY = "prefer";
    private static final String DIRECTION_KEY = "direction";

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

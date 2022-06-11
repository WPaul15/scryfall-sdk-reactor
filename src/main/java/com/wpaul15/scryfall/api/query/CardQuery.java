package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.Filters.is;

import com.wpaul15.scryfall.api.query.constants.Colors;
import com.wpaul15.scryfall.api.query.options.Printing;
import com.wpaul15.scryfall.api.query.options.SortDirection;
import com.wpaul15.scryfall.api.query.options.SortField;
import com.wpaul15.scryfall.api.query.options.Uniqueness;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CardQuery {

  private static final String COLOR_KEY = "c";
  private static final String COLOR_IDENTITY_KEY = "id";
  private static final String TYPE_KEY = "t";
  private static final String ORACLE_TEXT_KEY = "o";
  private static final String FULL_ORACLE_TEXT_KEY = "fo";
  private static final String KEYWORD_KEY = "keyword";
  private static final String IS_KEY = "is";
  private static final String HAS_KEY = "has";
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

  final Map<String, List<Filter<?>>> filters = new LinkedHashMap<>();
  CardQueryOptions searchOptions;

  public static CardQuery findCardsWith() {
    return new CardQuery();
  }

  public CardQuery searchOptions(CardQueryOptions searchOptions) {
    this.searchOptions = searchOptions;
    return this;
  }

  /**
   * Adds a search term to filter results by color.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery color(ComparingFilter<Colors> filter) {
    addFilter(COLOR_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by color.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery color(MultiFilter<Colors> filter) {
    addFilter(COLOR_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by color identity.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIdentity(ComparingFilter<Colors> filter) {
    addFilter(COLOR_IDENTITY_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by color identity.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIdentity(MultiFilter<Colors> filter) {
    addFilter(COLOR_IDENTITY_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by cards containing a color indicator.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery colorIndicator() {
    addFilter(HAS_KEY, is("indicator"));
    return this;
  }

  /**
   * Adds a term to filter results by card type. Accepts supertypes, types, and subtypes. The value
   * is case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param type the type to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery type(String type) {
    addFilter(TYPE_KEY, is(type));
    return this;
  }

  /**
   * Adds a term to filter results by card type. Accepts supertypes, types, and subtypes. The value
   * is case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param filter the filter to add
   * @return this {@code CardQuery}
   */
  public CardQuery type(SingleFilter<String> filter) {
    addFilter(TYPE_KEY, filter);
    return this;
  }

  /**
   * Adds a term to filter results by card type. Accepts supertypes, types, and subtypes. The value
   * is case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param filter the filter to add
   * @return this {@code CardQuery}
   */
  public CardQuery type(MultiFilter<String> filter) {
    addFilter(TYPE_KEY, filter);
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

  // TODO: Fix confusing name
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
    String optionsString = "";

    if (searchOptions != null) {
      optionsString =
          searchOptions.options.entrySet().stream()
              .map(entry -> entry.getKey() + ":" + entry.getValue())
              .collect(Collectors.joining(" "));
    }

    String filterString =
        filters.entrySet().stream()
            .flatMap(
                entry -> entry.getValue().stream().map(filter -> Map.entry(entry.getKey(), filter)))
            .map(entry -> entry.getValue().toFilterString(entry.getKey()))
            .collect(Collectors.joining(" "));

    StringBuilder builder = new StringBuilder();

    if (!optionsString.isBlank()) {
      builder.append(optionsString);

      if (!filterString.isBlank()) {
        builder.append(" ").append(filterString);
      }
    } else {
      builder.append(filterString);
    }

    return URLEncoder.encode(builder.toString(), StandardCharsets.UTF_8);
  }

  private void addFilter(String key, Filter<?> filter) {
    Optional.ofNullable(filters.get(key))
        .ifPresentOrElse(
            list -> list.add(filter),
            () -> {
              List<Filter<?>> list = new ArrayList<>();
              list.add(filter);
              filters.put(key, list);
            });
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public static final class CardQueryOptions {

    private static final String INCLUDE_KEY = "include";
    private static final String UNIQUENESS_KEY = "unique";
    private static final String ORDER_KEY = "order";
    private static final String PREFERENCE_KEY = "prefer";
    private static final String DIRECTION_KEY = "direction";

    Map<String, String> options = new HashMap<>();

    /**
     * Creates a new set of options.
     *
     * @return a new {@code CardQueryOptions} instance
     */
    public static CardQueryOptions options() {
      return new CardQueryOptions();
    }

    /**
     * Sets the query to include "extra" cards such as Vanguard cards, plane cards, art series
     * cards, etc. This is disabled by default.
     *
     * @return this {@code CardQueryOptions}
     */
    public CardQueryOptions includeExtras() {
      options.put(INCLUDE_KEY, "extras");
      return this;
    }

    /**
     * Sets how duplicate printings should be removed. The exact printing(s) returned are determined
     * by {@link CardQueryOptions#prefer(Printing)}. The default is {@link Uniqueness#CARDS}.
     *
     * @return this {@code CardQueryOptions}
     * @see CardQueryOptions#prefer(Printing)
     */
    public CardQueryOptions uniqueness(Uniqueness option) {
      options.put(UNIQUENESS_KEY, option.toString());
      return this;
    }

    /**
     * Sets the field by which to sort cards. The default is {@link SortField#NAME}.
     *
     * @return this {@code CardQueryOptions}
     */
    public CardQueryOptions orderBy(SortField option) {
      options.put(ORDER_KEY, option.toString());
      return this;
    }

    /**
     * Sets which printing of cards to prefer. The default is {@link Printing#NEWEST}.
     *
     * @return this {@code CardQueryOptions}
     */
    public CardQueryOptions prefer(Printing option) {
      options.put(PREFERENCE_KEY, option.toString());
      return this;
    }

    /**
     * Sets the sort direction using the field specified in {@link
     * CardQueryOptions#orderBy(SortField)}. The default is {@link SortDirection#ASCENDING}.
     *
     * @return this {@code CardQueryOptions}
     * @see CardQueryOptions#orderBy(SortField)
     */
    public CardQueryOptions sortDirection(SortDirection option) {
      options.put(DIRECTION_KEY, option.toString());
      return this;
    }
  }
}

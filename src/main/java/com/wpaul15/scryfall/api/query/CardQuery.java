package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.Filters.exactly;

import com.wpaul15.scryfall.api.model.Colors;
import com.wpaul15.scryfall.api.model.Rarity;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class CardQuery {

  private static final String COLOR_KEY = "c";
  private static final String COLOR_IDENTITY_KEY = "id";
  private static final String INCLUDE_KEY = "include";
  private static final String IS_KEY = "is";
  private static final String LOYALTY_KEY = "loy";
  private static final String POWER_KEY = "pow";
  private static final String RARITY_KEY = "r";
  private static final String TOUGHNESS_KEY = "tou";
  public static final String TYPE_KEY = "t";

  Map<String, List<Filter<?>>> params = new HashMap<>();

  /**
   * Starts a new card query.
   *
   * @return a new {@code CardQuery}
   */
  public static CardQuery where() {
    return new CardQuery();
  }

  /**
   * Adds a search term to filter results by color.
   *
   * <p>This method is a shortcut for
   *
   * <pre>colorIs(exactly(color))</pre>
   *
   * @param color the color to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery colorIs(Colors color) {
    addToParams(COLOR_KEY, exactly(color));
    return this;
  }

  /**
   * Adds a search term to filter results by color.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIs(Filter<Colors> filter) {
    addToParams(COLOR_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by color identity.
   *
   * <p>This method is a shortcut for
   *
   * <pre>colorIdentityIs(exactly(colorIdentity))</pre>
   *
   * @param colorIdentity the color identity to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery colorIdentityIs(Colors colorIdentity) {
    addToParams(COLOR_IDENTITY_KEY, exactly(colorIdentity));
    return this;
  }

  /**
   * Adds a search term to filter results by color identity.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIdentityIs(Filter<Colors> filter) {
    addToParams(COLOR_IDENTITY_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes. The
   * search value is case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * <p>This method is a shortcut for
   *
   * <pre>typeIs(exactly(type))</pre>
   *
   * @param type the type to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(String type) {
    addToParams(TYPE_KEY, exactly(type));
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes. The
   * search values are case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(MultiFilter<String> filter) {
    addToParams(TYPE_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes. The
   * search value is case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(NegatingMonoFilter<String> filter) {
    addToParams(TYPE_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes. The
   * search values are case-insensitive and may be a partial term, e.g. "drag" instead of "dragon".
   *
   * <p>Note that partial search terms may return more cards than expected. For instance, "dra" will
   * match all cards of type Eldrazi, Drake, Dragon, Hydra, or Chandra
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(NegatingMultiFilter<String> filter) {
    addToParams(TYPE_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by power.
   *
   * <p>The value is a {@code Double} because there are some cards from the "Un-" sets that have
   * fractional powers, such as <a href="https://scryfall.com/card/unh/49/bad-ass">Bad Ass</a>.
   *
   * <p>This method is a shortcut for
   *
   * <pre>powerIs(exactly(power))</pre>
   *
   * @param power the power to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery powerIs(Double power) {
    addToParams(POWER_KEY, exactly(power));
    return this;
  }

  /**
   * Adds a search term to filter results by power.
   *
   * <p>The values are {@code Double}s because there are some cards from the "Un-" sets that have
   * fractional powers, such as <a href="https://scryfall.com/card/unh/49/bad-ass">Bad Ass</a>.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery powerIs(MonoFilter<Double> filter) {
    addToParams(POWER_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by power. Specifically, this method will match all cards
   * with power 0 or * (variable power) and/or cards that enter the battlefield with any number of
   * +1/+1 counters.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery powerIsZeroOrVariable() {
    addToParams(POWER_KEY, exactly("*"));
    return this;
  }

  /**
   * Adds a search term to filter results by toughness.
   *
   * <p>The values are {@code Double}s because there are some cards from the "Un-" sets that have
   * fractional toughnesses, such as <a href="https://scryfall.com/card/unh/95/fat-ass">Fat Ass</a>.
   *
   * <p>This method is a shortcut for
   *
   * <pre>toughnessIs(exactly(toughness))</pre>
   *
   * @param toughness the toughness to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery toughnessIs(Double toughness) {
    addToParams(TOUGHNESS_KEY, exactly(toughness));
    return this;
  }

  /**
   * Adds a search term to filter results by toughness.
   *
   * <p>The values are {@code Double}s because there are some cards from the "Un-" sets that have
   * fractional toughnesses, such as <a href="https://scryfall.com/card/unh/95/fat-ass">Fat Ass</a>.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery toughnessIs(MonoFilter<Double> filter) {
    addToParams(TOUGHNESS_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by toughness. Specifically, this method will match all
   * cards with toughness 0 or * (variable toughness) and/or cards that enter the battlefield with
   * any number of +1/+1 counters.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery toughnessIsZeroOrVariable() {
    addToParams(TOUGHNESS_KEY, exactly("*"));
    return this;
  }

  /**
   * Adds a search term to filter by loyalty.
   *
   * <p>This method is a shortcut for
   *
   * <pre>loyaltyIs(exactly(loyalty))</pre>
   *
   * @param loyalty the loyalty to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery loyaltyIs(Integer loyalty) {
    addToParams(LOYALTY_KEY, exactly(loyalty));
    return this;
  }

  /**
   * Adds a search term to filter by loyalty.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery loyaltyIs(MonoFilter<Integer> filter) {
    addToParams(LOYALTY_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by loyalty. Specifically, this method will match all cards
   * with loyalty 0, X, or * (variable loyalty) and/or cards that enter the battlefield with any
   * number of loyalty counters.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery loyaltyIsZeroOrVariable() {
    addToParams(LOYALTY_KEY, exactly("*"));
    return this;
  }

  /**
   * Adds a search term to filter by a special search keyword.
   *
   * @param searchKeyword the search keyword to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery cardIs(SearchKeywords searchKeyword) {
    addToParams(IS_KEY, exactly(searchKeyword));
    return this;
  }

  /**
   * Adds a search term to filter by special search keywords.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery cardIs(MultiFilter<SearchKeywords> filter) {
    addToParams(IS_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to include special cards in the search results, namely:
   *
   * <ul>
   *   <li>Vanguard cards from the Vanguard format;
   *   <li>Plane cards from the Planechase format;
   *   <li>Phenomenon cards from the Planechase format;
   *   <li>Scheme cards from the Archenemy format; and
   *   <li>Memorabilia cards, such as tournament prize cards, gold-bordered world champion deck
   *       cards, art series cards, oversized cards, etc.
   * </ul>
   *
   * These cards are hidden from search results by default.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery extrasAreIncluded() {
    addToParams(INCLUDE_KEY, exactly("extras"));
    return this;
  }

  /**
   * Adds a search term to filter by rarity.
   *
   * <p>This method is a shortcut for
   *
   * <pre>rarityIs(exactly(rarity))</pre>
   *
   * @param rarity the rarity to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery rarityIs(Rarity rarity) {
    addToParams(RARITY_KEY, exactly(rarity));
    return this;
  }

  /**
   * Adds a search term to filter by rarity.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery rarityIs(Filter<Rarity> filter) {
    addToParams(RARITY_KEY, filter);
    return this;
  }

  /**
   * Syntactic sugar.
   *
   * @return this {@code CardQuery}
   */
  public CardQuery and() {
    return this;
  }

  public boolean isEmpty() {
    return params.isEmpty();
  }

  @Override
  public String toString() {
    if (params.isEmpty()) {
      return "";
    }

    String rawParams =
        params.entrySet().stream()
            .flatMap(
                entry ->
                    entry.getValue().stream()
                        .map(queryParams -> Map.entry(entry.getKey(), queryParams)))
            .map(
                entry -> {
                  StringBuilder builder = new StringBuilder();

                  if (entry.getValue() instanceof NegatingFilter<?> negatingFilter
                      && negatingFilter.isNegated()) {
                    builder.append("-");
                  }

                  builder.append(entry.getValue().toQueryParams(entry.getKey()));

                  return builder.toString();
                })
            .collect(Collectors.joining(" "));

    return URLEncoder.encode(rawParams, StandardCharsets.UTF_8);
  }

  private void addToParams(String key, Filter<?> filter) {
    if (!params.containsKey(key)) {
      List<Filter<?>> list = new ArrayList<>();
      list.add(filter);
      params.put(key, list);
    } else {
      params.get(key).add(filter);
    }
  }
}

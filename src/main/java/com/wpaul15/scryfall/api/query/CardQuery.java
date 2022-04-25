package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.Filters.exactly;

import com.wpaul15.scryfall.api.model.Colors;
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
   * @param value the type to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(String value) {
    addToParams(TYPE_KEY, exactly(value));
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

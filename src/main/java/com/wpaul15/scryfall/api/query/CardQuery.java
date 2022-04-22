package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.filter.Filters.exactly;

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
public class CardQuery {

  private static final String COLOR_KEY = "c";
  private static final String COLOR_IDENTITY_KEY = "id";
  public static final String TYPE_KEY = "t";

  Map<String, List<IQueryParams>> params = new HashMap<>();

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
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIs(IFilter<Colors> filter) {
    addToParams(COLOR_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by color identity.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery colorIdentityIs(IFilter<Colors> filter) {
    addToParams(COLOR_IDENTITY_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes.
   *
   * @param value the type to filter by
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(String value) {
    addToParams(TYPE_KEY, exactly(value));
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(INegatingMonoFilter<String> filter) {
    addToParams(TYPE_KEY, filter);
    return this;
  }

  /**
   * Adds a search term to filter results by type. Accepts supertypes, types, or subtypes.
   *
   * @param filter the filter to apply
   * @return this {@code CardQuery}
   */
  public CardQuery typeIs(IMultiFilter<String> filter) {
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

                  if (entry.getValue() instanceof INegatingFilter<?> negatingFilter
                      && negatingFilter.isNegated()) {
                    builder.append("-");
                  }

                  builder.append(entry.getValue().toQueryParams(entry.getKey()));

                  return builder.toString();
                })
            .collect(Collectors.joining(" "));

    return URLEncoder.encode(rawParams, StandardCharsets.UTF_8);
  }

  private void addToParams(String key, IQueryParams queryParams) {
    if (!params.containsKey(key)) {
      List<IQueryParams> list = new ArrayList<>();
      list.add(queryParams);
      params.put(key, list);
    } else {
      params.get(key).add(queryParams);
    }
  }
}

package com.wpaul15.scryfall.api.query;

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

  Map<String, List<IQueryParams>> params = new HashMap<>();

  public static CardQuery where() {
    return new CardQuery();
  }

  public CardQuery colorIs(IFilter<Colors> filter) {
    addToParams(COLOR_KEY, filter);
    return this;
  }

  public CardQuery colorIdentityIs(IFilter<Colors> filter) {
    addToParams(COLOR_IDENTITY_KEY, filter);
    return this;
  }

  /**
   * Syntactic sugar.
   *
   * @return this {@code SearchQuery}
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

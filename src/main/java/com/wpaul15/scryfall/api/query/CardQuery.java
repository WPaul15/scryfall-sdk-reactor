package com.wpaul15.scryfall.api.query;

import com.wpaul15.scryfall.api.model.Color;
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
public class CardQuery implements IQueryParams {

  private static final String COLOR_KEY = "c";

  Map<String, List<IQueryParams>> params = new HashMap<>();

  public static CardQuery where() {
    return new CardQuery();
  }

  public CardQuery color(IFilter<Color> colorQuery) {
    if (!params.containsKey(COLOR_KEY)) {
      List<IQueryParams> list = new ArrayList<>();
      list.add(colorQuery);
      params.put(COLOR_KEY, list);
    } else {
      params.get(COLOR_KEY).add(colorQuery);
    }

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
  public String toQueryParams() {
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

                  builder.append(entry.getKey()).append(entry.getValue().toQueryParams());

                  return builder.toString();
                })
            .collect(Collectors.joining(" "));

    return URLEncoder.encode(rawParams, StandardCharsets.UTF_8);
  }
}

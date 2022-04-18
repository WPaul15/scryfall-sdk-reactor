package com.wpaul15.scryfall.api.query;

import com.wpaul15.scryfall.api.model.Color;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CardQuery implements IQueryParams {

  Map<String, IQueryParams> params = new HashMap<>();

  public static CardQuery where() {
    return new CardQuery();
  }

  public CardQuery color(IFilter<Color> colorQuery) {
    params.put("c", colorQuery);
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

    StringBuilder builder = new StringBuilder();

    params.forEach(
        (key, value) -> {
          if (value instanceof INegatingFilter<?> negatingFilter && negatingFilter.isNegated()) {
            builder.append("-");
          }

          builder
              .append(key)
              .append(URLEncoder.encode(value.toQueryParams(), StandardCharsets.UTF_8));
        });

    return builder.toString();
  }
}

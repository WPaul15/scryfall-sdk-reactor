package com.wpaul15.scryfall.api.query.filter;

import com.wpaul15.scryfall.api.query.ICombiningFilter;
import com.wpaul15.scryfall.api.query.IFilter;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
abstract class CombiningFilter<T> implements ICombiningFilter<T> {

  Iterable<IFilter<T>> values;

  @Override
  public String toQueryParams(String key) {
    return "("
        + StreamSupport.stream(values.spliterator(), false)
            .map(filter -> filter.toQueryParams(key))
            .collect(Collectors.joining(" or "))
        + ")";
  }
}

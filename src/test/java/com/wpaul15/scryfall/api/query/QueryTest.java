package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.CardQuery.CardQueryOptions.options;
import static com.wpaul15.scryfall.api.query.CardQuery.findCardsWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.wpaul15.scryfall.api.query.options.Printing;
import com.wpaul15.scryfall.api.query.options.SortDirection;
import com.wpaul15.scryfall.api.query.options.SortField;
import com.wpaul15.scryfall.api.query.options.Uniqueness;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class QueryTest {

  @ParameterizedTest
  @MethodSource("optionsArguments")
  void optionsShouldBeCorrectlyEncoded(CardQuery query, String expectedFilters) {
    assertThat(query.toString())
        .isEqualTo(URLEncoder.encode(expectedFilters, StandardCharsets.UTF_8));
  }

  private static Stream<Arguments> optionsArguments() {
    return Stream.of(
        arguments(findCardsWith(), ""),
        arguments(findCardsWith().searchOptions(options().includeExtras()), "include:extras"),
        arguments(
            findCardsWith()
                .searchOptions(
                    options().orderBy(SortField.COLOR).sortDirection(SortDirection.DESCENDING)),
            "order:color direction:desc"),
        arguments(
            findCardsWith()
                .searchOptions(options().prefer(Printing.NEWEST).orderBy(SortField.ARTIST)),
            "prefer:newest order:artist"),
        arguments(
            findCardsWith().searchOptions(options().uniqueness(Uniqueness.ART)), "unique:art"));
  }
}

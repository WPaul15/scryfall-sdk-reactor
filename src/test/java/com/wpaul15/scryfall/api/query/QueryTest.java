package com.wpaul15.scryfall.api.query;

import static com.wpaul15.scryfall.api.query.CardQuery.CardQueryOptions.options;
import static com.wpaul15.scryfall.api.query.CardQuery.findCardsWith;
import static com.wpaul15.scryfall.api.query.Filters.anyOf;
import static com.wpaul15.scryfall.api.query.Filters.equalTo;
import static com.wpaul15.scryfall.api.query.Filters.greaterThan;
import static com.wpaul15.scryfall.api.query.Filters.greaterThanOrEqualTo;
import static com.wpaul15.scryfall.api.query.Filters.lessThan;
import static com.wpaul15.scryfall.api.query.Filters.lessThanOrEqualTo;
import static com.wpaul15.scryfall.api.query.Filters.noneOf;
import static com.wpaul15.scryfall.api.query.Filters.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.wpaul15.scryfall.api.model.Color;
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
  void optionsShouldBeCorrectlyEncoded(CardQuery query, String expectedOptions) {
    assertThat(query.toString())
        .isEqualTo(URLEncoder.encode(expectedOptions, StandardCharsets.UTF_8));
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

  @ParameterizedTest
  @MethodSource("filterArguments")
  void filtersShouldBeCorrectlyEncoded(CardQuery query, String expectedFilters) {
    assertThat(query.toString())
        .isEqualTo(URLEncoder.encode(expectedFilters, StandardCharsets.UTF_8));
  }

  private static Stream<Arguments> filterArguments() {
    return Stream.of(
        arguments(findCardsWith(), ""),
        arguments(findCardsWith().type(equalTo("Human")), "t:Human"),
        arguments(findCardsWith().type(not(equalTo("Angel"))), "-t:Angel"),
        arguments(findCardsWith().type(not(not(equalTo("Demon")))), "t:Demon"),
        arguments(
            findCardsWith().type(anyOf("Instant", "Sorcery", "Enchantment")),
            "(t:Instant or t:Sorcery or t:Enchantment)"),
        arguments(
            findCardsWith().type(not(anyOf("Sorcery", "Instant"))), "-(t:Sorcery or t:Instant)"),
        arguments(
            findCardsWith().type(noneOf("Wizard", "Elemental")), "-(t:Wizard or t:Elemental)"),
        arguments(findCardsWith().type(equalTo("Human Warrior")), "t:\"Human Warrior\""),
        arguments(findCardsWith().color(lessThan(Color.RED, Color.WHITE, Color.BLACK)), "c<RWB"),
        arguments(findCardsWith().color(greaterThan(Color.GREEN, Color.BLUE)), "c>GU"),
        arguments(
            findCardsWith().color(lessThanOrEqualTo(Color.BLACK, Color.GREEN, Color.RED)),
            "c<=BGR"),
        arguments(
            findCardsWith().color(greaterThanOrEqualTo(Color.BLACK, Color.BLUE, Color.BLACK)),
            "c>=BU"),
        arguments(findCardsWith().color(not(equalTo(Color.BLACK))), "-c=B"),
        arguments(findCardsWith().color(anyOf(Color.WHITE, Color.COLORLESS)), "(c=W or c=C)"));
  }
}

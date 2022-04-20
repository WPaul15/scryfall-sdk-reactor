package com.wpaul15.scryfall.api;

import static com.wpaul15.scryfall.api.query.filter.Filters.exactlyOneOf;
import static com.wpaul15.scryfall.api.query.filter.Filters.isAtLeast;
import static com.wpaul15.scryfall.api.query.filter.Filters.isAtMost;
import static com.wpaul15.scryfall.api.query.filter.Filters.isExactly;
import static com.wpaul15.scryfall.api.query.filter.Filters.isExactlyOneOf;
import static com.wpaul15.scryfall.api.query.filter.Filters.isLessThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.isMoreThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.isNot;
import static com.wpaul15.scryfall.api.query.filter.Filters.isNotExactly;
import static com.wpaul15.scryfall.api.query.filter.Filters.not;
import static org.assertj.core.api.Assertions.assertThat;

import com.wpaul15.scryfall.api.model.Color;
import com.wpaul15.scryfall.api.query.CardQuery;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FilterTest {

  @ParameterizedTest
  @MethodSource("filterArguments")
  void shouldEncodeFiltersCorrectly(CardQuery query, String expectedParams) {
    assertThat(query.toString())
        .isEqualTo(URLEncoder.encode(expectedParams, StandardCharsets.UTF_8));
  }

  private static Stream<Arguments> filterArguments() {
    return Stream.of(
        Arguments.of(CardQuery.where(), ""),
        Arguments.of(CardQuery.where().color(isExactly(Color.BLUE, Color.BLACK)), "c=UB"),
        Arguments.of(
            CardQuery.where().color(isLessThan(Color.RED, Color.WHITE, Color.BLUE)), "c<RWU"),
        Arguments.of(CardQuery.where().color(isMoreThan(Color.GREEN, Color.WHITE)), "c>GW"),
        Arguments.of(CardQuery.where().color(isAtLeast(Color.BLUE)), "c>=U"),
        Arguments.of(CardQuery.where().color(isAtMost(Color.BLACK, Color.RED)), "c<=BR"),
        Arguments.of(CardQuery.where().color(isNotExactly(Color.BLUE, Color.GREEN)), "c!=UG"),
        Arguments.of(CardQuery.where().color(isNot(Color.MULTICOLORED)), "-c=M"),
        Arguments.of(CardQuery.where().color(isNot(not(Color.COLORLESS))), "c=C"),
        Arguments.of(
            CardQuery.where().color(isExactlyOneOf(Color.WHITE, Color.BLUE, Color.BLACK)),
            "(c=W or c=U or c=B)"),
        Arguments.of(
            CardQuery.where().color(isNot(exactlyOneOf(Color.GREEN, Color.RED))), "-(c=G or c=R)"),
        Arguments.of(
            CardQuery.where()
                .color(isAtLeast(List.of(Color.WHITE, Color.BLUE)))
                .and()
                .color(isNot(Color.RED)),
            "c>=WU -c=R"));
  }
}

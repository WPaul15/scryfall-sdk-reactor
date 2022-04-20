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

import com.wpaul15.scryfall.api.model.Colors;
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
        Arguments.of(CardQuery.where().color(isExactly(Colors.BLUE, Colors.BLACK)), "c=UB"),
        Arguments.of(CardQuery.where().color(isLessThan(Colors.JESKAI)), "c<jeskai"),
        Arguments.of(CardQuery.where().color(isMoreThan(Colors.GREEN, Colors.WHITE)), "c>GW"),
        Arguments.of(CardQuery.where().color(isAtLeast(Colors.BLUE)), "c>=U"),
        Arguments.of(CardQuery.where().color(isAtMost(Colors.BLACK, Colors.RED)), "c<=BR"),
        Arguments.of(CardQuery.where().color(isNotExactly(Colors.BLUE, Colors.GREEN)), "c!=UG"),
        Arguments.of(CardQuery.where().color(isNot(Colors.MULTICOLORED)), "-c=M"),
        Arguments.of(CardQuery.where().color(isNot(not(Colors.COLORLESS))), "c=C"),
        Arguments.of(
            CardQuery.where().color(isExactlyOneOf(Colors.WHITE, Colors.BLUE, Colors.BLACK)),
            "(c=W or c=U or c=B)"),
        Arguments.of(
            CardQuery.where().color(isNot(exactlyOneOf(Colors.GREEN, Colors.RED))),
            "-(c=G or c=R)"),
        Arguments.of(
            CardQuery.where()
                .color(isAtLeast(List.of(Colors.WHITE, Colors.BLUE)))
                .and()
                .color(isNot(Colors.RED)),
            "c>=WU -c=R"),
        Arguments.of(CardQuery.where().colorIdentity(isAtMost(Colors.ESPER)), "id<=esper"));
  }
}

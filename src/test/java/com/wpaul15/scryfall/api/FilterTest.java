package com.wpaul15.scryfall.api;

import static com.wpaul15.scryfall.api.query.filter.Filters.atLeast;
import static com.wpaul15.scryfall.api.query.filter.Filters.atMost;
import static com.wpaul15.scryfall.api.query.filter.Filters.exactly;
import static com.wpaul15.scryfall.api.query.filter.Filters.exactlyOneOf;
import static com.wpaul15.scryfall.api.query.filter.Filters.lessThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.moreThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.not;
import static com.wpaul15.scryfall.api.query.filter.Filters.notExactly;
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
        Arguments.of(CardQuery.where().colorIs(exactly(Colors.BLUE, Colors.BLACK)), "c=UB"),
        Arguments.of(CardQuery.where().colorIs(lessThan(Colors.JESKAI)), "c<jeskai"),
        Arguments.of(CardQuery.where().colorIs(moreThan(Colors.GREEN, Colors.WHITE)), "c>GW"),
        Arguments.of(CardQuery.where().colorIs(atLeast(Colors.BLUE)), "c>=U"),
        Arguments.of(CardQuery.where().colorIs(atMost(Colors.BLACK, Colors.RED)), "c<=BR"),
        Arguments.of(CardQuery.where().colorIs(notExactly(Colors.BLUE, Colors.GREEN)), "c!=UG"),
        Arguments.of(CardQuery.where().colorIs(not(Colors.MULTICOLORED)), "-c=M"),
        Arguments.of(CardQuery.where().colorIs(not(not(Colors.COLORLESS))), "c=C"),
        Arguments.of(
            CardQuery.where().colorIs(exactlyOneOf(Colors.WHITE, Colors.BLUE, Colors.BLACK)),
            "(c=W or c=U or c=B)"),
        Arguments.of(
            CardQuery.where().colorIs(not(exactlyOneOf(Colors.GREEN, Colors.RED))),
            "-(c=G or c=R)"),
        Arguments.of(
            CardQuery.where()
                .colorIs(atLeast(List.of(Colors.WHITE, Colors.BLUE)))
                .and()
                .colorIs(not(Colors.RED)),
            "c>=WU -c=R"),
        Arguments.of(CardQuery.where().colorIdentityIs(atMost(Colors.ESPER)), "id<=esper"),
        Arguments.of(
            CardQuery.where()
                .colorIdentityIs(atMost(Colors.JUND))
                .and()
                .colorIs(not(Colors.MULTICOLORED)),
            "-c=M id<=jund"));
  }
}

package com.wpaul15.scryfall.api;

import static com.wpaul15.scryfall.api.query.CardQuery.where;
import static com.wpaul15.scryfall.api.query.filter.Filters.atLeast;
import static com.wpaul15.scryfall.api.query.filter.Filters.atMost;
import static com.wpaul15.scryfall.api.query.filter.Filters.exactly;
import static com.wpaul15.scryfall.api.query.filter.Filters.exactlyOneOf;
import static com.wpaul15.scryfall.api.query.filter.Filters.lessThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.moreThan;
import static com.wpaul15.scryfall.api.query.filter.Filters.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
        arguments(where(), ""),
        arguments(where().colorIs(exactly(Colors.BLUE, Colors.BLACK)), "c=UB"),
        arguments(where().colorIs(lessThan(Colors.JESKAI)), "c<jeskai"),
        arguments(where().colorIs(moreThan(Colors.GREEN, Colors.WHITE)), "c>GW"),
        arguments(where().colorIs(atLeast(Colors.BLUE)), "c>=U"),
        arguments(where().colorIs(atMost(Colors.BLACK, Colors.BLACK, Colors.RED)), "c<=BR"),
        arguments(where().colorIs(not(Colors.MULTICOLORED)), "-c=M"),
        arguments(where().colorIs(not(not(Colors.COLORLESS))), "c=C"),
        arguments(
            where().colorIs(exactlyOneOf(Colors.WHITE, Colors.WHITE, Colors.BLUE, Colors.BLACK)),
            "(c=W or c=U or c=B)"),
        arguments(where().colorIs(not(exactlyOneOf(Colors.GREEN, Colors.RED))), "-(c=G or c=R)"),
        arguments(
            where()
                .colorIs(atLeast(List.of(Colors.WHITE, Colors.BLUE)))
                .and()
                .colorIs(not(Colors.RED)),
            "c>=WU -c=R"),
        arguments(
            where().colorIdentityIs(atMost(Colors.ESPER)).and().typeIs("Artifact"),
            "t=\"Artifact\" id<=esper"),
        arguments(
            where()
                .colorIdentityIs(atMost(Colors.JUND))
                .and()
                .colorIs(not(Colors.MULTICOLORED))
                .and()
                .typeIs(exactlyOneOf("Creature", "Instant")),
            "-c=M (t=\"Creature\" or t=\"Instant\") id<=jund"),
        arguments(
            where().typeIs(not(exactlyOneOf("Enchantment", "Artifact"))),
            "-(t=\"Enchantment\" or t=\"Artifact\")"));
  }
}

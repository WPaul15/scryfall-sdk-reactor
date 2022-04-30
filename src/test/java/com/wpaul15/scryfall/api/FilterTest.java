package com.wpaul15.scryfall.api;

import static com.wpaul15.scryfall.api.query.CardQuery.where;
import static com.wpaul15.scryfall.api.query.Filters.allOf;
import static com.wpaul15.scryfall.api.query.Filters.anyOf;
import static com.wpaul15.scryfall.api.query.Filters.atLeast;
import static com.wpaul15.scryfall.api.query.Filters.atMost;
import static com.wpaul15.scryfall.api.query.Filters.exactly;
import static com.wpaul15.scryfall.api.query.Filters.lessThan;
import static com.wpaul15.scryfall.api.query.Filters.moreThan;
import static com.wpaul15.scryfall.api.query.Filters.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.wpaul15.scryfall.api.model.Colors;
import com.wpaul15.scryfall.api.model.Rarity;
import com.wpaul15.scryfall.api.query.CardQuery;
import com.wpaul15.scryfall.api.query.SearchKeywords;
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
        arguments(where().colorIdentityIs(Colors.BOROS).and().colorIs(Colors.RED), "c=R id=boros"),
        arguments(where().colorIs(exactly(Colors.BLUE, Colors.BLACK)), "c=UB"),
        arguments(where().colorIs(lessThan(Colors.JESKAI)), "c<jeskai"),
        arguments(where().colorIs(moreThan(Colors.GREEN, Colors.WHITE)), "c>GW"),
        arguments(where().colorIs(atLeast(Colors.BLUE)), "c>=U"),
        arguments(where().colorIs(atMost(Colors.BLACK, Colors.BLACK, Colors.RED)), "c<=BR"),
        arguments(where().colorIs(not(Colors.MULTICOLORED)), "-c=M"),
        arguments(where().colorIs(not(not(Colors.COLORLESS))), "c=C"),
        arguments(
            where().colorIs(anyOf(Colors.WHITE, Colors.WHITE, Colors.BLUE, Colors.BLACK)),
            "(c=W or c=U or c=B)"),
        arguments(where().colorIs(not(anyOf(Colors.GREEN, Colors.RED))), "-(c=G or c=R)"),
        arguments(
            where()
                .colorIs(atLeast(List.of(Colors.WHITE, Colors.BLUE)))
                .and()
                .colorIs(not(Colors.RED)),
            "c>=WU -c=R"),
        arguments(
            where().colorIdentityIs(atMost(Colors.ESPER)).and().typeIs("Artifact"),
            "t=Artifact id<=esper"),
        arguments(
            where()
                .colorIdentityIs(atMost(Colors.JUND))
                .and()
                .colorIs(not(Colors.MULTICOLORED))
                .and()
                .typeIs(anyOf("Creature", "Instant")),
            "-c=M (t=Creature or t=Instant) id<=jund"),
        arguments(
            where()
                .colorIdentityIs(atMost(Colors.MARDU))
                .and()
                .typeIs(allOf("Legendary", "Warrior")),
            "(t=Legendary t=Warrior) id<=mardu"),
        arguments(
            where().typeIs(not(anyOf("Enchantment", "Artifact"))),
            "-(t=Enchantment or t=Artifact)"),
        arguments(
            where().typeIs("Creature").and().powerIsZeroOrVariable().and().toughnessIs(atMost(3.0)),
            "t=Creature pow=* tou<=3.0"),
        arguments(
            where().typeIs("Planeswalker").and().loyaltyIs(lessThan(5)), "t=Planeswalker loy<5"),
        arguments(
            where()
                .cardIs(anyOf(SearchKeywords.FRENCH_VANILLA, SearchKeywords.BEAR))
                .and()
                .cardIs(SearchKeywords.FUNNY),
            "(is=frenchvanilla or is=bear) is=funny"),
        arguments(where().extrasAreIncluded(), "include:extras"),
        arguments(where().typeIs("Artifact").and().rarityIs(Rarity.COMMON), "r=common t=Artifact"),
        arguments(
            where().colorIs(anyOf(Colors.BLUE, Colors.GREEN)).and().rarityIs(atLeast(Rarity.RARE)),
            "r>=rare (c=U or c=G)"));
  }
}

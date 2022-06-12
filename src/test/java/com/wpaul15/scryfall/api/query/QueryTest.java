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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.wpaul15.scryfall.api.query.constants.Colors;
import com.wpaul15.scryfall.api.query.constants.PTL;
import com.wpaul15.scryfall.api.query.options.Printing;
import com.wpaul15.scryfall.api.query.options.SortDirection;
import com.wpaul15.scryfall.api.query.options.SortField;
import com.wpaul15.scryfall.api.query.options.Uniqueness;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
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
        arguments(findCardsWith().type(""), ""),
        arguments(findCardsWith().type("Human"), "t:Human"),
        arguments(findCardsWith().type("Human Warrior"), "t:\"Human Warrior\""),
        arguments(findCardsWith().type(not("Angel")), "-t:Angel"),
        arguments(findCardsWith().type(not(not("Demon"))), "t:Demon"),
        arguments(
            findCardsWith().type(anyOf("Instant", "Sorcery", "Enchantment")),
            "(t:Instant or t:Sorcery or t:Enchantment)"),
        arguments(
            findCardsWith().type(not(anyOf("Sorcery", "Instant"))), "-(t:Sorcery or t:Instant)"),
        arguments(
            findCardsWith().type(noneOf("Wizard", "Elemental")), "-(t:Wizard or t:Elemental)"),
        arguments(findCardsWith().color(lessThan(Colors.RED, Colors.WHITE, Colors.BLACK)), "c<RWB"),
        arguments(findCardsWith().color(greaterThan(Colors.GREEN, Colors.BLUE)), "c>GU"),
        arguments(
            findCardsWith().color(greaterThanOrEqualTo(Colors.BLACK, Colors.BLUE, Colors.BLACK)),
            "c>=BU"),
        arguments(findCardsWith().color(not(equalTo(Colors.BLACK))), "-c=B"),
        arguments(findCardsWith().color(anyOf(Colors.WHITE, Colors.COLORLESS)), "(c=W or c=C)"),
        arguments(
            findCardsWith().colorIdentity(lessThanOrEqualTo(Colors.GREEN, Colors.BLUE, Colors.RED)),
            "id<=GUR"),
        arguments(
            findCardsWith().colorIdentity(anyOf(Colors.SELESNYA, Colors.BLUE)),
            "(id=selesnya or id=U)"),
        arguments(findCardsWith().colorIndicator(), "has:indicator"),
        arguments(findCardsWith().oracleText(not("")), ""),
        arguments(findCardsWith().oracleText("mill"), "o:mill"),
        arguments(
            findCardsWith().oracleText(not("When ~ enters the battlefield")),
            "-o:\"When ~ enters the battlefield\""),
        arguments(findCardsWith().oracleText(anyOf("draw", "discard")), "(o:draw or o:discard)"),
        arguments(
            findCardsWith().fullOracleText("cannot be blocked except by"),
            "fo:\"cannot be blocked except by\""),
        arguments(findCardsWith().fullOracleText(not("you may pay")), "-fo:\"you may pay\""),
        arguments(
            findCardsWith().fullOracleText(anyOf("you may discard", "tap up to")),
            "(fo:\"you may discard\" or fo:\"tap up to\")"),
        arguments(findCardsWith().cmc(lessThanOrEqualTo(5)), "cmc<=5"),
        arguments(findCardsWith().cmc(not(equalTo(3))), "-cmc=3"),
        arguments(findCardsWith().cmc(anyOf(6, 7)), "(cmc=6 or cmc=7)"),
        arguments(findCardsWith().evenCmc(), "cmc:even"),
        arguments(findCardsWith().oddCmc(), "cmc:odd"),
        arguments(findCardsWith().manaProduced(equalTo(Colors.BLUE, Colors.WHITE)), "produces=UW"),
        arguments(findCardsWith().manaProduced(equalTo(Colors.MULTICOLORED)), "produces=M"),
        arguments(
            findCardsWith().manaProduced(anyOf(Colors.RAKDOS, Colors.GRIXIS)),
            "(produces=rakdos or produces=grixis)"),
        arguments(
            findCardsWith().manaProduced(greaterThanOrEqualTo(Colors.ABZAN)), "produces>=abzan"),
        arguments(findCardsWith().hybridManaSymbols(), "is:hybrid"),
        arguments(findCardsWith().phyrexianManaSymbols(), "is:phyrexian"),
        arguments(findCardsWith().power(lessThan(6.0)), "pow<6.0"),
        arguments(findCardsWith().power(not(greaterThan(2.0))), "-pow>2.0"),
        arguments(findCardsWith().power(anyOf(2.0, 3.0)), "(pow=2.0 or pow=3.0)"),
        arguments(findCardsWith().relativePower(greaterThan(PTL.TOUGHNESS)), "pow>tou"),
        arguments(findCardsWith().toughness(greaterThanOrEqualTo(4.0)), "tou>=4.0"),
        arguments(findCardsWith().toughness(anyOf(4.0, 5.0)), "(tou=4.0 or tou=5.0)"),
        arguments(findCardsWith().relativeToughness(lessThanOrEqualTo(PTL.POWER)), "tou<=pow"));
  }

  @ParameterizedTest
  @MethodSource("invalidFilterArguments")
  void invalidFiltersShouldThrowIllegalArgumentException(Callable<CardQuery> cardQueryFunction) {
    assertThatThrownBy(cardQueryFunction::call).isInstanceOf(IllegalArgumentException.class);
  }

  private static Stream<Arguments> invalidFilterArguments() {
    return Stream.of(
        arguments((Callable<CardQuery>) () -> findCardsWith().cmc(equalTo(-1))),
        arguments((Callable<CardQuery>) () -> findCardsWith().cmc(anyOf(2, -1))),
        arguments(
            (Callable<CardQuery>)
                () -> findCardsWith().relativePower(greaterThanOrEqualTo(PTL.POWER))),
        arguments(
            (Callable<CardQuery>) () -> findCardsWith().relativeToughness(equalTo(PTL.TOUGHNESS))));
  }
}

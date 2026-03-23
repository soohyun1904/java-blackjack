package domain.participant;

import domain.Card;
import domain.Name;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    private final BigDecimal betAmount = new BigDecimal("10000");

    @Test
    void 이름을_반환한다() {
        Player player = new Player(new Name("pobi"), betAmount);

        assertThat(player.name()).isEqualTo("pobi");
    }

    @Test
    void 블랙잭이면_배팅금의_1_5배를_받는다() {
        Player player = new Player(new Name("pobi"), betAmount);
        player.deal(List.of(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.HEART, Rank.KING)
        ));

        assertThat(player.profit()).isEqualByComparingTo(new BigDecimal("15000"));
    }

    @Test
    void 버스트면_배팅금을_잃는다() {
        Player player = new Player(new Name("pobi"), betAmount);
        player.deal(List.of(
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.TEN)
        ));
        player.draw(Card.of(Suit.DIAMOND, Rank.FIVE));

        assertThat(player.profit()).isEqualByComparingTo(new BigDecimal("-10000"));
    }

    @Test
    void 진행중에_profit을_호출하면_예외가_터진다() {
        Player player = new Player(new Name("pobi"), betAmount);
        player.deal(List.of(
                Card.of(Suit.HEART, Rank.TWO),
                Card.of(Suit.HEART, Rank.THREE)
        ));

        assertThatThrownBy(player::profit)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 진행중입니다.");
    }
}

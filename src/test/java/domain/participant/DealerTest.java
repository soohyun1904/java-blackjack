package domain.participant;

import domain.Card;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    void 이름은_딜러다() {
        Dealer dealer = new Dealer();

        assertThat(dealer.name()).isEqualTo("딜러");
    }

    @Test
    void 점수가_16이하면_카드를_더_받아야_한다() {
        Dealer dealer = new Dealer();
        dealer.deal(List.of(
                Card.of(Suit.HEART, Rank.EIGHT),
                Card.of(Suit.SPADE, Rank.EIGHT)
        ));

        assertThat(dealer.shouldDraw()).isTrue();
    }

    @Test
    void 점수가_17이상이면_카드를_받지_않아도_된다() {
        Dealer dealer = new Dealer();
        dealer.deal(List.of(
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.SEVEN)
        ));

        assertThat(dealer.shouldDraw()).isFalse();
    }

    @Test
    void 블랙잭이면_카드를_받지_않아도_된다() {
        Dealer dealer = new Dealer();
        dealer.deal(List.of(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.HEART, Rank.KING)
        ));

        assertThat(dealer.shouldDraw()).isFalse();
    }

    @Test
    void 버스트면_카드를_받지_않아도_된다() {
        Dealer dealer = new Dealer();
        dealer.deal(List.of(
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.SIX)
        ));
        dealer.draw(Card.of(Suit.DIAMOND, Rank.TEN));

        assertThat(dealer.shouldDraw()).isFalse();
    }
}

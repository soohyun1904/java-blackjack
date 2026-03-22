package domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
class HandTest {
    private Hand handWith(Card... cards) {
        return Hand.empty().draw(List.of(cards));
    }

    @Test
    void 카드_점수의_합을_반환하다(){
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.NINE),
                Card.of(Suit.HEART, Rank.TEN)
        );
        assertThat(hand.calculateScore()).isEqualTo(19);
    }

    @Test
    void 에이스는_합이_21_이하이면_11로_계산한다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.HEART, Rank.TEN)
        );
        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @Test
    void 에이스는_합이_21을_초과하면_1로_계산한다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.FIVE)
        );
        assertThat(hand.calculateScore()).isEqualTo(16);
    }

    @Test
    void 에이스_두_장이면_하나만_11로_계산한다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.SPADE, Rank.ACE)
        );
        assertThat(hand.calculateScore()).isEqualTo(12);
    }

    @Test
    void 두_장이_21이면_블랙잭이다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.ACE),
                Card.of(Suit.HEART, Rank.KING)
        );
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 세_장으로_21이면_블랙잭이_아니다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.SEVEN),
                Card.of(Suit.SPADE, Rank.SEVEN),
                Card.of(Suit.DIAMOND, Rank.SEVEN)
        );
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 합이_21을_초과하면_버스트다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.TEN),
                Card.of(Suit.DIAMOND, Rank.FIVE)
        );
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 합이_21_이하이면_버스트가_아니다() {
        Hand hand = handWith(
                Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.SPADE, Rank.TEN)
        );
        assertThat(hand.isBust()).isFalse();
    }
}
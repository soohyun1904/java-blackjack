package domain.state;

import domain.Card;
import domain.Hand;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class StateTest {
    private Hand handWith(Card... cards) {
        return Hand.empty().draw(List.of(cards));
    }

    @Nested
    class Hit_상태 {
        @Test
        void draw하면_Hit이_된다() {
            State state = new Hit(handWith(
                    Card.of(Suit.HEART, Rank.TWO),
                    Card.of(Suit.HEART, Rank.THREE)
            ));

            State next = state.draw(Card.of(Suit.HEART, Rank.FIVE));

            assertThat(next).isInstanceOf(Hit.class);
        }

        @Test
        void draw했는데_버스트면_Bust가_된다() {
            State state = new Hit(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.TEN)
            ));

            State next = state.draw(Card.of(Suit.DIAMOND, Rank.FIVE));

            assertThat(next).isInstanceOf(Bust.class);
        }

        @Test
        void stay하면_Stay가_된다() {
            State state = new Hit(handWith(
                    Card.of(Suit.HEART, Rank.TWO),
                    Card.of(Suit.HEART, Rank.THREE)
            ));

            State next = state.stay();

            assertThat(next).isInstanceOf(Stay.class);
        }

        @Test
        void isFinished는_false다() {
            State state = new Hit(handWith(
                    Card.of(Suit.HEART, Rank.TWO),
                    Card.of(Suit.HEART, Rank.THREE)
            ));

            assertThat(state.isFinished()).isFalse();
        }

        @Test
        void profit을_호출하면_예외가_터진다() {
            State state = new Hit(handWith(
                    Card.of(Suit.HEART, Rank.TWO),
                    Card.of(Suit.HEART, Rank.THREE)
            ));

            assertThatThrownBy(() -> state.profit(new BigDecimal("10000")))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아직 진행중입니다.");
        }
    }

    @Nested
    class Blackjack_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Blackjack(handWith(
                    Card.of(Suit.HEART, Rank.ACE),
                    Card.of(Suit.HEART, Rank.KING)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Blackjack(handWith(
                    Card.of(Suit.HEART, Rank.ACE),
                    Card.of(Suit.HEART, Rank.KING)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Blackjack(handWith(
                    Card.of(Suit.HEART, Rank.ACE),
                    Card.of(Suit.HEART, Rank.KING)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void 배팅금의_1_5배를_받는다() {
            State state = new Blackjack(handWith(
                    Card.of(Suit.HEART, Rank.ACE),
                    Card.of(Suit.HEART, Rank.KING)
            ));

            assertThat(state.profit(new BigDecimal("10000"))).isEqualByComparingTo(new BigDecimal("15000"));
        }
    }

    @Nested
    class Bust_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Bust(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.TEN),
                    Card.of(Suit.DIAMOND, Rank.FIVE)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Bust(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.TEN),
                    Card.of(Suit.DIAMOND, Rank.FIVE)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Bust(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.TEN),
                    Card.of(Suit.DIAMOND, Rank.FIVE)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void 배팅금을_잃는다() {
            State state = new Bust(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.TEN),
                    Card.of(Suit.DIAMOND, Rank.FIVE)
            ));

            assertThat(state.profit(new BigDecimal("10000"))).isEqualByComparingTo(new BigDecimal("-10000"));
        }
    }

    @Nested
    class Stay_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void profit을_호출하면_예외가_터진다() {
            Stay state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(() -> state.profit(new BigDecimal("10000")))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러와 비교가 필요합니다.");
        }

        @Test
        void 딜러보다_점수가_높으면_Win이_된다() {
            Stay state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.judge(16)).isInstanceOf(Win.class);
        }

        @Test
        void 딜러보다_점수가_낮으면_Lose가_된다() {
            Stay state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.judge(20)).isInstanceOf(Lose.class);
        }

        @Test
        void 딜러와_점수가_같으면_Draw가_된다() {
            Stay state = new Stay(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.judge(17)).isInstanceOf(Draw.class);
        }
    }

    @Nested
    class Win_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Win(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Win(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Win(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void 배팅금만큼_받는다() {
            State state = new Win(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.profit(new BigDecimal("10000"))).isEqualByComparingTo(new BigDecimal("10000"));
        }
    }

    @Nested
    class Lose_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Lose(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Lose(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Lose(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void 배팅금을_잃는다() {
            State state = new Lose(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.profit(new BigDecimal("10000"))).isEqualByComparingTo(new BigDecimal("-10000"));
        }
    }

    @Nested
    class Draw_상태 {
        @Test
        void draw하면_예외가_터진다() {
            State state = new Draw(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(() -> state.draw(Card.of(Suit.HEART, Rank.FOUR)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            State state = new Draw(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThatThrownBy(state::stay)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            State state = new Draw(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.isFinished()).isTrue();
        }

        @Test
        void 수익이_0이다() {
            State state = new Draw(handWith(
                    Card.of(Suit.HEART, Rank.TEN),
                    Card.of(Suit.SPADE, Rank.SEVEN)
            ));

            assertThat(state.profit(new BigDecimal("10000"))).isEqualByComparingTo(BigDecimal.ZERO);
        }
    }
}

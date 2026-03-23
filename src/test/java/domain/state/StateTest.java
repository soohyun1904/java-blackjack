package domain.state;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class StateTest {

    @Nested
    class Hit_상태 {
        @Test
        void draw하면_Hit이_된다() {
            assertThat(Hit.getInstance().draw(false)).isInstanceOf(Hit.class);
        }

        @Test
        void draw했는데_버스트면_Bust가_된다() {
            assertThat(Hit.getInstance().draw(true)).isInstanceOf(Bust.class);
        }

        @Test
        void stay하면_Stay가_된다() {
            assertThat(Hit.getInstance().stay()).isInstanceOf(Stay.class);
        }

        @Test
        void isFinished는_false다() {
            assertThat(Hit.getInstance().isFinished()).isFalse();
        }

        @Test
        void profit을_호출하면_예외가_터진다() {
            assertThatThrownBy(() -> Hit.getInstance().profit(new BigDecimal("10000")))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아직 진행중입니다.");
        }
    }

    @Nested
    class Blackjack_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Blackjack.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Blackjack.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Blackjack.getInstance().isFinished()).isTrue();
        }

        @Test
        void 배팅금의_1_5배를_받는다() {
            assertThat(Blackjack.getInstance().profit(new BigDecimal("10000")))
                    .isEqualByComparingTo(new BigDecimal("15000"));
        }
    }

    @Nested
    class Bust_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Bust.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Bust.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Bust.getInstance().isFinished()).isTrue();
        }

        @Test
        void 배팅금을_잃는다() {
            assertThat(Bust.getInstance().profit(new BigDecimal("10000")))
                    .isEqualByComparingTo(new BigDecimal("-10000"));
        }
    }

    @Nested
    class Stay_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Stay.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Stay.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Stay.getInstance().isFinished()).isTrue();
        }

        @Test
        void profit을_호출하면_예외가_터진다() {
            assertThatThrownBy(() -> Stay.getInstance().profit(new BigDecimal("10000")))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("딜러와 비교가 필요합니다.");
        }

        @Test
        void 딜러보다_점수가_높으면_Win이_된다() {
            assertThat(Stay.getInstance().judge(17, 16)).isInstanceOf(Win.class);
        }

        @Test
        void 딜러보다_점수가_낮으면_Lose가_된다() {
            assertThat(Stay.getInstance().judge(17, 20)).isInstanceOf(Lose.class);
        }

        @Test
        void 딜러와_점수가_같으면_Draw가_된다() {
            assertThat(Stay.getInstance().judge(17, 17)).isInstanceOf(Draw.class);
        }
    }

    @Nested
    class Win_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Win.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Win.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Win.getInstance().isFinished()).isTrue();
        }

        @Test
        void 배팅금만큼_받는다() {
            assertThat(Win.getInstance().profit(new BigDecimal("10000")))
                    .isEqualByComparingTo(new BigDecimal("10000"));
        }
    }

    @Nested
    class Lose_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Lose.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Lose.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Lose.getInstance().isFinished()).isTrue();
        }

        @Test
        void 배팅금을_잃는다() {
            assertThat(Lose.getInstance().profit(new BigDecimal("10000")))
                    .isEqualByComparingTo(new BigDecimal("-10000"));
        }
    }

    @Nested
    class Draw_상태 {
        @Test
        void draw하면_예외가_터진다() {
            assertThatThrownBy(() -> Draw.getInstance().draw(false))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void stay하면_예외가_터진다() {
            assertThatThrownBy(() -> Draw.getInstance().stay())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 종료된 상태입니다.");
        }

        @Test
        void isFinished는_true다() {
            assertThat(Draw.getInstance().isFinished()).isTrue();
        }

        @Test
        void 수익이_0이다() {
            assertThat(Draw.getInstance().profit(new BigDecimal("10000")))
                    .isEqualByComparingTo(BigDecimal.ZERO);
        }
    }
}

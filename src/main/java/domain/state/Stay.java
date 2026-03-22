package domain.state;

import domain.Card;
import domain.Hand;

import java.math.BigDecimal;

public class Stay extends Started {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public BigDecimal profit(BigDecimal betAmount) {
        throw new IllegalStateException("딜러와 비교가 필요합니다.");
    }

    public State judge(int dealerScore) {
        int myScore = score();
        if (myScore > dealerScore) return new Win(hand);
        if (myScore < dealerScore) return new Lose(hand);
        return new Draw(hand);
    }
}

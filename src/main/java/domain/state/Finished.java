package domain.state;

import domain.Card;
import domain.Hand;

import java.math.BigDecimal;

public abstract class Finished extends Started {
    protected Finished(Hand hand) {
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
        return betAmount.multiply(earningRate());
    }

    protected abstract BigDecimal earningRate();
}

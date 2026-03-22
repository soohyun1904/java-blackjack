package domain.state;

import domain.Hand;

import java.math.BigDecimal;

public abstract class Running extends Started {
    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public BigDecimal profit(BigDecimal betAmount) {
        throw new IllegalStateException("아직 진행중입니다.");
    }
}

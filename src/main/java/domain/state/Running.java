package domain.state;

import java.math.BigDecimal;

public abstract class Running implements State {

    @Override
    public State judge(int myScore, int dealerScore) {
        return this;
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

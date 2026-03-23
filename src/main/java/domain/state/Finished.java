package domain.state;

import java.math.BigDecimal;

public abstract class Finished implements State {

    @Override
    public State draw(boolean isBust) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public State judge(int myScore, int dealerScore) {
        return this;
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

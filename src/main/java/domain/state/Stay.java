package domain.state;

import java.math.BigDecimal;

public class Stay extends Finished {
    private Stay() {
    }

    private static class SingleInstanceHolder{
        private static final Stay INSTANCE = new Stay();
    }

    public static Stay getInstance(){
        return Stay.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public State judge(int myScore, int dealerScore) {
        if (myScore > dealerScore) {
            return Win.getInstance();
        }
        if (myScore < dealerScore) {
            return Lose.getInstance();
        }
        return Draw.getInstance();
    }

    @Override
    protected BigDecimal earningRate() {
        throw new IllegalStateException("딜러와 비교가 필요합니다.");
    }

    @Override
    public BigDecimal profit(BigDecimal betAmount) {
        throw new IllegalStateException("딜러와 비교가 필요합니다.");
    }
}

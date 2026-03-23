package domain.state;

import java.math.BigDecimal;

public class Lose extends Finished {
    private Lose() {
    }

    private static class SingleInstanceHolder{
        private static final Lose INSTANCE = new Lose();
    }

    public static Lose getInstance(){
        return Lose.SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE.negate();
    }
}

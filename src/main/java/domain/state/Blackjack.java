package domain.state;

import java.math.BigDecimal;

public class Blackjack extends Finished {
    private Blackjack() {
    }

    private static class SingleInstanceHolder{
        private static final Blackjack INSTANCE = new Blackjack();
    }

    public static Blackjack getInstance(){
        return SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected BigDecimal earningRate() {
        return new BigDecimal("1.5");
    }
}

package domain.state;

import domain.Hand;

import java.math.BigDecimal;

public class Lose extends Finished {
    public Lose(Hand hand) {
        super(hand);
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE.negate();
    }
}

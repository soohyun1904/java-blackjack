package domain.state;

import domain.Hand;

import java.math.BigDecimal;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE.negate();
    }
}

package domain.state;

import domain.Hand;

import java.math.BigDecimal;

public class Draw extends Finished {
    public Draw(Hand hand) {
        super(hand);
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ZERO;
    }
}

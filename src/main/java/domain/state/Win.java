package domain.state;

import domain.Hand;

import java.math.BigDecimal;

public class Win extends Finished {
    public Win(Hand hand) {
        super(hand);
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE;
    }
}

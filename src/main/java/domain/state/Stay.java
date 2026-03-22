package domain.state;

import domain.Hand;

public class Stay extends Finished{
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    protected double earningRate() {
        return 1;
    }
}

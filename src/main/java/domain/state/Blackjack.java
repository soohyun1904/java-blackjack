package domain.state;

import domain.Hand;

public class Blackjack extends Finished{
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    protected double earningRate() {
        return 1.5;
    }
}

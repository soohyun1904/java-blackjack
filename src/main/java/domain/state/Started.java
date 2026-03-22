package domain.state;

import domain.Hand;

public abstract class Started implements State{
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }
}

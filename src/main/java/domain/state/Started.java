package domain.state;

import domain.Card;
import domain.Hand;

import java.util.List;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State judge(int dealerScore) {
        return this;
    }

    @Override
    public int score() {
        return hand.calculateScore();
    }

    @Override
    public List<Card> cards() {
        return hand.cards();
    }
}

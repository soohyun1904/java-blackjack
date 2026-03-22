package domain.state;

import domain.Card;
import domain.Hand;

public class Hit extends Running{
    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        hand.draw(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}

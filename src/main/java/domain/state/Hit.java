package domain.state;

import domain.Card;
import domain.Hand;

public class Hit extends Running {
    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        Hand newHand = hand.draw(card);
        if (newHand.isBust()) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}

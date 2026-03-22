package domain.state;

import domain.Hand;

public class StateFactory {
    public static State init(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }
}

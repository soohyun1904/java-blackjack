package domain.state;

import domain.Card;
import domain.Hand;

public interface State {
    State draw(Card card);
    State stay();
    boolean isFinished();
    Hand hand();
    double profit(double betAmount);
}

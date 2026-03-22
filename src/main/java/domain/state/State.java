package domain.state;

import domain.Card;

import java.math.BigDecimal;
import java.util.List;

public interface State {
    State draw(Card card);
    State stay();
    boolean isFinished();
    BigDecimal profit(BigDecimal betAmount);
    int score();
    boolean isBust();
    List<Card> cards();
}

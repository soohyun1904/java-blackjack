package domain.state;

import java.math.BigDecimal;

public interface State {
    State draw(boolean isBust);
    State stay();
    State judge(int myScore, int dealerScore);
    boolean isFinished();
    BigDecimal profit(BigDecimal betAmount);
}

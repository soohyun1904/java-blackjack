package domain.state;

public class StateFactory {
    public static State init(boolean isBlackjack) {
        if (isBlackjack) {
            return Blackjack.getInstance();
        }
        return Hit.getInstance();
    }
}

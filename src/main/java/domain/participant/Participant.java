package domain.participant;

import domain.Card;
import domain.Hand;
import domain.Name;
import domain.state.State;
import domain.state.StateFactory;

import java.math.BigDecimal;
import java.util.List;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;

    private final Name name;
    private State state;

    protected Participant(Name name) {
        this.name = name;
    }

    public void deal(List<Card> cards) {
        Hand hand = Hand.empty().draw(cards);
        this.state = StateFactory.init(hand);
    }

    public void draw(Card card) {
        state = getState().draw(card);
    }

    public void stay() {
        state = getState().stay();
    }

    public boolean isFinished() {
        return getState().isFinished();
    }

    public int score() {
        return getState().score();
    }

    public boolean isBust() {
        return score() > BUST_THRESHOLD;
    }

    public void judge(int dealerScore) {
        state = getState().judge(dealerScore);
    }

    public List<Card> cards() {
        return getState().cards();
    }

    public String name() {
        return name.name();
    }

    protected BigDecimal profit(BigDecimal betAmount) {
        return getState().profit(betAmount);
    }

    private State getState() {
        if (state == null) {
            throw new IllegalStateException("카드가 배분되지 않았습니다.");
        }
        return state;
    }
}

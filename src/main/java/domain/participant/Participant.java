package domain.participant;

import domain.Card;
import domain.Hand;
import domain.Name;
import domain.state.State;
import domain.state.StateFactory;

import java.math.BigDecimal;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private Hand hand;
    private State state;

    protected Participant(Name name) {
        this.name = name;
    }

    public void deal(List<Card> cards) {
        hand = Hand.empty().draw(cards);
        state = StateFactory.init(hand.isBlackjack());
    }

    public void draw(Card card) {
        hand = hand.draw(card);
        state = getState().draw(hand.isBust());
    }

    public void stay() {
        state = getState().stay();
    }

    public void judge(int dealerScore) {
        state = getState().judge(score(), dealerScore);
    }

    public boolean isFinished() {
        return getState().isFinished();
    }

    public int score() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> cards() {
        return hand.cards();
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

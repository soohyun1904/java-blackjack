package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_DIFFERENCE = 10;
    private static final int BLACKJACK_THRESHOLD = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand empty() {
        return new Hand(new ArrayList<>());
    }

    public Hand draw(Card card) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public Hand draw(List<Card> cards) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.addAll(cards);
        return new Hand(newCards);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT
                && calculateScore() == BLACKJACK_THRESHOLD;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_THRESHOLD;
    }

    public int calculateScore() {
        int totalScore = sumCardScore();
        return adjustForAces(totalScore);
    }

    private int sumCardScore() {
        return cards.stream()
                .mapToInt(Card::rank)
                .sum();
    }

    private int adjustForAces(int totalScore) {
        int aceCount = aceCount();
        while (aceCount > 0 && totalScore > BLACKJACK_THRESHOLD) {
            totalScore -= ACE_DIFFERENCE;
            aceCount--;
        }
        return totalScore;
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}

package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final ShuffleStrategy strategy;

    private Deck(List<Card> cards, ShuffleStrategy strategy) {
        this.cards = new ArrayList<>(cards);
        this.strategy = strategy;
    }

    public static Deck createDeck(ShuffleStrategy strategy) {
        List<Card> cards = generateFullDeck();
        return new Deck(cards, strategy);
    }

    static Deck of(Card... cards) {
        return new Deck(new ArrayList<>(List.of(cards)), cards2 -> {});
    }

    private static List<Card> generateFullDeck(){
        return new ArrayList<>(Card.allCards());
    }

    public void shuffle(){
        strategy.shuffle(cards);
    }

    public List<Card> pop(int count) {
        if (count > remainingCount()) {
            throw new IllegalArgumentException("덱에 남은 카드보다 많은 수를 가져갑니다.");
        }
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card card = pop();
            result.add(card);
        }
        return result;
    }

    public Card pop(){
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 남은 카드가 없습니다.");
        }
        return cards.removeFirst();
    }

    public int remainingCount(){
        return cards.size();
    }
}

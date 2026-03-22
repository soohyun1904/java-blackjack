package domain;

import java.util.Arrays;
import java.util.List;

public class Card {
    private static final List<Card> ALL_CARD = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Rank.values())
                    .map(rank -> new Card(suit, rank)))
            .toList();

    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(Suit suit, Rank rank) {
        return ALL_CARD.stream()
                .filter(card -> card.rank == rank && card.suit == suit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 카드가 아닙니다."));
    }

    public static List<Card> allCards(){
        return ALL_CARD;
    }

    public String displayName(){
        return suit.getKoreanName() + rank.getSymbol();
    }

    public boolean isAce(){
        return this.rank.isAce();
    }

    public int rank(){
        return rank.getValue();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Card card)) return false;

        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}

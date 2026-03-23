package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    private Deck emptyDeck(){
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        while (deck.remainingCount() > 0) {
            deck.pop();
        }
        return deck;
    }

    @Test
    void  덱_생성_시_52장이다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        assertThat(deck.remainingCount()).isEqualTo(52);
    }

    @Test
    void pop하면_카드가_하나_줄어준다(){
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        deck.pop();
        assertThat(deck.remainingCount()).isEqualTo(51);
    }

    @Test
    void pop할_때_카드가_없으면_예외가_터진다() {
        Deck deck = emptyDeck();
        Assertions.assertThatThrownBy(deck::pop)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 남은 카드가 없습니다.");
    }

    @Test
    void 여러_장_pop하면_요청한_수만큼_카드를_반환한다(){
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        List<Card> cards = deck.pop(3);
        assertThat(cards.size()).isEqualTo(3);
    }

    @Test
    void pop할_때_남은_카드보다_많이_요청하면_예외가_터진다(){
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        assertThatThrownBy(() -> deck.pop(53))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 남은 카드보다 많은 수를 가져갑니다.");
    }
}
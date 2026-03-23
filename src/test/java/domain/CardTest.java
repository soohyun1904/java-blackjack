package domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void 전체_카드는_52장이다(){
        assertThat(Card.allCards().size()).isEqualTo(52);
    }

    @Test
    void 문양과_랭크로_카드를_조회할_수_있다() {
        Card card = Card.of(Suit.HEART, Rank.KING);
        assertThat(card).isNotNull();
    }

    @Test
    void 에이스_카드는_isAce가_참이다() {
        Card card = Card.of(Suit.COLVER, Rank.ACE);
        boolean ace = card.isAce();
        assertThat(ace).isTrue();
    }
}
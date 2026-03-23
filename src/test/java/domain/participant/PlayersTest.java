package domain.participant;

import domain.Card;
import domain.Name;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    private Player player(String name, String bet) {
        return new Player(new Name(name), new BigDecimal(bet));
    }

    private Players dealedPlayers(Player... players) {
        Players ps = new Players(List.of(players));
        Card ten = Card.of(Suit.HEART, Rank.TEN);
        Card seven = Card.of(Suit.SPADE, Rank.SEVEN);
        ps.deal(() -> List.of(ten, seven));
        return ps;
    }

    private void stayAll(Players players) {
        players.playTurns(p -> false, p -> {}, () -> null);
    }

    @Test
    void judge_딜러보다_높은_플레이어는_Win이다() {
        Player pobi = player("pobi", "10000");
        Players players = dealedPlayers(pobi);
        stayAll(players);

        players.judge(16);

        assertThat(pobi.profit()).isEqualByComparingTo(new BigDecimal("10000"));
    }

    @Test
    void judge_딜러보다_낮은_플레이어는_Lose다() {
        Player pobi = player("pobi", "10000");
        Players players = dealedPlayers(pobi);
        stayAll(players);

        players.judge(20);

        assertThat(pobi.profit()).isEqualByComparingTo(new BigDecimal("-10000"));
    }

    @Test
    void totalProfit은_플레이어_수익의_합이다() {
        Player pobi = player("pobi", "10000");
        Player jason = player("jason", "20000");
        Players players = dealedPlayers(pobi, jason);
        stayAll(players);

        players.judge(16);

        assertThat(players.totalProfit()).isEqualByComparingTo(new BigDecimal("30000"));
    }

    @Test
    void totalProfit은_무승부면_0이다() {
        Player pobi = player("pobi", "10000");
        Player jason = player("jason", "20000");
        Players players = dealedPlayers(pobi, jason);
        stayAll(players);

        players.judge(17);

        assertThat(players.totalProfit()).isEqualByComparingTo(BigDecimal.ZERO);
    }
}

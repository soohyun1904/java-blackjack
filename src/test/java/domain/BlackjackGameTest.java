package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {
    private static final BigDecimal BET = new BigDecimal("10000");

    private BlackjackGame game(Deck deck, Player... players) {
        return new BlackjackGame(new Players(List.of(players)), new Dealer(), deck);
    }

    @Test
    void deal_후_딜러와_플레이어는_2장을_가진다() {
        Player player = new Player(new Name("pobi"), BET);
        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TWO), Card.of(Suit.HEART, Rank.THREE),
                Card.of(Suit.SPADE, Rank.FOUR), Card.of(Suit.SPADE, Rank.FIVE)
        ), player);

        game.deal();

        assertThat(game.getDealer().cards()).hasSize(2);
        assertThat(player.cards()).hasSize(2);
    }

    @Test
    void 플레이어가_hit하면_카드가_추가된다() {
        Player player = new Player(new Name("pobi"), BET);
        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TWO), Card.of(Suit.HEART, Rank.THREE),
                Card.of(Suit.SPADE, Rank.FOUR), Card.of(Suit.SPADE, Rank.FIVE),
                Card.of(Suit.DIAMOND, Rank.TWO)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});

        assertThat(player.cards()).hasSize(2);
    }

    @Test
    void 플레이어가_버스트되면_진행이_끝난다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TWO), Card.of(Suit.HEART, Rank.THREE),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.HEART, Rank.TEN),
                Card.of(Suit.DIAMOND, Rank.FIVE)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> true, p -> {});

        assertThat(player.isBust()).isTrue();
        assertThat(player.isFinished()).isTrue();
    }

    @Test
    void 딜러는_16이하면_계속_카드를_받는다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.EIGHT), Card.of(Suit.SPADE, Rank.EIGHT),
                Card.of(Suit.DIAMOND, Rank.TEN), Card.of(Suit.DIAMOND, Rank.SEVEN),
                Card.of(Suit.COLVER, Rank.TEN)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});

        assertThat(game.getDealer().isBust()).isTrue();
    }

    @Test
    void 딜러가_17이상이면_카드를_받지_않는다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.HEART, Rank.SEVEN),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.SPADE, Rank.EIGHT)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});

        assertThat(game.getDealer().score()).isEqualTo(17);
        assertThat(game.getDealer().cards()).hasSize(2);
    }

    @Test
    void 플레이어가_딜러보다_높으면_Win이다() {
        Player player = new Player(new Name("pobi"), BET);
        // dealer: TEN+SEVEN=17, player: TEN+TEN=20
        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.HEART, Rank.SEVEN),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.DIAMOND, Rank.TEN)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});
        game.judge();

        assertThat(player.profit()).isEqualByComparingTo(BET);
    }

    @Test
    void 플레이어가_딜러보다_낮으면_Lose다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.DIAMOND, Rank.TEN),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.SPADE, Rank.SEVEN)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});
        game.judge();

        assertThat(player.profit()).isEqualByComparingTo(BET.negate());
    }

    @Test
    void 플레이어와_딜러_점수가_같으면_Draw다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.HEART, Rank.SEVEN),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.SPADE, Rank.SEVEN)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});
        game.judge();

        assertThat(player.profit()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void 딜러가_버스트면_Stay_플레이어는_Win이다() {
        Player player = new Player(new Name("pobi"), BET);

        BlackjackGame game = game(Deck.of(
                Card.of(Suit.HEART, Rank.EIGHT), Card.of(Suit.SPADE, Rank.EIGHT),
                Card.of(Suit.DIAMOND, Rank.TEN), Card.of(Suit.DIAMOND, Rank.SEVEN),
                Card.of(Suit.COLVER, Rank.TEN)
        ), player);

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});
        game.judge();

        assertThat(player.profit()).isEqualByComparingTo(BET);
    }

    @Test
    void 딜러수익은_플레이어_수익의_합을_음수로_반환한다() {
        Player pobi = new Player(new Name("pobi"), new BigDecimal("10000"));
        Player jason = new Player(new Name("jason"), new BigDecimal("20000"));

        BlackjackGame game = new BlackjackGame(new Players(List.of(pobi, jason)), new Dealer(), Deck.of(
                Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.DIAMOND, Rank.TEN),
                Card.of(Suit.SPADE, Rank.TEN), Card.of(Suit.HEART, Rank.SEVEN),
                Card.of(Suit.SPADE, Rank.NINE), Card.of(Suit.DIAMOND, Rank.NINE)
        ));

        game.deal();
        game.playPlayerTurns(p -> false, p -> {});
        game.playDealerTurn(() -> {});
        game.judge();

        assertThat(game.dealerProfit()).isEqualByComparingTo(new BigDecimal("30000"));
    }
}

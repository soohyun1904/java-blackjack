package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void deal() {
        dealer.deal(deck.pop(2));
        players.deal(() -> deck.pop(2));
    }

    public void playPlayerTurns(Function<Player, Boolean> hitDecision, Consumer<Player> onUpdate) {
        players.playTurns(hitDecision, onUpdate, deck::pop);
    }

    public void playDealerTurn(Runnable onDraw) {
        while (dealer.shouldDraw()) {
            dealer.draw(deck.pop());
            onDraw.run();
        }
    }

    public void judge() {
        players.judge(dealerScore());
    }

    private int dealerScore() {
        if (dealer.isBust()) {
            return 0;
        }
        return dealer.score();
    }

    public BigDecimal dealerProfit() {
        return players.totalProfit().negate();
    }

    public List<Player> getPlayers() {
        return players.getList();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

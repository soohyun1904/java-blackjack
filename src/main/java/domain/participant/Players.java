package domain.participant;

import domain.Card;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public void deal(Supplier<List<Card>> cardSupplier) {
        players.forEach(p -> p.deal(cardSupplier.get()));
    }

    public void playTurns(Function<Player, Boolean> hitDecision, Consumer<Player> onUpdate, Supplier<Card> cardSupplier) {
        players.forEach(p -> playTurn(p, hitDecision, onUpdate, cardSupplier));
    }

    private void playTurn(Player player, Function<Player, Boolean> hitDecision, Consumer<Player> onUpdate, Supplier<Card> cardSupplier) {
        while (!player.isFinished()) {
            act(player, hitDecision.apply(player), cardSupplier);
            onUpdate.accept(player);
        }
    }

    private void act(Player player, boolean hit, Supplier<Card> cardSupplier) {
        if (hit) {
            player.draw(cardSupplier.get());
            return;
        }
        player.stay();
    }

    public void judge(int dealerScore) {
        players.forEach(p -> p.judge(dealerScore));
    }

    public BigDecimal totalProfit() {
        return players.stream()
                .map(Player::profit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Player> getList() {
        return players;
    }
}

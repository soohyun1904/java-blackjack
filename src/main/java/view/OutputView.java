package view;

import domain.participant.Dealer;
import domain.participant.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::name)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println("딜러와 " + playerNames + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealer.cards().get(0).displayName());
        players.forEach(this::printPlayerCards);
    }

    public void printPlayerCards(Player player) {
        System.out.println(formatCards(player.name() + "카드", player.cards()));
    }

    public void printDealerDraw() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(formatCards("딜러 카드", dealer.cards()) + " - 결과: " + dealer.score());
        players.forEach(p ->
                System.out.println(formatCards(p.name() + "카드", p.cards()) + " - 결과: " + p.score()));
    }

    public void printProfits(BigDecimal dealerProfit, List<Player> players) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + format(dealerProfit));
        players.forEach(p -> System.out.println(p.name() + ": " + format(p.profit())));
    }

    private String formatCards(String label, List<domain.Card> cards) {
        return label + ": " + cards.stream()
                .map(domain.Card::displayName)
                .collect(Collectors.joining(", "));
    }

    private String format(BigDecimal amount) {
        return amount.stripTrailingZeros().toPlainString();
    }
}

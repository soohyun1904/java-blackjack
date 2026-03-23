package controller;

import domain.BlackjackGame;
import domain.Deck;
import domain.Name;
import domain.RandomShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = createPlayers();

        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        deck.shuffle();

        BlackjackGame game = new BlackjackGame(players, new Dealer(), deck);
        game.deal();
        outputView.printInitialDeal(game.getDealer(), game.getPlayers());

        game.playPlayerTurns(
                player -> inputView.readHitDecision(player.name()),
                outputView::printPlayerCards
        );

        game.playDealerTurn(outputView::printDealerDraw);

        game.judge();

        outputView.printFinalCards(game.getDealer(), game.getPlayers());
        outputView.printProfits(game.dealerProfit(), game.getPlayers());
    }

    private Players createPlayers() {
        List<String> names = inputView.readNames();
        List<Player> playerList = names.stream()
                .map(name -> new Player(new Name(name), inputView.readBetAmount(name)))
                .toList();
        return new Players(playerList);
    }
}

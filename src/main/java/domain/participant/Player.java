package domain.participant;

import domain.Name;

import java.math.BigDecimal;

public class Player extends Participant {
    private final BigDecimal betAmount;

    public Player(Name name, BigDecimal betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public BigDecimal profit() {
        return super.profit(betAmount);
    }
}

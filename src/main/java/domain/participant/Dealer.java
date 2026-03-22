package domain.participant;

import domain.Name;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean shouldDraw() {
        return !isFinished() && score() <= DRAW_THRESHOLD;
    }
}

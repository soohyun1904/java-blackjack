package domain.state;

import domain.Hand;

public abstract class Running extends Started{
   protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double betAmount) {
        throw new IllegalStateException("아직 진행중입니다.");
    }
}

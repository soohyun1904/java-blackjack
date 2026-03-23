package domain.state;

import java.math.BigDecimal;

public class Bust extends Finished {
    private Bust() {
    }

    private static class SingleInstanceHolder{
        private static final Bust INSTANCE = new Bust();
    }

    public static Bust getInstance(){
        return Bust.SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE.negate();
    }
}

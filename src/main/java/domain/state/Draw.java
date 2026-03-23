package domain.state;

import java.math.BigDecimal;

public class Draw extends Finished {
    private Draw() {
    }

    private static class SingleInstanceHolder{
        private static final Draw INSTANCE = new Draw();
    }

    public static Draw getInstance(){
        return Draw.SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ZERO;
    }
}

package domain.state;

import java.math.BigDecimal;

public class Win extends Finished {
    private Win() {
    }

    private static class SingleInstanceHolder{
        private static final Win INSTANCE = new Win();
    }

    public static Win getInstance(){
        return Win.SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected BigDecimal earningRate() {
        return BigDecimal.ONE;
    }
}

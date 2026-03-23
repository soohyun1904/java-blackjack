package domain.state;

public class Hit extends Running {
    private Hit() {
    }

    private static class SingleInstanceHolder{
        private static final Hit INSTANCE = new Hit();
    }

    public static Hit getInstance(){
        return Hit.SingleInstanceHolder.INSTANCE;
    }

    @Override
    public State draw(boolean isBust) {
        if (isBust) {
            return Bust.getInstance();
        }
        return Hit.getInstance();
    }

    @Override
    public State stay() {
        return Stay.getInstance();
    }
}

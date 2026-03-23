package config;

import controller.BlackjackController;
import view.InputView;
import view.OutputView;

public class AppConfig {
    public BlackjackController blackjackController(){
        return new BlackjackController(new InputView(), new OutputView());
    }
}

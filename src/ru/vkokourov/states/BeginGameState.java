package ru.vkokourov.states;

import ru.vkokourov.Game;

public class BeginGameState implements GameState {

    private static final String REGEX_YES_OR_NO = "[дн]";

    private final Game game;

    public BeginGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Начать игру?");
    }

    @Override
    public void suggest() {
        System.out.println("Введите Д(да) или Н(нет)");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches(REGEX_YES_OR_NO)) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        if (enter.equals("д")) {
            game.setState(game.getLaunchState());
        } else {
            game.setState(game.getQuitState());
        }
    }
}

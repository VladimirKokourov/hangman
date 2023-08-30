package ru.vkokourov.states;

import ru.vkokourov.game.Game;

public class BeginGameState implements GameState {

    private static final String REGEX_YES_OR_NO = "[дн]";
    private static final String SYMBOL_YES = "д";

    private final Game game;

    public BeginGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Угадай слово или тебя ждет ВИСИЛИЦА! Начать игру?\n");
    }

    @Override
    public void suggest() {
        System.out.println("Введите (Д) да или (Н) нет:");
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
        if (enter.equals(SYMBOL_YES)) {
            game.setState(game.getChooseModeState());
        } else {
            game.setState(game.getQuitState());
        }
    }
}

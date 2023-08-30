package ru.vkokourov.states;

import ru.vkokourov.game.Game;

public class QuitGameState implements GameState {

    private static final String REGEX_SURE_OR_NO = "[тн]";

    private final Game game;

    public QuitGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Вы точно уверены, что хотите выйти? ");
    }

    @Override
    public void suggest() {
        System.out.println("Введите Т(точно) или Н(нет)");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches(REGEX_SURE_OR_NO)) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        if (enter.equals("т")) {
            game.setGameOver(true);
        } else {
            game.setState(game.getPreviousState());
        }
    }
}

package ru.vkokourov.states;

import ru.vkokourov.game.Game;

public class MenuGameState implements GameState {

    private static final String REGEX_NUMS_MENU = "[1-4]";
    private static final String SYMBOL_FOR_SUGGEST = "1";
    private static final String SYMBOL_FOR_RESTART = "2";
    private static final String SYMBOL_FOR_QUIT = "3";
    private static final String SYMBOL_FOR_BACK = "4";

    private final Game game;

    public MenuGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Меню:");
        System.out.println("1) Открыть первую неизвестную букву");
        System.out.println("2) Рестарт");
        System.out.println("3) Выход");
        System.out.println("4) Назад\n");
    }

    @Override
    public void suggest() {
        System.out.println("Введите цифру (1), (2), (3) или (4):");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches(REGEX_NUMS_MENU)) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        switch (enter) {
            case SYMBOL_FOR_SUGGEST:
                game.getLaunchState().openFirstHiddenLetter();
                break;
            case SYMBOL_FOR_RESTART:
                game.setState(game.getBeginState());
                break;
            case SYMBOL_FOR_QUIT:
                game.setState(game.getQuitState());
                break;
            case SYMBOL_FOR_BACK:
                game.setState(game.getLaunchState());
                break;
        }
    }
}

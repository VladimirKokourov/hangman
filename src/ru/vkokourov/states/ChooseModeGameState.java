package ru.vkokourov.states;

import ru.vkokourov.game.Game;
import ru.vkokourov.game.GameMode;

public class ChooseModeGameState implements GameState {

    private static final String REGEX_NUMS = "[123]";
    private static final String SYMBOL_FOR_EASY = "1";
    private static final String SYMBOL_FOR_NORMAL = "2";
    private static final String SYMBOL_FOR_HARD = "3";

    private final Game game;

    public ChooseModeGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Выберите сложность игры:");
        System.out.println("1) Легко (слова с длиной до 5 букв)");
        System.out.println("2) Нормально (слова с длиной от 6 до 8 букв)");
        System.out.println("3) Жесткач (слова с длиной от 9 букв)");
        System.out.println();
    }

    @Override
    public void suggest() {
        System.out.println("Введите цифру от 1 до 3:");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches(REGEX_NUMS)) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        switch (enter) {
            case SYMBOL_FOR_EASY:
                game.setGameMode(GameMode.EASY);
                break;
            case SYMBOL_FOR_NORMAL:
                game.setGameMode(GameMode.MEDIUM);
                break;
            case SYMBOL_FOR_HARD:
                game.setGameMode(GameMode.HARD);
                break;
        }
        game.setState(game.getLaunchState());
    }
}

package ru.vkokourov.states;

import ru.vkokourov.gallows.Gallows;
import ru.vkokourov.game.Game;
import ru.vkokourov.words.HiddenWord;
import ru.vkokourov.words.WordList;

import java.util.HashSet;
import java.util.Set;

public class LaunchGameState implements GameState {

    private static final int NUMBER_OF_TRIES = 6;
    private static final String SYMBOL_FOR_MENU = "1";
    private static final String REGEX_LETTERS = "[а-яё" + SYMBOL_FOR_MENU + "]";

    private final Game game;
    private final WordList wordList;
    private final Gallows gallows;
    private final Set<String> mistakes;

    private HiddenWord word;

    public LaunchGameState(Game game) {
        this.game = game;
        wordList = new WordList();
        gallows = new Gallows();
        mistakes = new HashSet<>();
        prepare();
    }

    @Override
    public void printMessage() {
        printGallowsAndWord();
        if (getNumOfMistakes() > 0) {
            System.out.printf("Вы уже пробовали: %s.\n", mistakes);
        }
    }

    @Override
    public void suggest() {
        System.out.println("Введите букву кириллицей или цифру (1) для выхода в Меню:");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches(REGEX_LETTERS)) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        // it needs if we open first hidden letter from MenuGameState
        game.setState(game.getLaunchState());

        if (enter.equals(SYMBOL_FOR_MENU)) {
            game.setState(game.getMenuGameState());
        } else if (word.isGuessLetter(enter)) {
            word.addGuessLetter(enter);
            if (word.isGuessedWord()) {
                printGallowsAndWord();
                System.out.println("Мои поздравления! Вы победили!\n");
                game.setState(game.getBeginState());
            }
        } else {
            mistakes.add(enter);
            if (isLastTry()) {
                printGallowsAndWord();
                System.out.println("К сожалению Вы проиграли. Попробуйте еще раз!\n");
                game.setState(game.getBeginState());
            }
        }
    }

    public void prepare() {
        switch (game.getGameMode()) {
            case EASY:
                word = new HiddenWord(wordList.getRandomWord(0, 5));
                break;
            case MEDIUM:
                word = new HiddenWord(wordList.getRandomWord(6, 8));
                break;
            case HARD:
                word = new HiddenWord(wordList.getRandomWord(9, 30));
                break;
        }
        mistakes.clear();
    }

    public boolean isLastTry() {
        return getNumOfMistakes() == NUMBER_OF_TRIES;
    }

    public void openFirstHiddenLetter() {
        String letter = word.getFirstHiddenLetter();
        if (letter != null) {
            action(word.getFirstHiddenLetter());
        } else {
            System.out.println("В слове нет скрытых букв.");
        }
    }

    private void printGallowsAndWord() {
        gallows.draw(getNumOfMistakes());
        System.out.print("Слово: ");
        if (isLastTry()) {
            word.printWord();
        } else {
            word.printHiddenWord();
        }
    }

    private int getNumOfMistakes() {
        return mistakes.size();
    }
}

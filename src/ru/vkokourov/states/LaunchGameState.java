package ru.vkokourov.states;

import ru.vkokourov.Gallows;
import ru.vkokourov.Game;
import ru.vkokourov.HiddenWord;
import ru.vkokourov.WordList;

import java.util.HashSet;
import java.util.Set;

public class LaunchGameState implements GameState {

    private static final int NUMBER_OF_TRIES = 6;
    private static final String QUIT_SYMBOL = "q";
    private static final String REGEX_LETTERS = "[а-яё" + QUIT_SYMBOL + "]";

    private final Game game;
    private final WordList wordList;
    private final Gallows gallows;
    private final Set<String> mistakes;

    private HiddenWord word;

    public LaunchGameState(Game game) {
        this.game = game;
        wordList = new WordList();
        word = new HiddenWord(wordList.getRandomWord());
        gallows = new Gallows();
        mistakes = new HashSet<>();
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
        System.out.println("Введите букву кириллицей или Q для выхода из игры.");
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
        if (enter.equals(QUIT_SYMBOL)) {
            game.setState(game.getQuitState());
        } else if (word.isGuessLetter(enter)) {
            word.addGuessLetter(enter);
            if (word.isGuessedWord()) {
                printGallowsAndWord();
                System.out.println("Вы выиграли! Сыграйте еще!");
                game.setState(game.getBeginState());
            }
        } else {
            mistakes.add(enter);
            if (isLastTry()) {
                printGallowsAndWord();
                System.out.println("К сожалению Вы проиграли. Попробуйте еще раз!");
                game.setState(game.getBeginState());
            }
        }
    }

    public void prepare() {
        word = new HiddenWord(wordList.getRandomWord());
        mistakes.clear();
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

    public boolean isLastTry() {
        return getNumOfMistakes() == NUMBER_OF_TRIES;
    }
}

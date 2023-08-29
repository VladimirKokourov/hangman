package ru.vkokourov.states;

import ru.vkokourov.Gallows;
import ru.vkokourov.Game;
import ru.vkokourov.HiddenWord;
import ru.vkokourov.WordList;

import java.util.ArrayList;
import java.util.List;

public class LaunchGameState implements GameState {
    private static final int NUMBER_OF_TRIES = 6;

    private final Game game;
    private final WordList wordList;
    private final Gallows gallows;
    private final List<String> mistakes;
    private final HiddenWord word;

    public LaunchGameState(Game game) {
        this.game = game;
        wordList = new WordList();
        word = new HiddenWord(wordList.getRandomWord());
        gallows = new Gallows();
        mistakes = new ArrayList<>();
    }

    @Override
    public void printMessage() {
        if (word.isGuess() || isLastTry()) {
            word.setWord(wordList.getRandomWord());
            mistakes.clear();
        }
        printGallowsAndWord();
        if (getNumOfMistakes() > 0) {
            System.out.printf("Вы уже пробовали: %s.\n", mistakes);
        }
    }

    @Override
    public void suggest() {
        System.out.println("Введите букву кириллицей: ");
    }

    @Override
    public void validate(String enter) {
        if (!enter.matches("[а-яё]")) {
            System.out.println("Некорректный ввод.");
            game.scanEnter();
        }
    }

    @Override
    public void action(String enter) {
        if (word.isGuessLetter(enter)) {
            word.addGuessLetter(enter);
            if (word.isGuess()) {
                printGallowsAndWord();
                System.out.println("Вы выиграли! Сыграйте еще!");
                game.setState(game.getBeginState());
            }
        } else {
            if (!mistakes.contains(enter)) {
                mistakes.add(enter);
            }
            if (isLastTry()) {
                printGallowsAndWord();
                System.out.println("К сожалению Вы проиграли. Попробуйте еще раз!");
                game.setState(game.getBeginState());
            }
        }
    }

    private void printGallowsAndWord() {
        gallows.draw(getNumOfMistakes());
        System.out.print("Слово: ");
        if (isLastTry()) {
            word.printWholeWord();
        } else {
            word.print();
        }
    }

    private int getNumOfMistakes() {
        return mistakes.size();
    }

    public boolean isLastTry() {
        return getNumOfMistakes() == NUMBER_OF_TRIES;
    }
}

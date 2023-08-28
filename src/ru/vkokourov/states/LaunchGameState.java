package ru.vkokourov.states;

import ru.vkokourov.Gallows;
import ru.vkokourov.Game;
import ru.vkokourov.HiddenWord;
import ru.vkokourov.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaunchGameState implements GameState {
    private static final int NUMBER_OF_TRIES = 6;

    private final Game game;
    private final Gallows gallows;
    private final List<String> mistakes;

    private HiddenWord word;

    public LaunchGameState(Game game) {
        this.game = game;
        gallows = new Gallows();
        mistakes = new ArrayList<>();
    }

    @Override
    public void printMessage() {
        if (word == null || word.isGuess() || isLastTry()) {
            word = new HiddenWord();
            mistakes.clear();
        }
        printGallowsAndWord();
        if (getNumOfMistakes() > 0) {
            System.out.printf("Вы уже пробовали: %s.\n", mistakes);
        }
    }

    @Override
    public void scanEnter(Scanner scanner) {
        String enter;
        do {
            System.out.println("Введите букву кириллицей: ");
            enter = scanner.next().toLowerCase();
        } while (!Validator.isValidYesOrNo(enter));

        if (word.isGuessLetter(enter)) {
            if (mistakes.contains(enter)) {
                System.out.printf("Буква %s уже была", enter);
            } else {
                word.addGuessLetter(enter);
            }
            if (word.isGuess()) {
                printGallowsAndWord();
                System.out.println("Вы выиграли! Сыграйте еще!");
                game.setState(game.getBeginState());
            }
        } else {
            if (mistakes.contains(enter)) {
                System.out.printf("Буква %s уже была", enter);
            } else {
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
        word.print();
    }

    private int getNumOfMistakes() {
        return mistakes.size();
    }

    public boolean isLastTry() {
        return getNumOfMistakes() == NUMBER_OF_TRIES;
    }

}

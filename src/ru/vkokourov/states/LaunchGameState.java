package ru.vkokourov.states;

import ru.vkokourov.Gallows;
import ru.vkokourov.Game;
import ru.vkokourov.HiddenWord;
import ru.vkokourov.utils.Validator;

import java.util.Scanner;

public class LaunchGameState implements GameState {

    private final Game game;
    private final Gallows gallows;

    private HiddenWord word;

    public LaunchGameState(Game game) {
        this.game = game;
        gallows = new Gallows();
    }

    @Override
    public void printMessage() {
        if (word == null || word.isGuess() || game.isLastTry()) {
            word = new HiddenWord();
            game.getMistakes().clear();
        }
        gallows.draw(game.getNumOfMistakes());
        word.print();
        if (game.getNumOfMistakes() > 0) {
            System.out.printf("Вы уже пробовали: %s.\n", game.getMistakes().toString());
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
            word.addGuessLetter(enter);
            if (word.isGuess()) {
                System.out.println("Вы выиграли! Сыграйте еще!");
                game.setState(game.getBeginState());
            }
        } else {
            game.getMistakes().add(enter);
            if (game.isLastTry()) {
                System.out.println("К сожалению Вы проиграли. Попробуйте еще раз!");
                game.setState(game.getBeginState());
            }
        }
    }
}

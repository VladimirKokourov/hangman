package ru.vkokourov.states;

import ru.vkokourov.Gallows;
import ru.vkokourov.Game;
import ru.vkokourov.HiddenWord;
import ru.vkokourov.utils.Validator;

import java.util.Scanner;

public class LaunchGameState implements GameState {

    private final Game game;

    private HiddenWord word;
    private Gallows gallows;

    public LaunchGameState(Game game) {
        this.game = game;
        gallows = new Gallows();
    }

    @Override
    public void printMessage() {
        if (word == null) {
            word = new HiddenWord();
        }
        gallows.draw(game.getNumOfMistakes());
        word.draw();
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
                game.setState(game.getWinState());
            }
        } else {
            game.getMistakes().add(enter);
            if (game.isLastTry()) {
                game.setState(game.getLoseState());
            }
        }
    }
}

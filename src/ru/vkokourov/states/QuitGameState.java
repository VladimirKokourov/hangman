package ru.vkokourov.states;

import java.util.Scanner;

import ru.vkokourov.Game;
import ru.vkokourov.utils.Validator;

public class QuitGameState implements GameState {

    private final Game game;

    public QuitGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.print("Вы уверены, что хотите выйти? ");
    }

    @Override
    public void scanEnter(Scanner scanner) {
        String enter;
        do {
            System.out.println("Введите Y или N");
            enter = scanner.next().toLowerCase();
        } while (Validator.isValidYesOrNo(enter));

        if (enter.equals("y")) {
            game.setGameOver(true);
        } else {
            game.setState(game.getPreviousState());
        }
    }
}

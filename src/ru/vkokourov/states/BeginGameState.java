package ru.vkokourov.states;

import java.util.Scanner;

import ru.vkokourov.Game;
import ru.vkokourov.utils.Validator;

public class BeginGameState implements GameState {
    private final Game game;

    public BeginGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.print("Начать игру? (y/n)");
    }

    @Override
    public void scanEnter(Scanner scanner) {
        String enter;
        do {
            System.out.println("Введите Y или N");
            enter = scanner.next().toLowerCase();
        } while (!Validator.isValidYesOrNo(enter));

        if (enter.equals("y")) {
            game.setState(game.getLaunchState());
        } else {
            game.setState(game.getQuitState());
        }
    }
}

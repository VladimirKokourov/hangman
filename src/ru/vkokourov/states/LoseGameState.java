package ru.vkokourov.states;

import ru.vkokourov.Game;

import java.util.Scanner;

public class LoseGameState implements GameState {

    private Game game;

    public LoseGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("К сожалению Вы проиграли. Попробуйте еще раз!");
    }

    @Override
    public void scanEnter(Scanner scanner) {
        game.setState(game.getBeginState());
    }
}

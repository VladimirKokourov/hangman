package ru.vkokourov.states;

import ru.vkokourov.Game;

import java.util.Scanner;

public class WinGameState implements GameState {

    private Game game;

    public WinGameState(Game game) {
        this.game = game;
    }

    @Override
    public void printMessage() {
        System.out.println("Вы выиграли! Сыграйте еще!");
    }

    @Override
    public void scanEnter(Scanner scanner) {
        game.setState(game.getBeginState());
    }
}

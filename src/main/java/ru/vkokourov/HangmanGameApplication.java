package ru.vkokourov;

import ru.vkokourov.game.Game;

public class HangmanGameApplication {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGameLoop();
    }
}

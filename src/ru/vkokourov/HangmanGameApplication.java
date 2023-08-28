package ru.vkokourov;

public class HangmanGameApplication {

    public static void main(String[] args) {
	    Game game = new Game();

        while (!game.isGameOver()) {
            game.printMessage();
            game.scanEnter();
        }
    }
}

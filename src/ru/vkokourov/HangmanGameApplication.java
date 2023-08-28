package ru.vkokourov;

public class HangmanGameApplication {

    public static void main(String[] args) {
        Data data = new Data();
        Dispay dispay = new Display();
	    Game game = new Game(data, dispay);

        game.start();
    }
}

package ru.vkokourov;

import ru.vkokourov.states.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static final int NUMBER_OF_TRIES = 6;

    private Scanner scanner;

    private List<String> mistakes;
    private boolean isGameOver;
    private BeginGameState beginState;
    private LaunchGameState launchState;
    private WinGameState winState;
    private LoseGameState loseState;
    private QuitGameState quitState;

    private GameState previousState;
    private GameState state;

    public Game() {
        this.scanner = new Scanner(System.in);
        mistakes = new ArrayList<>();
        isGameOver = false;
        beginState = new BeginGameState(this);
        launchState = new LaunchGameState(this);
        winState = new WinGameState(this);
        loseState = new LoseGameState(this);
        quitState = new QuitGameState(this);
        state = beginState;
    }

    public void printMessage() {
        state.printMessage();
    }

    public void scanEnter() {
        state.scanEnter(scanner);
    }

    public boolean isLastTry() {
        return getNumOfMistakes() == NUMBER_OF_TRIES;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setState(GameState state) {
        previousState = this.state;
        this.state = state;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public BeginGameState getBeginState() {
        return beginState;
    }

    public LaunchGameState getLaunchState() {
        return launchState;
    }

    public QuitGameState getQuitState() {
        return quitState;
    }

    public GameState getPreviousState() {
        return previousState;
    }

    public WinGameState getWinState() {
        return winState;
    }

    public LoseGameState getLoseState() {
        return loseState;
    }

    public List<String> getMistakes() {
        return mistakes;
    }

    public int getNumOfMistakes() {
        return mistakes.size();
    }
}

package ru.vkokourov;

import ru.vkokourov.states.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Scanner scanner;

    private final BeginGameState beginState;
    private final LaunchGameState launchState;
    private final QuitGameState quitState;

    private boolean isGameOver;
    private GameState previousState;
    private GameState state;

    public Game() {
        this.scanner = new Scanner(System.in);
        beginState = new BeginGameState(this);
        launchState = new LaunchGameState(this);
        quitState = new QuitGameState(this);
        isGameOver = false;
        state = beginState;
    }

    public void startGameLoop() {
        while (!isGameOver()) {
            printMessage();
            scanEnter();
        }
    }

    public void printMessage() {
        state.printMessage();
    }

    public void scanEnter() {
        state.scanEnter(scanner);
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

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}

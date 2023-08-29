package ru.vkokourov;

import ru.vkokourov.states.BeginGameState;
import ru.vkokourov.states.GameState;
import ru.vkokourov.states.LaunchGameState;
import ru.vkokourov.states.QuitGameState;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private final BeginGameState beginState;
    private LaunchGameState launchState;
    private QuitGameState quitState;
    private String enter;
    private boolean isGameOver;
    private GameState previousState;
    private GameState state;

    public Game() {
        this.scanner = new Scanner(System.in);
        beginState = new BeginGameState(this);
        isGameOver = false;
        state = beginState;
    }

    public void startGameLoop() {
        while (!isGameOver()) {
            printMessage();
            scanEnter();
            action();
        }
    }

    private void printMessage() {
        state.printMessage();
    }

    public void scanEnter() {
        state.suggest();
        enter = scanner.next().toLowerCase();
        validate();
    }

    private void validate() {
        if (enter.length() > 1) {
            System.out.println("Введено больше одного символа.");
            scanEnter();
        }
        state.validate(enter);
    }

    private void action() {
        state.action(enter);
    }

    public void setState(GameState state) {
        previousState = this.state;
        this.state = state;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public BeginGameState getBeginState() {
        return beginState;
    }

    public LaunchGameState getLaunchState() {
        return launchState == null ? new LaunchGameState(this) : launchState;
    }

    public QuitGameState getQuitState() {
        return quitState == null ? new QuitGameState(this) : quitState;
    }

    public GameState getPreviousState() {
        return previousState;
    }
}

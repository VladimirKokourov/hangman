package ru.vkokourov.game;

import ru.vkokourov.states.*;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private final BeginGameState beginState;

    private ChooseModeGameState chooseModeState;

    private LaunchGameState launchState;
    private QuitGameState quitState;
    private String enter;
    private boolean isGameOver;
    private GameState previousState;
    private GameState state;
    private GameMode gameMode;

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

    public ChooseModeGameState getChooseModeState() {
        return chooseModeState == null ? new ChooseModeGameState(this) : chooseModeState;
    }

    public BeginGameState getBeginState() {
        return beginState;
    }

    public LaunchGameState getLaunchState() {
        if (launchState == null) {
            return new LaunchGameState(this);
        } else {
            launchState.prepare();
            return launchState;
        }
    }

    public QuitGameState getQuitState() {
        return quitState == null ? new QuitGameState(this) : quitState;
    }

    public GameState getPreviousState() {
        return previousState;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}

package ru.vkokourov.game;

import ru.vkokourov.states.*;

import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private final BeginGameState beginState;

    private ChooseModeGameState chooseModeState;
    private LaunchGameState launchState;
    private MenuGameState menuGameState;
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

    public BeginGameState getBeginState() {
        return beginState;
    }

    public ChooseModeGameState getChooseModeState() {
        if (chooseModeState == null) {
            chooseModeState = new ChooseModeGameState(this);
        }
        return chooseModeState;
    }

    public LaunchGameState getLaunchState() {
        if (launchState == null) {
            launchState = new LaunchGameState(this);
        } else if (state.equals(chooseModeState)) {
            launchState.prepare();
        }
        return launchState;
    }

    public MenuGameState getMenuGameState() {
        if (menuGameState == null) {
            menuGameState = new MenuGameState(this);
        }
        return menuGameState;
    }

    public QuitGameState getQuitState() {
        if (quitState == null) {
            quitState = new QuitGameState(this);
        }
        return quitState;
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

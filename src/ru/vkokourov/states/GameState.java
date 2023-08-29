package ru.vkokourov.states;

public interface GameState {

    void printMessage();

    void suggest();

    void validate(String enter);

    void action(String enter);
}

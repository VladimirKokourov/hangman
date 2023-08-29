package ru.vkokourov.states;

import java.util.Scanner;

public interface GameState {

    void printMessage();

    void scanEnter();

    void validate(String enter);

    void action(String enter);
}

package ru.vkokourov;

import java.util.ArrayList;
import java.util.List;

public class HiddenWord {
    public static final String HIDDEN = "*";

    private List<String> letters;
    private List<Integer> numbersOfGuessLetters;

    public HiddenWord() {
        this.letters = List.of("цивилизация".split(""));
        numbersOfGuessLetters = new ArrayList<>();
    }

    public void draw() {
        for (int i = 0; i < letters.size(); i++) {
            if (numbersOfGuessLetters.contains(i)) {
                System.out.print(letters.get(i));
            } else {
                System.out.print(HIDDEN);
            }
        }
        System.out.println();
    }

    public boolean isGuessLetter(String enter) {
        return letters.contains(enter);
    }

    public void addGuessLetter(String enter) {
        for (int i = 0; i < letters.size(); i++) {
            if (enter.equals(letters.get(i))) {
                numbersOfGuessLetters.add(i);
            }
        }
    }

    public boolean isGuess() {
        return letters.size() == numbersOfGuessLetters.size();
    }

    public List<String> getLetters() {
        return letters;
    }

    public List<Integer> getNumbersOfGuessLetters() {
        return numbersOfGuessLetters;
    }
}

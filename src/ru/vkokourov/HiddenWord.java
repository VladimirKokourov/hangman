package ru.vkokourov;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HiddenWord {
    public static final String HIDDEN = "*";

    private String word;
    private final List<String> letters;
    private final Set<Integer> numbersOfGuessLetters;

    public HiddenWord(String word) {
        this.word = word;
        letters = List.of(word.split(""));
        numbersOfGuessLetters = new HashSet<>();
    }

    public void print() {
        for (int i = 0; i < letters.size(); i++) {
            if (numbersOfGuessLetters.contains(i)) {
                System.out.print(letters.get(i));
            } else {
                System.out.print(HIDDEN);
            }
        }
        System.out.println();
    }

    public void printWholeWord() {
        System.out.println(word);
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

    public void setWord(String word) {
        this.word = word;
    }
}

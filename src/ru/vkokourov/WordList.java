package ru.vkokourov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    private final List<String> words;

    public WordList() {
        this.words = new ArrayList<>();
        fillWords();
    }

    private void fillWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            e.printStackTrace();
        }
    }

    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}

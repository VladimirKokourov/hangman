package ru.vkokourov.words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordList {

    protected final List<String> words;

    public WordList() {
        this.words = new ArrayList<>();
        fillWords();
    }

    private void fillWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains("-")) {
                    words.add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            e.printStackTrace();
        }
    }

    public String getRandomWord(int minLength, int maxLength) {
        Random random = new Random();
        List<String> easyWords = words.stream()
                .filter(w -> w.length() >= minLength && w.length() <= maxLength)
                .collect(Collectors.toList());
        int randomIndex = random.nextInt(easyWords.size());
        return easyWords.get(randomIndex);
    }
}

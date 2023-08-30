package ru.vkokourov;

public class Gallows {

    public static final String TOP = " _____\n/    |\n|    |";
    public static final String MIDDLE = "|\n|\n|";
    public static final String MIDDLE_ONE = "|    O\n|\n|";
    public static final String MIDDLE_TWO = "|    O\n|    |\n|";
    public static final String MIDDLE_THREE = "|    O\n|   /|\n|";
    public static final String MIDDLE_FOUR = "|    O\n|   /|\\\n|";
    public static final String MIDDLE_FIVE = "|    O\n|   /|\\\n|   /";
    public static final String MIDDLE_SIX = "|    O\n|   /|\\\n|   / \\";
    public static final String BOTTOM = "|\n|_________";

    public void draw(int numOfMistakes) {
        System.out.println(TOP);
        System.out.println(getMiddle(numOfMistakes));
        System.out.println(BOTTOM);
    }

    private String getMiddle(int numOfMistakes) {
        switch (numOfMistakes) {
            case 1:
                return MIDDLE_ONE;
            case 2:
                return MIDDLE_TWO;
            case 3:
                return MIDDLE_THREE;
            case 4:
                return MIDDLE_FOUR;
            case 5:
                return MIDDLE_FIVE;
            case 6:
                return MIDDLE_SIX;
            default:
                return MIDDLE;
        }
    }
}

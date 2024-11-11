package util;

import java.util.Random;

public class GenerateRandomWordsArr {

    Random rnd = new Random();

    public String[] getArray(int amount) {
        String[] array = new String[amount];

        for (int i = 0; i < amount; i++) array[i] = getWord();

        return array;
    }

    String getWord() {
        int len = rnd.nextInt(5, 15);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) rnd.nextInt(97, 123));
        }
        return sb.toString();
    }
}

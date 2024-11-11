package OAHashTable.util;

import java.util.Random;

public class DraftHT {
    final char[] LETTERS = new char[]{
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106,
            107, 108, 109, 110, 111, 112, 113, 114, 115, 116,
            117, 118, 119, 120, 121, 122, 65, 66, 67, 68,
            69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
            79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    final int LIM = LETTERS.length;

    public String[] getWordPack(int packSize) {
        Random rnd = new Random();
        String[] wordsPack = new String[packSize];

        for (int i = 0; i < wordsPack.length; i++)
            wordsPack[i] = lPack(8 + rnd.nextInt(7));

        return wordsPack;
    }

    String lPack(int len) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(LETTERS[rnd.nextInt(LIM)]);
        }
        return sb.toString();
    }
}

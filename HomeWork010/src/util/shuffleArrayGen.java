package util;

import java.util.Random;

public class shuffleArrayGen {


    static public int[] setRandom(int len, int lim) {
        Random rnd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = 10 + rnd.nextInt(lim - 10);
        }
        return arr;
    }

    static public int[] setAscending(int len) {
        return setAscending(len, 0);
    }
    static public int[] setAscending(int len, int start) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = start + i;
        return arr;
    }


    static public int[] setShuffle(int len) {
        return setShuffle(len, 0);
    }


    static public int[] setShuffle(int len, int start) {
        Random rnd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = start + i;
            if (i > 0) {
                int k = rnd.nextInt(i);
                int tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
            }

        }
        return arr;
    }
}

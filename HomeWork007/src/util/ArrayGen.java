package util;

import java.util.Random;

public class ArrayGen {

    static int SEED = 12345;


    public static Integer[] getArray(int length, String arrType) {
        switch (arrType) {
            case "Random": return setRandom(length);
            case "Digits": return setRandom(length, true);
            case "Sorted": return setSorted(length);
            case "Reversed": return setReverse(length);
            default:
                throw new IllegalArgumentException("Missing array type: Random/Digits/Sorted/Reversed");
        }
    }

    /**
     * Build int[] array of random numbers or digits
     */
    public static Integer[] setRandom(int length, boolean digits) {
        Random rnd = new Random(SEED);
        Integer[] array = new Integer[length];
        int maxNum = digits ? 9 : length;
        for (int i = 0; i < length; i++) {
            array[i] = rnd.nextInt(maxNum);
        }
        return array;
    }

    /**
     * Build int array of random numbers
     */
    public static Integer[] setRandom(int length) {
        return setRandom(length, false);
    }

    /**
     * Build sorted (99%) int array of random numbers
     */
    public static Integer[] setSorted(int length) {
        Random rnd = new Random(SEED);
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        int unsorted = length > 100 ? (int) (0.01 * length) : length > 10 ? (int) (0.05 * length) : (int) (0.2 * length);
        for (int i = 0; i < unsorted; i++) {
            int j = rnd.nextInt(length);
            int tmp = array[j];
            int k = rnd.nextInt(length);
            array[j] = array[k];
            array[k] = tmp;

        }
        return array;
    }

    /**
     * Build reverse int array
     */
    public static Integer[] setReverse(int lenght) {
        Integer[] array = new Integer[lenght];
        for (int i = 0; i < lenght; i++) {
            array[i] = lenght - i;
        }
        return array;
    }
}

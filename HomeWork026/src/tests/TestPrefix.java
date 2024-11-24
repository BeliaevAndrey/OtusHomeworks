package tests;

import hw.PrefixFunctions;
import hw.KMP;

import java.util.Arrays;
import java.util.Random;

public class TestPrefix {

    public static void main(String[] args) {

        String testText = "abcabcd";
        KMP kmp = new KMP();
        kmp.createPi(testText);
        int[] res1 = kmp.getPi();

        System.out.printf("res1: %d  %s\n", res1.length, Arrays.toString(res1));

        PrefixFunctions pf = new PrefixFunctions();

        int[] res2 = pf.prefixFunctionSlow(testText);
        System.out.printf("res3: %d  %s\n", res2.length, Arrays.toString(res2));

        int[] res3 = pf.prefixFunctionFast(testText);
        System.out.printf("res3: %d  %s\n", res3.length, Arrays.toString(res3));



    }



    // ========================================================================
    // ========================================================================
    // region unnecessary

    String genText(int len) {
        Random rnd = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < len; i++) {
            text.append((char)(65 + rnd.nextInt(26)));
        }
        return text.toString();
    }

    String genABC() {
        Random rnd = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            System.out.println(i);
            text.append((char)(65 + i));
        }
        return text.toString();
    }

    void printABC() {
        for (int i = 0; i < 26; i++) {
            System.out.print((char)(65 + i));
        }
    }
    // endregion

}

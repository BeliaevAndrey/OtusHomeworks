package hw;

import java.util.Arrays;

public class PrefixFunctions {



    public int[] prefixFunctionSlow(String s) {
        char[] str = s.toCharArray();
        int[] p = new int[str.length + 1];  // prefix array
        for (int i = 0; i < str.length; i++)
            for (int k = 0; k < i; k++) {
                if (s.substring(0, k).equals(s.substring(i - k, i))) {
                    p[i] = k;
                }

            }
        return p;
    }


    public int[] prefixFunctionFast(String s) {
        char[] str = s.toCharArray();

        int[] p = new int[s.length() + 1];

        for (int i = 1; i < str.length; i++) {

            int k = p[i];

            while (k > 0 && str[i] != str[k]) k = p[k];

            if (str[i] == str[k]) k++;

            p[i + 1] = k;
        }

        return p;
    }

}

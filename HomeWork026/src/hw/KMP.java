package hw;

public class KMP {
/*
     pi | q |
    ----|---|------------------
     0  | 0 |    (+A)
     0  | 1 | A    (+B)
     0  | 2 | A B    (+A)
     1  | 3 | A B A    (+B)
     2  | 4 | A B A B    (+C)
     0  | 5 | A B A B C


 */

    int[] pi; //    pi-function (pi-table)


    public void createPi(String pattern) {
        pi = new int[pattern.length() + 1];

        pi[0] = 0;
        pi[1] = 0;
        for (int q = 1; q < pattern.length(); q++) {
            int len = pi[q];

            while (len > 0 && pattern.charAt(len) != pattern.charAt(q)) len = pi[len];

            if (pattern.charAt(len) == pattern.charAt(q)) len++;

            pi[q + 1] = len;
        } // O(P)

    }

    public int[] getPi() {return pi;}

    public int getPatternLen() {
        int max = -1;
        for (int i : pi) max = i > max ? i : max;
        return max;
    }
}

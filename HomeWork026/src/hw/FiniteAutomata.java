package hw;

public class FiniteAutomata {

    private final int length;
    private final int alphaLen = 127;
    int[][] delta;  // table of shifts (states switcher)

    public FiniteAutomata(String pattern) {
        length = pattern.length();
        createDelta(pattern);
    }

    private void createDelta(String pattern) {
        delta = new int[length + 1][alphaLen];
        for (int q = 0; q <= length; q++) {
            for (char c = 0; c < alphaLen; c++) {
                int k = q + 1;
                if (q == length) k--;

                String line = left(pattern, q) + c;

                while (!left(pattern, k).equals(right(line, k))) k--;
                delta[q][c] = k;
            }
        }
    }

    public int search(String text) {
        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            q = delta[q][text.charAt(i)];
            if (q == length) return i - length + 1;
        }
        return -1;
    }

    public int[][] getDelta() { return delta; }

    public int getLength() { return length; }


    // region srv

    private String left(String line, int x) {return line.substring(0, x);}

    private String right(String line, int x) {return line.substring(line.length() - x);}

    // endregion
}

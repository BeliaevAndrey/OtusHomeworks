package substringSearch;

public class FullIterativeSearchReverse implements ISearcher {

    public final String header = "Iterative search suffix shift algorithm";

    private int compares;
    private String text;
    private String mask;
    private int lenText;
    private int lenMask;

    @Override
    public void init(String string, String substring) {
        text = string;
        mask = substring;
        lenText = text.length();
        lenMask = mask.length();
    }

    @Override
    public int search() {
        compares = 0;

        for (int i = lenMask; i <= lenText; i++) {
            int pos = i - 1, j = lenMask - 1;
            for (; j >= 0; j--, pos--) {
                if (!compare(text.charAt(pos), mask.charAt(j))) {
                    i += lenMask - j - 1;               // shift by suffix length
                    break;
                }
            }
            if (j < 0) return i - lenMask;
        }
        return -1;
    }

    boolean compare(char l, char r) {
        compares++;
        return l == r;
    }

    @Override
    public void prepare() {}

    @Override
    public int getCompares() {return compares;}

    @Override
    public String getHeader() { return header; }

}

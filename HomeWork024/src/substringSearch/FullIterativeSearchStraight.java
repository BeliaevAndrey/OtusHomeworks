package substringSearch;

public class FullIterativeSearchStraight implements ISearcher {

    public final String header = "Iterative search prefix shift 1 algorithm";

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

        for (int i = 0; i < lenText - lenMask + 1; i++) {
            int pos = i, j = 0;
            for (; j < lenMask; j++, pos++) {
                if (!compare(text.charAt(pos), mask.charAt(j))) {
                    break;
                }
            }
            if (pos - i == lenMask) return i;
        }
        return -1;
    }

        boolean compare(char l, char r) {
        compares++;
        return l == r;
    }

    @Override
    public void prepare() {    }

    @Override
    public int getCompares() { return compares; }

}

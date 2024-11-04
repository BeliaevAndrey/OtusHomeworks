package substringSearch;

public class BoyerMooreHorspool implements ISearcher {

    public final String header = "Boyer-Moore-Horspool algorithm";

    private String text;
    private String mask;
    int lenText;
    int lenMask;

    private int compares = 0;
    int[] shifts;

    public void init(String text, String mask) {
        this.text = text;
        this.mask = mask;
        lenText = text.length();
        lenMask = mask.length();
        prepare();
    }


    public int search() {
        int t = 0;
        compares = 0;
        prepare();
        int lenText = text.length();
        int lenMask = mask.length();
        while (t <= lenText - lenMask) {
            int m = lenMask - 1;
            while ((m >= 0) && (text.charAt(t + m) == mask.charAt(m))) {
                compares++;
                m--;
            }
            if (m < 0)
                return t;
            t += shifts[text.charAt(t + lenMask - 1)];
        }
        return -1;
    }

    public void prepare() {
        int size = 128;
        shifts = new int[size];
        for (int i = 0; i < size; i++)
            shifts[i] = lenMask;
        for (int i = 0; i < lenMask - 1; i++)
            shifts[mask.charAt(i)] = lenMask - i - 1;
    }


    public int getCompares() {return compares;}

    @Override
    public String getHeader() { return header; }

}

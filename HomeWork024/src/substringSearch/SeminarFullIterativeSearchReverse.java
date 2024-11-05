package substringSearch;

public class SeminarFullIterativeSearchReverse implements ISearcher {

    public final String header = "Seminar implementation Iterative search algorithm reverse";

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

    public int search() {
        int t = 0;
        compares = 0;
        while (t <= lenText - lenMask) {
            int m = lenMask - 1;
            while ((m >= 0) && (text.charAt(t + m) == mask.charAt(m))) {
                compares++;
                m--;
            }
            if (m < 0)
                return t;
            t++;
        }
        return -1;
    }

    @Override
    public void prepare() {    }

    @Override
    public int getCompares() { return compares; }

    @Override
    public String getHeader() { return header; }

}

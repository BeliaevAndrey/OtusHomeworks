package substringSearch;

public class SeminarFullIterativeSearchStraight implements ISearcher {

    public final String header = "Seminar implementation Iterative search prefix shift algorithm";

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
        int t = 0;
        compares = 0;
        while (t <= lenText - lenMask) {
            int m = 0;
            while ((m < lenMask) && (text.charAt(t + m) == mask.charAt(m))) {
                compares++;
                m++;
            }
            if (m == lenMask) return t;
            t++;
        }
        return -1;
    }

    @Override
    public void prepare() {    }

    @Override
    public int getCompares() {return compares;}
}

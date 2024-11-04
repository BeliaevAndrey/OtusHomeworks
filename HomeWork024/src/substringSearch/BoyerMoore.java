package substringSearch;

public class BoyerMoore implements ISearcher {

    public final String header = "Boyer-Moore algorithm";
    private String text;
    private String mask;
    int lenText;
    int lenMask;
    private int compares = 0;
    int[] suffix;

    public void init(String text, String mask) {
        this.text = text;
        this.mask = mask;
        lenText = text.length();
        lenMask = mask.length();
        prepare();
    }

    public int search() {
        prepare();
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
            int c = lenMask - 1 - m;
            t += suffix[c];
        }
        return -1;
    }

    public void prepare() {
        suffix = new int[lenMask];
        suffix[0] = 1;
        for (int i = 1; i < lenMask; i++) {
            for (int k = 1; k <= lenMask; k++) {
                int cnt = 0;
                for (int j = 0; j < i; j++) {
                    if ((j + k + 1) > lenMask) break;   // out of bounds
                    if (mask.charAt(lenMask - 1 - j) != mask.charAt(lenMask - 1 - j - k))
                        break;
                    cnt++;
                }
                if (cnt < i) {
                    if ((k + cnt) == lenMask) {
                        suffix[i] = k;
                        break;
                    } else continue;
                }
                suffix[i] = k;
                break;
            }
        }
    }

    public int getCompares() { return compares; }
}

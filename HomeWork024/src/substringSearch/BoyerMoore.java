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
        int t = 0;                      // text position
        compares = 0;
        while (t <= lenText - lenMask) {
            int m = lenMask - 1;        // pattern position
            while ((m >= 0) && (text.charAt(t + m) == mask.charAt(m))) {
                // symbol in text equals symbol in pattern
                compares++;
                m--;                    // decrement pattern position
            }
            if (m < 0) return t;        // whole pattern symbols are coincident to text; returning position
            int c = lenMask - 1 - m;    // calculate suffix length
            t += suffix[c];             // shift by suffix length
        }
        return -1;
    }

    public void prepare() {
        // search for repeated symbol groups in pattern
        suffix = new int[lenMask];                  // array of shifts by pattern length
        suffix[0] = 1;                              // shift by 1 position if no coincidences
        for (int i = 1; i < lenMask; i++) {         // cycle over shifts array
            for (int k = 1; k <= lenMask; k++) {    // cycle over symbol group
                int cnt = 0;                        // set group length counter
                for (int j = 0; j < i; j++) {       // cycle over symbol subgroup in group
                    if ((j + k + 1) > lenMask) break;   // prevent out of bounds
                    if (mask.charAt(lenMask - 1 - j) != mask.charAt(lenMask - 1 - j - k))
                        break;                  // stop if no coincidence on this step
                    cnt++;                      // increment group length counter
                }
                if (cnt < i) {                  // group length lesser than overall position
                    if ((k + cnt) == lenMask) { // subgroup is till pattern end
                        suffix[i] = k;          // set shift for this suffix as difference of pattern and suffix lengths
                        break;
                    } else continue;
                }
                suffix[i] = k;  // set shift for i coincident symbols as k
                break;
            }
        }
    }

    public int getCompares() { return compares; }

    @Override
    public String getHeader() { return header; }

}

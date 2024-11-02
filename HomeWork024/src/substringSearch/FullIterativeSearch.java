package substringSearch;

public class FullIterativeSearch {

    public int search(String string, String substring) {
        int lenString = string.length();
        int lenSub = substring.length();
        for (int i = 0; i < lenString - lenSub + 1; i++) {
            int pos = i, j = 0;
            for (; j < lenSub; j++, pos++) {
                if (string.charAt(pos) != substring.charAt(j)) break;
            }
            if (pos - i == lenSub) return i;
        }
        return -1;
    }

}

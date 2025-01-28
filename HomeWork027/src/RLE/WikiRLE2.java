package RLE;

public class WikiRLE2 {

    /* String to string encoding. Numbers in source sequence are not supported.
    *  Sequences with high diversity at edges result in data damage.
    * */

    public String encode(String line) {
        char[] array = line.toCharArray();
        int arLen = array.length;
        int count = 0;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < arLen) {
            char c = array[i];
            int j = i;
            while (j < arLen && array[j++] == c) count++;
            if (count > 1) {
                result.append(count).append(c);
                i += count;
                count = 0;
            } else {
                count = 0;
                int seqCount = 1;
                StringBuilder seq = new StringBuilder();
                seq.append(array[j - 2]);
                while (j < arLen && array[j] != array[j - 1]) {
                    seq.append(array[j - 1]);
                    seqCount++;
                    j++;
                }
                result.append(-seqCount).append(seq);
                i += seqCount;
            }

        }

        return result.toString();
    }

    public String decode(String encoded) {
        StringBuilder result = new StringBuilder();
        char[] lineArr = encoded.toCharArray();
        int strLen = encoded.length();
        int i = 0;
        while (i < strLen) {
            StringBuilder number = new StringBuilder();

            while ((lineArr[i] >= '0' && lineArr[i] <= '9') || lineArr[i] == '-') number.append(lineArr[i++]);

            int count = Integer.parseInt(number.toString());

            if (count > 0) {
                result.append(String.format("%c", lineArr[i]).repeat(count));
                i++;
            } else {
                count = -count + i;
                while (i < count) result.append(lineArr[i++]);
            }
        }
        return result.toString();
    }

}

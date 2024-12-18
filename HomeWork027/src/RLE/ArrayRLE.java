package RLE;

import java.util.Arrays;

public class ArrayRLE {

    public static void main(String[] args) {
        ArrayRLE arl = new ArrayRLE();

        String wikiLine1 = "WWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
        String wikiLine2 = "ABCABCABCDDDFFFFFF";

        char[] sample1 = wikiLine1.toCharArray();
        byte[] encoded1 = arl.encode(sample1);
        System.out.println(Arrays.toString(encoded1));
        StringBuilder sb = new StringBuilder();
        for (char c : arl.decode(encoded1)) sb.append(c);
        System.out.println("rst: " + sb);
        System.out.println(sb.toString().equals(wikiLine1));

        char[] sample2 = wikiLine2.toCharArray();
        byte[] encoded2 = arl.encode(sample2);
        System.out.println(Arrays.toString(encoded2));
        sb = new StringBuilder();
        for (char c : arl.decode(encoded2)) sb.append(c);
        System.out.println("rst: " + sb);
        System.out.println(sb.toString().equals(wikiLine2));
    }

    public byte[] encode(char[] array) {
        int arLen = array.length;
        byte[] buffer = new byte[arLen];
        int i = 0;
        int total = 0;

        while (i < arLen) {
            int j = i;
            int count = 0;

            while (j < arLen && array[j++] == array[i]) count++;

            if (count > 1) {                                // in case of amount of bytes more than 1
                buffer[total++] = (byte) count;             // a positive means the repetitions amount
                buffer[total++] = (byte) array[i];          // byte value of a symbol
                i += count;                                 // shift counter to position after sequence

            } else {
                int seqLen = 1;                             // first byte already counted
                int bufStart = total++;                     // a place for length of series value
                buffer[total++] = (byte) array[j - 2];      // first byte of sequence

                while (array[j] != array[j - 1]) {
                    buffer[total++] = (byte) array[j - 1];  // placing a symbol byte value
                    j++;
                    seqLen++;
                }

                buffer[bufStart] = (byte) -seqLen;      // a negative mean the length of a sequence
                i += seqLen;                            // shift counter to position after sequence
            }
        }

        byte[] encoded = new byte[total];

        for (int j = 0; j < total; j++) encoded[j] = buffer[j];

        return encoded;
    }

    public char[] decode(byte[] encoded) {
        int arLen = encoded.length;
        char[] buffer = new char[arLen * arLen];
        int total = 0;

        for (int i = 0; i < arLen; ) {
            if (encoded[i] > 0) {   /* appending repeating bytes one by one */
                for (int j = 0; j < encoded[i]; j++) buffer[total++] = (char) encoded[i + 1];
                i += 2;             // shift to next pair
            } else {                /* appending a sequence byte by byte */
                int j = 0;
                for (; j < -encoded[i]; j++) buffer[total++] = (char) encoded[i + 1 + j];
                i += j + 1;         // shift to last position
            }
        }

        char[] decoded = new char[total];

        for (int i = 0; i < total; i++) decoded[i] = buffer[i];

        return decoded;
    }


}

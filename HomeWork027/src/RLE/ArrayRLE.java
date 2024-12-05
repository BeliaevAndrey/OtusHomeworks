package RLE;

import java.util.Arrays;

public class ArrayRLE {

    public static void main(String[] args) {
        ArrayRLE arl = new ArrayRLE();
        String wikiLine1 = "WWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
                          //WWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW
        String wikiLine2 = "ABCABCABCDDDFFFFFF";

        char[] a = wikiLine1.toCharArray();
        arl.encode(a);
        System.out.println(Arrays.toString(arl.encoded));
//        char[] dcd1 =  arl.decode();
        StringBuilder sb = new StringBuilder();
        for (char c : arl.decode()) sb.append(c);
        System.out.println("rst: " + sb);
        System.out.println(sb.toString().equals(wikiLine1));

        char[] b = wikiLine2.toCharArray();
        arl.encode(b);
        System.out.println(Arrays.toString(arl.encoded));
        sb = new StringBuilder();
        for (char c : arl.decode()) sb.append(c);
        System.out.println("rst: " + sb);
        System.out.println(sb.toString().equals(wikiLine2));
    }

    byte[] storage;
    byte[] encoded;

    public void init(char[] array) {
        storage = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            storage[i] = (byte) array[i];
        }
    }

    public void encode(char[] array) {
        int arLen = array.length;
        byte[] buffer = new byte[arLen];
        int i = 0;
        int k = 0;
        while (i < arLen) {
            int j = i;
            int count = 0;
            while (j < arLen && array[j++] == array[i]) count++;
            if (count > 1) {
                buffer[k++] = (byte) count;
                buffer[k++] = (byte) array[i];
            } else {
                int seqLen = 1;
                int bufStart = k++;
                buffer[k++] = (byte) array[j - 2];
                while (array[j] != array[j - 1]) {
                    buffer[k++] = (byte) array[j - 1];
                    j++;
                    seqLen++;
                }
                buffer[bufStart] = (byte) -seqLen;
                count = seqLen;
            }
            i += count;
        }
        encoded = new byte[k];

        for (int j = 0; j < k; j++) encoded[j] = buffer[j];
    }

    public char[] decode() {
        int arLen = encoded.length;
        char[] buffer = new char[arLen * arLen];
        int total = 0;
        for (int i = 0; i < arLen; ) {
            if (encoded[i] > 0) {
                for (int j = 0; j < encoded[i]; j++) buffer[total++] = (char) encoded[i + 1];
                i+=2;
            }
            else {
                int j = 0;
                for (; j < -encoded[i]; j++) buffer[total++] = (char) encoded[i + 1 + j];
                i += j + 1;
            }
        }
        char [] decoded = new char[total];
        for (int i = 0; i < total; i++) decoded[i] = buffer[i];

        return decoded;
    }


}

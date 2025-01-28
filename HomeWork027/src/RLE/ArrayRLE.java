package RLE;

public class ArrayRLE {

    public byte[] encode(char[] array) {
        int arLen = array.length;
        byte[] buffer = new byte[arLen];
        int i = 0;
        int total = 0;

        while (i < arLen) {
            int j = i;
            int count = 0;

            while (j < arLen && array[j] == array[i]) {
                count++;
                j++;
            }

            if (count > 127) {                  // in case of count value larger than byte
                for (int k = 0; k < count / 127; k++) {
                    buffer[total++] = 127;
                    i += 127;
                }
                count %= 127;
            }

            if (count > 1) {                                // in case of amount of bytes more than 1
                buffer[total++] = (byte) count;             // a positive means the repetitions amount
                buffer[total++] = (byte) array[i];          // byte value of a symbol
                i += count;                                 // shift counter to position after sequence

            } else {
                int seqLen = 1;                             // first byte already counted
                int bufStart = total++;                     // a place for length of series value
                buffer[total++] = (byte) array[j - 1];      // first byte of sequence
                j += 1;                                     // shift by 1 position -- 1st byte is written
                while (j < arLen && array[j] != array[j - 1]) {
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
        int bufLen = countLen(encoded);
        char[] buffer = new char[bufLen];
        int total = 0;

        for (int i = 0; i < arLen; ) {
            if (encoded[i] > 0) {   /* appending repeating bytes one by one */
                int count = 0;

                while (encoded[i] == 127) count += encoded[i++];

                count += encoded[i];

                for (int j = 0; j < count; j++) buffer[total++] = (char) encoded[i + 1];
                i += 2;             // shift to next pair

            } else {                /* appending a sequence byte by byte */

                int j = 0;
                for (; j < -encoded[i]; j++) buffer[total++] = (char) encoded[i + 1 + j];
                i += j + 1;         // shift to last position

            }
        }

        return buffer;
    }

    private int countLen(byte[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length; i += 2) {

            while (arr[i] == 127) size += arr[i++];

            if (arr[i] < 0) {
                size += -arr[i];
                i += -arr[i] - 1;
            } else size += arr[i];
        }
        return size;
    }


}

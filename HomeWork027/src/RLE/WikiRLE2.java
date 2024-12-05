package RLE;

public class WikiRLE2 {

    public static void main(String[] args) {
        WikiRLE2 arl = new WikiRLE2();
        String wikiLine1 = "WWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
        String wikiExpected1 = "9W3B24W1B14W";


        String result = arl.encode(wikiLine1);
        System.out.println(result.equals(wikiExpected1));
        System.out.println(result);


        String out = arl.decode(result);
        System.out.printf("out: %s\n", out);
        System.out.printf("out: %s\n", wikiLine1);
        System.out.printf("out: %s\n", wikiLine1.equals(out));

        String wikiLine2 = "ABCABCABCDDDFFFFFF";
        String wikiExpected2 = "1A1B1C1A1B1C1A1B1C3D6F";
        String wikiExpected3 = "-9ABCABCABC3D6F";

        String result2 = arl.encode0(wikiLine2);
        System.out.println("result 2: " + result2.equals(wikiExpected2));
        System.out.println("result 2: " + result2);

        String result3 = arl.encode(wikiLine2);
        System.out.println("result 3: " + result3.equals(wikiExpected3));

        System.out.println("result 3: " + result3);

        System.out.println();
        String out1 = arl.decode(result2);
        System.out.printf("out1: %s\n", out1);
        System.out.printf("out1: %s\n", wikiLine2.equals(out1));
        System.out.println();

        String out2 = arl.decode(result3);
        System.out.printf("out2: %s\n", out2);
        System.out.printf("out2: %s\n", wikiLine2.equals(out2));

    }


    public String encode0(String line) {
        char[] array = line.toCharArray();
        int arLen = array.length;
        int count = 0;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < arLen) {
            char c = array[i];
            int j = i;
            while (j < arLen && array[j++] == c) count++;
            result.append(count).append(c);
            i += count;
            count = 0;
        }
        return result.toString();
    }


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
                while (array[j] != array[j - 1]) {
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

    public String decode0(String encoded) {
        StringBuilder result = new StringBuilder();
        char[] lineArr = encoded.toCharArray();
        int strLen = encoded.length();
        int i = 0;
        while (i < strLen) {
            StringBuilder number = new StringBuilder();
            while (lineArr[i] >= '0' && lineArr[i] <= '9') number.append(lineArr[i++]);
            int count = Integer.parseInt(number.toString());
            result.append(String.format("%c", lineArr[i]).repeat(count));
            i++;
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

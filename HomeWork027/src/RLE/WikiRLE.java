package RLE;

public class WikiRLE {

    public static void main(String[] args) {
        WikiRLE arl = new WikiRLE();
        String wikiLine1 = "WWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
        String wikiExpected1 = "9W3B24W1B14W";

        String wikiLine2 = "ABCABCABCDDDFFFFFF";
        String wikiExpected2 = "1A1B1C1A1B1C1A1B1C3D6F";

        arl.init(wikiLine1);

        String result = arl.encode();
        System.out.println(result.equals(wikiExpected1));
        System.out.println(result);

        arl.init(wikiLine2);

        String result2 = arl.encode();
        System.out.println(result2.equals(wikiExpected2));
        System.out.println(result2);

        String out = arl.decode(result);
        System.out.printf(">>%s<<\n", out);
        System.out.printf(">>%s<<\n", wikiLine1);
        System.out.printf(">>%s<<\n", wikiLine1.equals(out));
        String out2 = arl.decode(result2);
        System.out.printf(">>%s<<\n", out2);
        System.out.printf(">>%s<<\n", wikiLine2);
        System.out.printf(">>%s<<\n", wikiLine2.equals(out2));

    }

    private char[] array;
    private int arLen = 0;

    WikiRLE() {    }

    public void init(String sequence) {
        array = sequence.toCharArray();
        arLen = array.length;
    }
    public void init(char[] sequence) {
        array = sequence;
        arLen = array.length;
    }

    public String encode() {
//        char c;
        int count = 0;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i < arLen) {
            char c = array[i];
            int j = i;
            while (j < arLen && array[j++] == c) count++;
            result.append(count).append(c);
            i += count;
            count = 0;
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
            while (lineArr[i] >= '0' && lineArr[i] <= '9' ) number.append(lineArr[i++]);
            int count = Integer.parseInt(number.toString());
            result.append(String.format("%c", lineArr[i]).repeat(count));
            i++;
        }
        return result.toString();
    }

}

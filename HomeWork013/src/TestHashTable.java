import util.DraftHT;

public class TestHashTable {

    public static void main(String[] args) {
        TestHashTable tht = new TestHashTable();
        tht.simpleTest();

        int len = (int) 5e7;    // 1e8 -> java.lang.OutOfMemoryError: Java heap space

        long start = System.nanoTime();
        try {
            tht.longTest(len);
        } finally {
            double end = (System.nanoTime() - start) / 1e9;
            System.out.println(end);
        }

    }

    public void simpleTest() {
        CustomHashTable cht = new CustomHashTable();
        String[] words = new String[]{
                "A", "big", "red", "dog", "carries", "a", "black",
                "cat", "in", "a", "white", "hat", "which", "has",
                "caught", "bat", "in", "a", "bag", "the", "quick", "brown",
                "fox", "jumps", "over", "the", "lazy", "dog"
        };


        int i = 0;
        for (String word : words) {
            cht.put(word, i++);
        }

        for (String word : words) {
            System.out.printf("contains: %s -> %s\n", word, cht.contains(word));
        }
        System.out.println();
        for (int j = 1; j <= words.length; j++) {
            System.out.printf("get: %s -> %d -> ", words[words.length - j], cht.get(words[words.length - j]));
            System.out.println(words[words.length - j].equals(words[cht.get(words[words.length - j])]));
        }
    }

    void longTest(int len) {
        DraftHT dht = new DraftHT();
        CustomHashTable cht = new CustomHashTable();
        String[] words = dht.getWordPack(len);
        int index = 0;
        for (String word : words) {
           cht.put(word, index++);
        }

        for (String word : words) {
           if (!cht.contains(word)) System.out.println("Lost: " + word);
        }
    }
}

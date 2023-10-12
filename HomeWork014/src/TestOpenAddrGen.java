import util.DraftHT;

public class TestOpenAddrGen {
    public static void main(String[] args) {
        TestOpenAddrGen toag = new TestOpenAddrGen();
        System.out.println("Test put/get: ");
        toag.testPutGet();
        System.out.println("Test remove: ");
        toag.testRemove();

    }

    void testPutGet() {
        OpenAddressHashTableGen<String, String> oaht = new OpenAddressHashTableGen<>();
        DraftHT dht = new DraftHT();

        String[] wordPack = dht.getWordPack((int) 5e7);

        long startPut = System.nanoTime();
        for (int i = 0; i < wordPack.length; i++) {
            oaht.put((String.format("%08d", ((long) i * i + 17))), wordPack[i]);
        }
        double endPut = (System.nanoTime() - startPut) / 1e9;

        long startGet = System.nanoTime();
        for (int i = 0; i < wordPack.length; i++) {
            String key = String.format("%08d", ((long) i * i + 17));
            String word = oaht.get(key);
            if (!word.equals(wordPack[i])) System.out.println("FAIL: " + key + " " + i);
        }
        double endGet = (System.nanoTime() - startGet) / 1e9;

        System.out.printf("Put time: %f\nGet time: %f\n\n", endPut, endGet);
    }

    void testRemove() {
        OpenAddressHashTableGen<String, String> oaht = new OpenAddressHashTableGen<>();
        DraftHT dht = new DraftHT();
        String[] wordPack = dht.getWordPack(30);

        for (int i = 0; i < wordPack.length; i++) {
            oaht.put((String.format("%08d", i * i + 17)), wordPack[i]);
        }
        for (int i = 0; i < wordPack.length; i++) {
            String key = String.format("%08d", i * i + 17);
            String word = oaht.get(key);
            System.out.print(key + " " + i + " " + word + " == " + wordPack[i] + " -> ");
            System.out.println(word.equals(wordPack[i]));
        }

        System.out.println();

        for (int i = 15; i <= 20; i++) {
            String key = String.format("%08d", i * i + 17);
            oaht.remove(key);
        }

        for (int i = 18; i <= 20; i++) {
            String key = String.format("%08d", i * i + 17);
            oaht.put(key,
                    String.format("Key %s; Substituting removed phrase %d", key, i));
        }

        for (int i = 0; i < wordPack.length; i++) {
            String key = String.format("%08d", i * i + 17);
            String word = oaht.get(key);
            System.out.print(key + " " + i + " " + word + " == " + wordPack[i]+ " -> ");
            if ((i > 14 && i < 21) && word == null) {
                System.out.println("REMOVED");
                continue;
            }
            System.out.println(word.equals(wordPack[i]));
        }
    }

}

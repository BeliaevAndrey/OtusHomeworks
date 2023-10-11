import util.DraftHT;

public class TestOpenAddrGen {
    public static void main(String[] args) {
        OpenAddressHashTableGen<String, String> oaht = new OpenAddressHashTableGen<>();
        DraftHT dht = new DraftHT();
        String[] wordPack = dht.getWordPack(100);
        for (int i = 0; i < wordPack.length; i++) {
            oaht.put((String.format("%08d", i * i + 17)), wordPack[i]);
        }
        for (int i = 0; i < wordPack.length; i++) {
            String key = String.format("%08d", i * i + 17);
            String word = oaht.get(key);
            System.out.print(key + " " + i + " " + word + " == ");
            System.out.print(wordPack[i] + " ");
            System.out.println(word.equals(wordPack[i]));
        }

    }
}

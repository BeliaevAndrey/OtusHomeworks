package tests;

import OAHashTable.OpenAddressHashTableGen;
import util.GenerateRandomWordsArr;
import Trie.Trie;
import util.FReader;
import util.WriteResults;

import java.util.HashMap;
import java.util.Locale;


public class TestTrieCompareHT {

    static int successTrie = 0;
    static int successOAHT = 0;
    static int nullsTrie = 0;
    static int nullsOAHT = 0;

    static HashMap<String, Double> resultTrie;
    static HashMap<String, Double> resultOAHT;
    static String[] randLines;

    static String[] keys;

    public static void main(String[] args) {

        int randTestLen = (int) 1e8;

        resultTrie = new HashMap<>();
        resultOAHT = new HashMap<>();

        GenerateRandomWordsArr genWords = new GenerateRandomWordsArr();
        FReader fr = new FReader();
        String[] dict = fr.readAllLines("mueller_utf8-cl.txt");
        randLines = genWords.getArray(randTestLen);

        TestTrieCompareHT app = new TestTrieCompareHT();
        keys = app.getKeys(dict);


        app.testTrie(dict);
        System.out.println("Trie test finished");
        app.testHashTable(dict);
        System.out.println("Open address hash table test finished");

        System.out.printf("%20s%20s\n", "successTrie", "successOAHT");
        System.out.printf("%20d%20d\t%s\n", successTrie, successOAHT, successTrie == successOAHT);
        System.out.printf("%20d%20d\t%s\n", nullsTrie, nullsOAHT, nullsTrie == nullsOAHT);

        Locale.setDefault(Locale.US);

        String head = "%20s|%20s|%20s|%20s|%20s|%20s|%20s\n";
        String body = "%20s|%20f|%20.0f|%20f|%20.0f|%20f|%20f\n";
        System.out.printf(head,
                "Structure", "Put time", "Searched words amt", "Search time",
                "Searched words amt", "Search time", "Delete time");
        System.out.printf(body, "Trie",
                resultTrie.get("Put time"),
                resultTrie.get("Searched words A"),
                resultTrie.get("Search time A"),
                resultTrie.get("Searched words B"),
                resultTrie.get("Search time B"),
                resultTrie.get("Delete time"));
        System.out.printf(body, "Hash Table",
                resultOAHT.get("Put time"),
                resultOAHT.get("Searched words A"),
                resultOAHT.get("Search time A"),
                resultOAHT.get("Searched words B"),
                resultOAHT.get("Search time B"),
                resultOAHT.get("Delete time"));


        String thead = "%s;%s;%s;%s;%s;%s;%s";
        String tbody = "%s;%f;%.0f;%f;%.0f;%f;%f";
        String[] results = new String[3];
        results[0] = String.format(
                thead,
                "Structure", "Put time", "Searched words amt", "Search time",
                "Searched words amt", "Search time", "Delete time"
        );
        results[1] = String.format(
                tbody,
                "Trie",
                resultTrie.get("Put time"),
                resultTrie.get("Searched words A"),
                resultTrie.get("Search time A"),
                resultTrie.get("Searched words B"),
                resultTrie.get("Search time B"),
                resultTrie.get("Delete time")
        );
        results[2] = String.format(
                tbody,
                "Hash Table",
                resultOAHT.get("Put time"),
                resultOAHT.get("Searched words A"),
                resultOAHT.get("Search time A"),
                resultOAHT.get("Searched words B"),
                resultOAHT.get("Search time B"),
                resultOAHT.get("Delete time")
        );

        WriteResults wr = new WriteResults();
        wr.writeResultsTable(results);
    }

    void testHashTable(String[] dict) {
        OpenAddressHashTableGen<String, String> oaHTable = new OpenAddressHashTableGen<>(true);
        testLoadHT(dict, oaHTable);
        testSearchHT(keys, oaHTable);
        testSearchHT(randLines, oaHTable);
        testDeleteHT(randLines, oaHTable);


    }

    void testLoadHT(String[] dict, OpenAddressHashTableGen<String, String> oaHTable) {
        long start = System.nanoTime();
        for (String l : dict) {
            String[] parts = l.split("\t");
            oaHTable.put(parts[0], l);
        }
        double end = (double) (System.nanoTime() - start) / 1e9;
        resultOAHT.put("Put time", end);
    }

    void testSearchHT(String[] keys, OpenAddressHashTableGen<String, String> oaHTable) {
        int testLen = keys.length;
        long start = System.nanoTime();
        for (String key : keys) {
            String s = oaHTable.get(key);
            if (s != null) successOAHT++;
            else nullsOAHT++;
        }
        double end = (double) (System.nanoTime() - start) / 1e9;

        String head = String.format("Searched words %s", testLen == randLines.length ? "B" : "A");
        resultOAHT.put(head, (double) testLen);
        head = String.format("Search time %s", testLen == randLines.length ? "B" : "A");
        resultOAHT.put(head, end);
    }

    void testDeleteHT(String[] keys, OpenAddressHashTableGen<String, String> oaHTable) {
        int testLen = keys.length;

        long start = System.nanoTime();
        for (String value : keys) {
            oaHTable.remove(value);
        }
        double end = (double) (System.nanoTime() - start) / 1e9;

        resultOAHT.put("Delete time", end);

        // check
        String s;
        for (String key : keys) {
            if ((s = oaHTable.get(key)) != null) System.out.printf("Not deleted: %s", s);
        }

    }


    void testTrie(String[] dict) {
        Trie book = new Trie();
        testLoadTrie(dict, book);
        testSearchTrie(keys, book);
        testSearchTrie(randLines, book);
        testDeleteTrie(keys, book);

    }

    void testLoadTrie(String[] dict, Trie book) {

        long start = System.nanoTime();
        for (String l : dict) {
            String[] parts = l.split("\t");
            book.insert(parts[0], l);
        }
        double end = (double) (System.nanoTime() - start) / 1e9;
        resultTrie.put("Put time", end);
    }

    void testSearchTrie(String[] keys, Trie book) {
        int testLen = keys.length;
        Object s;

        long start = System.nanoTime();
        for (String key : keys) {
            s = book.get(key);
            if (s != null) successTrie++;
            else nullsTrie++;
        }
        double end = (double) (System.nanoTime() - start) / 1e9;

        String head = String.format("Searched words %s", testLen == randLines.length ? "B" : "A");
        resultTrie.put(head, (double) testLen);
        head = String.format("Search time %s", testLen == randLines.length ? "B" : "A");
        resultTrie.put(head, end);

    }


    void testDeleteTrie(String[] keys, Trie book) {

        long start = System.nanoTime();
        for (String l : keys) {
            book.delete(l);
        }
        double end = (double) (System.nanoTime() - start) / 1e9;

        resultTrie.put("Delete time", end);

        // check
        Object s;
        for (String key : keys) {
            if ((s = book.get(key)) != null) System.out.printf("Not deleted: %s", s);
        }

    }


    // service
    private String[] getKeys(String[] dict) {
        String[] keys = new String[dict.length];
        int count = 0;
        for (String l : dict) {
            String[] parts = l.split("\t");
            keys[count++] = parts[0];
        }
        return keys;
    }

}


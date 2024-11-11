package englishDictPrefixTree;

import java.util.Locale;

public class PrefixTree {


    private Node root;

    public PrefixTree() {
        root = new Node('.'); // root element keeps '.' (dot)
    }

    public void put(String key, Word word) {
        key = key.toLowerCase(Locale.ROOT);
        Node node = root;
        for (char c : key.toCharArray()) node = node.next(c);
        node.word = word;
    }

    public Word get(String key) {
        key = key.toLowerCase(Locale.ROOT);
        Node node = root;
        for (char c : key.toCharArray()) {
            if (node.exists(c)) node = node.next(c);
            else return null;
        }
        if (node == null) return null;
        return node.word;
    }
}

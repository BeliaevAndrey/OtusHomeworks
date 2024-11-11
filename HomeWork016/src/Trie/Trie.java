package Trie;

public class Trie {


    Node root;

    public Trie() {
        root = new Node('.');
    }

    public void insert(String key, Object payload) {
//        key = key.toLowerCase(Locale.ROOT);
        Node node = root;
        for (char c : key.toCharArray()) node = node.next(c);
        node.set(payload);
    }

    public Node search(String key) {
        Node node = root;
        for (char c : key.toCharArray()) {
            if (node.exists(c)) node = node.next(c);
            else return null;
        }

        if (node == null) return null;
        return node;
    }

    public Object delete(String key) {
        Node node = root;
        for (char c : key.toCharArray()) {
            node = node.next(c);
        }

        if (node == null) return null;

        Object payload = node.get();
        node.set(null);

        return payload;
    }

    public Object get(String key) {
        Node node = search(key);
        if (node == null) return null;
        return node.get();
    }
}

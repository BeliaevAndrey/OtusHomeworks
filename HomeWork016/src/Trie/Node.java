package Trie;

public class Node {

    private final int A = 128;

    private final Node[] next;
    private Object payload;

    public char c;

    public Node(char c) {
        this.c = c;
        next = new Node[A];
        payload = null;
    }

    public boolean exists(char c) {return next[c] != null;}
//    public boolean exists(char c) {return next[c - 'a'] != null;}


    public Node next(char c) {
//        if (!exists(c)) next[c - 'a'] = new Node(c);
        if (!exists(c)) next[c] = new Node(c);
        return next[c];
//        return next[c - 'a'];
    }

    public boolean isEnd() {return payload != null;}

    @Override
    public String toString() { return isEnd() ? "FINAL" : String.valueOf(c); }

    public void set(Object payload) { this.payload = payload; }
    public Object get() { return payload; }
}

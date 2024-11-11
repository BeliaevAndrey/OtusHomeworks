package englishDictPrefixTree;

public class Node {

    private final int A = 26;
    public Node[] next;
    public Word word;
    public char c;

//    public boolean terminal = true;  // terminal flag (element is last in sequence)

    public Node(char c) {
        this.c = c;
        next = new Node[A];
        word = null;
    }

    public boolean exists(char c) {return next[c - 'a'] != null;}

    /**
     * Get next node; based on next char on key sequence
     * @param c  next char in key sequence
     * @return  next node
     */
    public Node next(char c) {
        if (!exists(c)) next[c - 'a'] = new Node(c);
        return next[c - 'a'];
    }

    /**
     * Substitution for 'terminal' flag
     */
    public boolean isEnd() {return word != null; }

    @Override
    public String toString() { return isEnd() ? "FINAL" : String.valueOf(c); }
}

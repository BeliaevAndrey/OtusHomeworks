package leetTask;

/* results
 * https://leetcode.com/problems/implement-trie-prefix-tree/submissions/1445194744
 */


public class Trie {

   private final Node root;

    public Trie() {
        root = new Node('.');
    }

    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) node = node.next(c);
        node.word = word;
    }

    public boolean search(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (node.exists(c)) node = node.next(c);
            else return false;
        }
        if (node.word != null) return node.word.equals(word);
        return false;
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            if (node.exists(c)) node = node.next(c);
            else return false;
        }
        return !node.terminal;

    }


    static class Node {

        private final int A = 26;
        private Node[] next;
        private String word;
        public char c;

        public boolean terminal = true;  // terminal flag (element is last in sequence)

        public Node(char c) {
            this.c = c;
            next = new Node[A];
            word = null;
            terminal = false;
        }

        private boolean exists(char c) {return next[c - 'a'] != null;}


        private Node next(char c) {
            if (!exists(c)) next[c - 'a'] = new Node(c);
            return next[c - 'a'];
        }

        public boolean isEnd() {return word != null; }

        @Override
        public String toString() { return isEnd() ? "FINAL" : String.valueOf(c); }

    }
}


/*
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


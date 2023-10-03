import java.util.Arrays;


public class BST {

    Node root;
    int[] sorted;
    int index;
    int len;

    public BST() {
        root = null;
    }


    public void init(int[] array) {
        for (int e : array) {
            this.insert(e);
        }
    }

    public void sort() {
        this.index = 0;
        this.sorted = new int[len];
        dfs(root);
    }

    public void insert(int key) {
        root = insert(root, key);
        this.len++;
        this.sorted = null;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.L = insert(node.L, key);
        }
        if (key > node.key)
            node.R = insert(node.R, key);
        return node;
    }


    public boolean search(int x) {
        Node node = search(root, x);
        return node != null && node.key == x;
    }

    private Node search(Node node, int x) {
        if (node == null || node.key == x) return node;
        if (node.key > x) {
            return search(node.L, x);
        } else
            return search(node.R, x);
    }

    private void dfs(Node node) {
        if (node == null) return;
        dfs(node.L);
        this.sorted[index++] = node.key;
        dfs(node.R);
    }

    public void remove(int x) {
        Node nodeToRm = search(root, x);
        if (nodeToRm == null) return;

        if (nodeToRm == root) {
            removeRoot();
        } else {
            if (nodeToRm.isLeaf()) {
                Node parent = searchParent(root, nodeToRm);
                removeNode(nodeToRm, parent);
            } else if (nodeToRm.L == null) {
                Node parent = searchParent(root, nodeToRm);
                removeNode(nodeToRm, parent);
            } else if (nodeToRm.R == null) {
                Node parent = searchParent(root, nodeToRm);
                removeNode(nodeToRm, parent);
            } else if (nodeToRm.L != null) {
                if (nodeToRm.L.isLeaf()) {
                    nodeToRm.key = nodeToRm.L.key;
                    nodeToRm.L = null;
                } else {
                    Node node = nodeToRm.L;
                    Node parent = nodeToRm;
                    while (node.R != null) {
                        parent = node;
                        node = node.R;
                    }
                    nodeToRm.key = node.key;
                    removeNode(node, parent);
                }
            } else if (nodeToRm.R != null) {
                if (nodeToRm.R.isLeaf()) {
                    nodeToRm.key = nodeToRm.R.key;
                    nodeToRm.R = null;
                } else {
                    Node node = nodeToRm.R;
                    Node parent = nodeToRm;
                    while (node.L != null) {
                        parent = node;
                        node = node.L;
                    }
                    nodeToRm.key = node.key;
                    removeNode(node, parent);
                }
            }
        }
        this.len--;
        this.sorted = null;
    }

    private void removeRoot() {
        if (root.isLeaf()) {
            root = null;
        } else if (root.L != null && root.L.R == null) {
            root.L.R = root.R;
            root = root.L;
        } else if (root.R != null && root.R.L == null) {
            if (root.L != null) {
                root.R.L = root.L;
                root = root.L;
            }
            else root = root.R;
        } else {
            Node node;
            if (root.L != null) {
                node = root.L;
                Node parent = node;
                while (node.R != null) {
                    parent = node;
                    node = node.R;
                }
                root.key = node.key;
                removeNode(node, parent);
            } else root = root.R;
        }
    }


    private void removeNode(Node nodeToRm, Node parent) {
        if (parent.R == nodeToRm) {
            if (nodeToRm.isLeaf())
                parent.R = null;
            else if (nodeToRm.R == null) {
                parent.R = nodeToRm.L;
            } else parent.R = nodeToRm.R;
        } else if (parent.L == nodeToRm) {
            if (nodeToRm.isLeaf())
                parent.L = null;
            else if (nodeToRm.R == null) {
                parent.L = nodeToRm.L;
            } else parent.L = nodeToRm.R;
        }
    }

    private Node searchParent(Node start, Node searched) {
        if ((start.L != null && start.L == searched) ||
                (start.R != null && start.R == searched)) return start;
        else if (start.R != null && searched.key > start.key) return searchParent(start.R, searched);
        else if (start.L != null && searched.key < start.key) return searchParent(start.L, searched);
        else return null;
    }


    public int[] getSorted() {
        if (this.sorted == null)
            this.sort();
        return sorted;
    }

    public int getLen() {
        return len;
    }

    public String getSortedString() {
        if (this.sorted != null)
            return Arrays.toString(this.sorted);
        return "Not sorted yet";
    }

    static class Node {
        int key;
        Node L, R;

        public Node(int key) {
            this.key = key;
            L = null;
            R = null;
        }

        boolean isLeaf() {
            return L == null && R == null;
        }

    }

}

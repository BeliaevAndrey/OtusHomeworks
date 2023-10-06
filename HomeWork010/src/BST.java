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
        if (key < node.key)
            node.L = insert(node.L, key);
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


    void remove(int x) {
        Node node = search(root, x);
        if (node == null) return;
        Node parent = searchParent(root, node);
        if (parent == null) {
            removeRoot();
        } else
            removeNode(parent, node);
        this.len--;
        this.sorted = null;
    }

    Node searchParent(Node startNode, Node endNode) {
        if (startNode.L == null && startNode.R == null) return null;
        if (startNode.L != null) {
            if (startNode.L == endNode) return startNode;
            if (startNode.key > endNode.key) return searchParent(startNode.L, endNode);
        }
        if (startNode.R != null) {
            if (startNode.R == endNode) return startNode;
            if (startNode.key < endNode.key) return searchParent(startNode.R, endNode);
        }
        return null;
    }

    void removeRoot() {
        if (root.L == null && root.R == null) {
            root = null;
            return;
        }
        if (root.L == null) {
            root = root.R;
            return;
        }
        if (root.R == null) {
            root = root.L;
            return;
        }
        if (root.L != null) {
            Node newRoot = root.L;
            Node np = root;
            while (newRoot.R != null) {
                np = newRoot;
                newRoot = newRoot.R;
            }
            root.key = newRoot.key;
            np.R = newRoot.L;
        } else {
            Node newRoot = root.R;
            Node np = root;
            while (newRoot.L != null) {
                np = newRoot;
                newRoot = newRoot.L;
            }
            root.key = newRoot.key;
            np.L = newRoot.R;
        }
    }

    void removeNode(Node parent, Node nodeToRemove) {
        if (nodeToRemove.L == null && nodeToRemove.R == null)
            if (nodeToRemove == parent.L) parent.L = null;
            else parent.R = null;
        else {
            if (nodeToRemove.L != null) {
                Node newNode = nodeToRemove.L;
                Node np = nodeToRemove;
                while (newNode.R != null) {
                    np = newNode;
                    newNode = newNode.R;
                }
                nodeToRemove.key = newNode.key;
                if (np.R == newNode)
                    np.R = newNode.L;
                else np.L = newNode.L;
            } else {
                Node newNode = nodeToRemove.R;
                Node np = nodeToRemove;
                while (newNode.L != null) {
                    np = newNode;
                    newNode = newNode.L;
                }
                nodeToRemove.key = newNode.key;
                if (np.L == newNode)
                    np.L = newNode.R;
                else np.R = newNode.R;
            }
        }
    }

    public int[] getSorted() {
        if (this.sorted == null)
            this.sort();
        return sorted;
    }

    static class Node {
        int key;
        BST.Node L, R;

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

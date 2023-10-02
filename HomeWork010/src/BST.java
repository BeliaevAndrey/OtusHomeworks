import java.util.Arrays;
import java.util.Random;

public class BST {
    public static void main(String[] args) {
        BST bst = new BST();
//        int[] A = bst.setRandom(100, 100);
//        int[] A = new int[]{83, 234, 304, 338, 400, 555, 789, 793, 878, 982};
//        int[] A = ("shuffle:\n" + Arrays.toString(bst.setShuffle(40, 20)) + "\n");

//        int[] A = new int[]{
//                50, 36, 33, 48, 23, 53, 58, 42, 46, 45, 21, 20, 39, 44, 40, 57, 54, 47, 52, 27,
//                38, 41, 43, 22, 32, 30, 59, 51, 37, 24, 31, 56, 35, 25, 29, 28, 49, 26, 55, 34
//        };

//        int[] A = new int[]{
//                65, 45, 105, 70, 64, 34, 87, 44, 73, 16, 53, 15, 31, 103, 32, 88, 107, 37, 94, 39,
//                83, 66, 92, 91, 58, 33, 49, 104, 26, 51, 86, 12, 21, 97, 36, 95, 29, 81, 13, 57,
//                74, 25, 102, 40, 77, 89, 106, 108, 18, 82, 35, 27, 101, 11, 76, 47, 79, 42, 43, 100,
//                90, 59, 61, 38, 75, 69, 60, 22, 28, 14, 99, 78, 96, 46, 67, 93, 30, 24, 41,
//                23, 54, 19, 10, 56, 72, 84, 109, 55, 71, 48, 20, 68, 80, 63, 50, 62, 98, 17, 85, 52
//        };

        int[] A = bst.setShuffle(20000, 10);
//        int[] A = new int[]{15, 27, 14, 16, 23, 22, 19, 24, 12, 18, 11, 25, 29, 10, 26, 13, 20, 28, 21, 17};
        bst.initSorting(A.length);

        int count = A.length;

        for (int val : A) {
            bst.insert(val);
        }
        for (int key : A) {
            boolean out = bst.search(key);
            if (!out) System.out.println(key);
        }

        System.out.println(Arrays.toString(A));
        for (int x : A) {
            boolean preproc = bst.search(x);
            System.out.printf("Before removing x=%d :\n", x);
//            bst.dfs(bst.root);
            bst.initAndSort(count);
            System.out.println(bst.getSortedString());
            bst.remove(x);
            count--;
            System.out.print("After removal:\n");
//            bst.dfs(bst.root);
            bst.initAndSort(count);
            System.out.println(bst.getSortedString());
            boolean aftermath = bst.search(x);
            System.out.println(" ".repeat(200) +
                    " " + preproc +
                    " " + aftermath + "\n");
        }
    }

    Node root;
    int[] sorted;
    int index;
    int len;

    public BST() {
        root = null;
    }

    public void initSorting(int len) {
        this.len = len;
    }

    public void sort() {
        this.index = 0;
        this.sorted = new int[len];
        dfs(root);
    }

    public void initAndSort(int len) {
        initSorting(len);
        sort();
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public Node insert(Node node, int key) {
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

    void dfs(Node node) {
        if (node == null) return;
        dfs(node.L);
//        System.out.print(node.key + ", ");
        this.sorted[index++] = node.key;
        dfs(node.R);
    }

    boolean search(int x) {
        Node node = search(root, x);
        return node != null && node.key == x;
    }

    Node search(Node node, int x) {
        if (node == null || node.key == x) return node;
        if (node.key > x) {
            return search(node.L, x);
        } else
            return search(node.R, x);
    }

    void removeRoot() {
        if (root.isLeaf()) {
            root = null;
        } else if (root.L != null && root.L.R == null) {
            root.L.R = root.R;
            root = root.L;
        } else if (root.R != null && root.R.L == null) {
            root.R.L = root.L;
            root = root.L;
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
                System.out.println("ROOT " + root.key + " " + root);
            } else root = root.R;
        }
    }


    void removeNode(Node nodeToRm, Node parent) {
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

    Node searchParent(Node start, Node searched) {
        if ((start.L != null && start.L == searched) ||
                (start.R != null && start.R == searched)) return start;
        else if (start.R != null && searched.key > start.key) return searchParent(start.R, searched);
        else if (start.L != null && searched.key < start.key) return searchParent(start.L, searched);
        else return null;
    }

    void remove(int x) {
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

    /*Utility section*/
    String getSortedString() {
        if (this.sorted != null)
            return Arrays.toString(this.sorted);
        return "Not sorted yet";
    }

    void printNode(String title, Node node) {
        System.out.println(title);
        System.out.println(" ".repeat(5) + node.key);
        if (node.L != null && node.R != null)
            System.out.println(node.L.key + " ".repeat(10) + node.R.key);
        else {
            if (node.L != null) System.out.println(node.L.key);
            if (node.R != null) System.out.println(" ".repeat(10) + node.R.key);
        }
    }

    int[] setRandom(int len, int lim) {
        Random rnd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = 10 + rnd.nextInt(lim - 10);
        }
        return arr;
    }

    int[] setShuffle(int len, int start) {
        Random rnd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = start + i;
            if (i > 0) {
                int k = rnd.nextInt(i);
                int tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
            }

        }
        return arr;
    }
}

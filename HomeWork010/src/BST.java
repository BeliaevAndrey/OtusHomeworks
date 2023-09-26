import java.util.Arrays;
import java.util.Random;

public class BST {

    public static void main(String[] args) {
        BST bst = new BST();
//        int[] A = bst.setRandom(100, 100);
//        int[] A = new int[]{83, 234, 304, 338, 400, 555, 789, 793, 878, 982};
//        int[] A = ("shuffle:\n" + Arrays.toString(bst.setShuffle(40, 20)) + "\n");

        int[] A = new int[]{
                38, 41, 43, 22, 32, 30, 59, 51, 37, 24, 31, 56, 35, 25, 29, 28, 49, 26, 55, 34,
                50, 36, 33, 48, 23, 53, 58, 42, 46, 45, 21, 20, 39, 44, 40, 57, 54, 47, 52, 27
        };
        for (int val : A) {
            bst.insert(val);
        }

        for (int key : A) {
            boolean out = bst.search(key);
            if (!out) System.out.println(key);
        }
        System.out.println(Arrays.toString(A));
        System.out.print("\n[");
        bst.dfs(bst.root);
        System.out.println("\b\b]");
        bst.remove(40);
        System.out.print("After removal:\n[");
        bst.dfs(bst.root);
        System.out.println("\b\b]");
    }

    Node root;

    public BST() {
        root = null;
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
        System.out.print(node.key + ", ");
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

//    void remove(int x) {
//        remove(root, 57);
//    }

    void remove(int x) {
        x = 56; // TODO: bug point
        boolean r_flag = false, l_flag = false;
        Node parent = root;
        Node node = root;
        while (x != node.key) {
            r_flag = l_flag = false;
            parent = node;
            if (x < node.key) {
                if (node.L != null) {
                    node = node.L;
                    l_flag = true;
                }
            } else {
                if (node.R != null) {
                    node = node.R;
                    r_flag = true;
                }
            }
        }
        if (node.L == null && node.R == null) {
            if (l_flag) parent.L = null;
            if (r_flag) parent.R = null;
        } else if (node.R == null) {
            parent.R = node.L;
        } else if (node.L == null) {
            parent.L = node.R;
        } else {
            parent = node;
            System.out.println(parent + " <- PARENT-> " + parent.key);  // TODO: remove semaphore
            node = node.L;
            Node current = node;
            while (current.R != null) {
                node = current;
                current = current.R;
            }
            parent.key = current.key;
            node.R = current.L;
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

    }

    /*Utility section*/
    int[] setRandom(int len, int lim) {
        Random rnd = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = rnd.nextInt(lim);
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

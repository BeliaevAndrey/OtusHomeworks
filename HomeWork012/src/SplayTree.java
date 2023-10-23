/*

Splay (расширение)
Основная операция дерева. Заключается в перемещении вершины в корень при помощи
последовательного выполнения трёх операций: Zig, Zig-Zig и Zig-Zag.
Обозначим вершину, которую хотим переместить в корень за x, её родителя — p,
а родителя p (если существует) — g.

Zig: выполняется, когда p является корнем. Дерево поворачивается по ребру между x и p.
Существует лишь для разбора крайнего случая и выполняется только один раз в конце,
когда изначальная глубина x была нечётна.

          P                           X
        /   \                      /     \
       X     C    =------>        A       P
     /   \                              /   \
    A     B                            B     C


Zig-zig
выполняется, когда и x, и p являются левыми (или правыми) сыновьями. Дерево поворачивается по
ребру между g и p, а потом — по ребру между p и x.

          G                      X                  X
        /   \                 /     \             /   \
       P     D               P       G           A      P
     /   \        =-->     /  \     /  \   =->        /  \
    X     C               A    B   C    D            B    G
  /   \                                                  /  \
 A     B                                                C    D

Zig-Zag: выполняется, когда x является правым сыном, а p — левым (или наоборот).
Дерево поворачивается по ребру между p и x, а затем — по ребру между x и g.

          G                      X
        /   \                 /     \
       P     D               P       G
     /   \        =-->     /  \     /  \
    A     X               A    B   C    D
        /   \
       C     D

*/


public class SplayTree {
    Node currentRoot;

    public SplayTree(int key) {
        this.currentRoot = new Node(key);
    }

// ========================================

    Node splay(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key > key) {
            if (root.L == null) return root;
            if (root.L.key > key) {
                root.L.L = splay(root.L.L, key);
                root = zig(root);
            } else if (root.L.key < key) {
                root.L.R = splay(root.L.R, key);
                if (root.L.R != null)
                    root.L = zag(root.L);
            }
            return (root.L == null) ? root : zig(root);
        } else {
            if (root.R == null) return root;
            if (root.R.key > key) {
                root.R.L = splay(root.R.L, key);
                if (root.R.L != null)
                    root.R = zig(root.R);
            } else if (root.R.key < key) {
                root.R.R = splay(root.R.R, key);
                root = zag(root);
            }
            return (root.R == null) ? root : zag(root);
        }
    }


    // =========================================
    Node zig(Node root) {   // right rotate
        Node child = root.L;
        root.L = child.R;
        child.R = root;
        return child;
    }

    Node zag(Node root) {   // left rotate (?)
        Node child = root.R;
        root.R = child.L;
        child.L = root;
        return child;
    }


    Node search() {
        return null;
    }

    void insert(int key) {
        if (currentRoot == null) currentRoot = new Node(key);
        currentRoot = splay(currentRoot, key);
        if (currentRoot.key == key) return;
        Node newNode = new Node(key);
        if (currentRoot.key > key) {
            newNode.R = currentRoot;
            newNode.L = currentRoot.L;
            currentRoot.L = null;
        } else {
            newNode.L = currentRoot;
            newNode.R = currentRoot.R;
            currentRoot.R = null;
        }
        currentRoot = newNode;
    }

    Node delete() {
        return null;
    }

    Node merge() {
        return null;
    }

    Node split() {
        return null;
    }

    private void printTree(Node root) {
        if (root == null) return;
        System.out.print(root.key + " ");
        printTree(root.L);
        printTree(root.R);
    }

    public void printTree() {
        if (currentRoot == null) return;
        printTree(currentRoot);
    }


    /* ********************************************* */
    static class Node {
        int key;

        Node P;
        Node L;
        Node R;

        public Node(int key) {
            this.key = key;
            P = L = R = null;
        }
    }
}

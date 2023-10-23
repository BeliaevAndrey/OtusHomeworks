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
    Node root;

    public SplayTree(int key) {
        this.root = new Node(key);
    }

// ========================================

    Node splay(int key) {
        if (root == null || root.key == key) return root;

        if (root.key > key) {
            if (root.L == null) return root;
            if (root.L.key > key) {
                // move root.L to root and return root
            } else {

            }
        }
        if (root.key < key) {
            if (root.R == null) return root;
        }

        return null;

    }


// =========================================
    Node zig(){
        return null;
    }

    Node zigZig(){
        return null;
    }

    Node zigZag(){
        return null;
    }

    Node search() {
        return null;
    }

    Node insert() {
        return null;
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

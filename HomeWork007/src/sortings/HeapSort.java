package sortings;

public class HeapSort {

    Integer[] array;
    int length;

    //
    // heap members: L = 2 * x + 1
    //               R = 2 * x + 2
    //               P = (x - 1) / 2
    //       P
    //       |
    //       X
    //     /   \
    //    L     R
    //

    public HeapSort() {
        this.array = new Integer[0];
        this.length = 0;
    }

    public void init(Integer[] array) {
        this.array = array;
        this.length = array.length;
    }

    public void sort() {
        for (int root = array.length / 2 - 1; root >= 0; root--) {
            heapify(root, array.length);
        }
        for (int j = array.length - 1; j > 0; j--) {
            swap(0, j);
            heapify(0, j);
        }
    }

    void heapify(int root, int size) {
        int L = 2 * root + 1;
        int R = 2 * root + 2;
        int X = root;
        if (L < size && array[L] > array[X]) X = L;
        if (R < size && array[R] > array[X]) X = R;
        if (X == root) return;
        swap(X, root);
        heapify(X, size);
    }

    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

}

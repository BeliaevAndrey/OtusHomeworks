package sortings;

public class SelectionSort {

    int[] array;
    int length;

    public SelectionSort() {
        this.array = new int[0];
        this.length = 0;
    }

    public void init(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    public void sort() {
        for (int j = length - 1; j >= 0; j--) {
            swap(getMax(j), j);
        }
    }

    int getMax(int j) {
        int maxIdx = 0;
        for (int i = 1; i <= j; i++) {
            if (array[i] > array[maxIdx])
                maxIdx = i;
        }
        return maxIdx;
    }

    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

}

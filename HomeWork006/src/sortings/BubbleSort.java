package sortings;

public class BubbleSort {

    Integer[] array;
    int length;

    public BubbleSort() {
        this.array = new Integer[0];
        this.length = 0;
    }

    public void init(Integer[] array) {
        this.array = array;
        this.length = array.length;
    }

    public void sort() {
        for (int i = length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1])
                    swap(j, j + 1);
    }

    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
}

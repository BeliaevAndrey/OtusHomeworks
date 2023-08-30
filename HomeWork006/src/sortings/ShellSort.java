package sortings;

public class ShellSort {

    int[] array;
    int length;

    public ShellSort() {
        this.array = new int[0];
        this.length = 0;
    }

    public void init(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    public void sort() {
        for (int gap = length / 2; gap > 0; gap /= 2)
            for (int j = gap; j < length; j++)
                for (int i = j; i >= gap && array[i - gap] > array[i] ; i -= gap)
                    swap(i - gap, i);
    }


    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

}

package sortings;

import util.ArrayGen;

import java.util.Arrays;

public class InsertionSort {

    int[] array;
    int length;

    public InsertionSort() {
        this.array = new int[0];
        this.length = 0;
    }

    public void init(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    public void sort() {
        for (int j = 1; j < length; j++)
            for (int i = j - 1; i >= 0 && array[i] > array[i + 1]; i--)
                swap(i, i + 1);
    }

    public void shiftSort() {
        int i;
        for (int j = 1; j < length; j++) {
            int buffer = array[j];
            for (i = j - 1; i >= 0 && array[i] > buffer; i--) {
                array[i + 1] = array[i];
            }
            array[i + 1] = buffer;
        }
    }

    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

}

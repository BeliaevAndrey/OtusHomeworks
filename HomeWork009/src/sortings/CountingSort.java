package sortings;

public class CountingSort {

    int[] A;
    int N;
    int[] buffer;


    public void init(int[] arr) {
        this.N = arr.length;
        this.A = arr;
        this.buffer = new int[N];
    }

    private int bufferAndFindMax() {
        int max = A[0];
        for (int i = 0; i < N; i++) {
            this.buffer[i] = A[i];
            if (A[i] > max)
                max = A[i];
        }
        return max;
    }

    public void sort() {
        int[] countArr = new int[bufferAndFindMax() + 1];

        for (int i = 0; i < N; i++)
            countArr[buffer[i]]++;

        for (int i = 1; i < countArr.length; i++) {
            if (countArr[i] == 0)
                countArr[i] = countArr[i - 1];
            else countArr[i] += countArr[i - 1];
        }
        for (int i = N - 1; i >= 0; i--)
            if (countArr[buffer[i]] > 0)
                A[--countArr[buffer[i]]] = buffer[i];
    }
}

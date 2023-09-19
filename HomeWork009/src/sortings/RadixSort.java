package sortings;


public class RadixSort {
    int[] A;
    int N;
    int[] buffer;
    int max = Integer.MIN_VALUE;
    int walks;


    public void init(int[] arr) {
        this.N = arr.length;
        this.A = arr;
        this.buffer = new int[N];
        for (int i = 0; i < N; i++) {
            if (max < A[i]) max = A[i];
            buffer[i] = A[i];
        }
        this.walks = (int) Math.log10(max) + 1;
    }

    void buffering() {
        for (int i = 0; i < N; i++) {
            buffer[i] = A[i];
        }
    }

    public void sort() {
        int denominator = 10;
        while (walks > 0) {
            int[] countArr = new int[10];

            for (int i = 0; i < N; i++)
                countArr[buffer[i] % denominator / (denominator / 10)]++;

            for (int i = 1; i < countArr.length; i++) {
                if (countArr[i] == 0) countArr[i] = countArr[i - 1];
                else countArr[i] += countArr[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                if (countArr[buffer[i] % denominator / (denominator / 10)] > 0)
                    A[--countArr[buffer[i] % denominator / (denominator / 10)]] = buffer[i];
            }

            buffering();
            walks -= 1;
            denominator *= 10;
        }
    }
}

import java.util.Random;

public class CountingSort {

    int A[];
    int N;

    public static void main(String[] args) {
        CountingSort cs = new CountingSort();
        int len = (int) 1e8;
        cs.init(len, cs.setRandom(len, len));
        cs.sort();
        System.out.println(cs.isAscending());
    }


    void init(int N, int[] arr) {
        this.N = N;
        this.A = arr;
    }

    int findMax() {
        int max = A[0];
        for (int i = 1; i < N; i++)
            if (A[i] > max)
                max = A[i];
        return max;
    }

    void sort() {
        int[] countArr = new int[findMax() + 1];
        int[] tmp = new int[N];

        for (int i = 0; i < N; i++)
            countArr[A[i]]++;

        for (int i = 1; i < countArr.length; i++) {
            if (countArr[i] == 0)
                countArr[i] = countArr[i - 1];
            else countArr[i] += countArr[i - 1];
        }
        for (int i = N - 1; i >= 0; i--)
            if (countArr[A[i]] > 0)
                tmp[--countArr[A[i]]] = A[i];

        A = tmp;
    }

    int[] setRandom(int N, int upperLim) {
        int[] arr = new int[N];
        Random rnd = new Random(1234);
        for (int i = 0; i < N; i++)
            arr[i] = rnd.nextInt(upperLim);
        return arr;
    }

    boolean isAscending() {
        for (int i = 1; i < N; i++) {
            if (A[i - 1] > A[i]) return false;
        }
        return true;
    }


}

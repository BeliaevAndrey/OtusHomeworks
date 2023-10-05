package sortings;

public class QuickSort implements ISorter{


    int[] arr;
    int N;

    public void init(int[] array) {
        arr = array;
        N = array.length;
    }

    public void sort() {
        qSort(0, N - 1);
    }

    void qSort(int L, int R) {
        if (L >= R) return;
        int M = split(L, R);
        qSort(L, M - 1);
        qSort(M + 1, R);


    }

    int split(int L, int R) {
        int P = arr[R];
        int M = L - 1;
        for (int j = L; j <= R; j++) {
            if (arr[j] <= P) swap(++M, j);
        }
        return M;
    }

    void swap(int lt, int rt) {
        int tmp = arr[lt];
        arr[lt] = arr[rt];
        arr[rt] = tmp;
    }

}

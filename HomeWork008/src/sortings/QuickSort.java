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

    boolean isAscending() {
        boolean flag = true;
        for (int i = 1; i < arr.length; i++) {
            String format = "%5s%5s%12s%12s\n";
            if (arr[i - 1] > arr[i]) {
                if (flag) System.out.printf(format, "i - 1", "i", "arr[i-1]", "arr[i]");
                System.out.printf(format, (i - 1), i, arr[i - 1], arr[i]);
                flag = false;
            }
        }
        return flag;
    }
}

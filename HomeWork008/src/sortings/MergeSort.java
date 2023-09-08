package sortings;


public class MergeSort {
    int[] arr;
    int N;


    public void init(int[] array) {
        arr = array;
        N = array.length;
    }

    public void mergeSort() {
        mSort(0, arr.length - 1);
    }

    void mSort(int L, int R) {
        if (L >= R) return;
        int M = (L + R) / 2;
        mSort(L, M);
        mSort(M + 1, R);
        merge(L, M, R);
    }

    void merge(int L, int M, int R) {
        int[] T = new int[R - L + 1];
        int a = L;
        int b = M + 1;
        int m = 0;
        while (a <= M && b <= R) {
            if (arr[a] > arr[b])
                T[m++] = arr[b++];
            else
                T[m++] = arr[a++];
        }
        while (a <= M) {
            T[m++] = arr[a++];
        }
        while (b <= R) {
            T[m++] = arr[b++];
        }
        for (int i = L; i <= R; i++) {
            arr[i] = T[i - L];
        }
    }

    public boolean isAscending() {
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

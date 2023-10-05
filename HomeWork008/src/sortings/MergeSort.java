package sortings;


public class MergeSort implements ISorter{
    int[] arr;
    int N;


    public void init(int[] array) {
        arr = array;
        N = array.length;
    }

    public void sort() {
        mSort(0, arr.length - 1);
    }

    void mSort(int L, int R) {
        if (L >= R) return;
        int M = (L + R) / 2;
        mSort(L, M);
        mSort(M + 1, R);
        merge(L, M, R);
    }

    private void merge(int L, int M, int R) {
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

}

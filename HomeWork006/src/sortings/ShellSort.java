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
                for (int i = j; i >= gap && array[i - gap] > array[i]; i -= gap)
                    swap(i - gap, i);
    }

    public void sortGaps(int[] gaps) {
        for (int gap : gaps)
            for (int j = gap; j < length; j++)
                for (int i = j; i >= gap && array[i - gap] > array[i]; i -= gap)
                    swap(i - gap, i);
    }
    public void sortCiura() {
        int[] gapsCiura = {701, 301, 132, 57, 23, 10, 4, 1};   // Ciura gap sequence
        sortGaps(gapsCiura);
    }
    public void sortKnuth(int N) {     // Knuth gap sequence
        int len = (int) Math.ceil(Math.log(N) / Math.log(3));
        int[] gaps = new int[len];
        int limit = N / 3;
        int gap = 0;
        for (int k = 1; gap < limit; k++) {
            gap = (int)((Math.pow(3, k) - 1) / 2);
            gaps[len - k] = gap;
        }
        sortGaps(gaps);
    }

    public void sortSedgewick86(int N) {    // Sedgewick gap sequence (1986)
        int len = (int) Math.ceil(Math.log10(N) / Math.log10(2));
        int[] gaps = new int[len];
        double sqrtOfTwo = Math.pow(2, 0.5);
        int gap = 0;
        for (int k = 0; gap < N / 2; k += 2) {
            gap = (int) Math.ceil(9 * (Math.pow(2, k) - Math.pow(sqrtOfTwo, k)) + 1);
            gaps[len - k - 1] = gap;
        }
        gap = 0;
        for (int k = 1; gap < N / 2; k += 2) {
            gap = (int) Math.ceil(8 * Math.pow(2, k) - 6 * Math.pow(sqrtOfTwo, (k + 1))) + 1;
            gaps[len - k - 1] = gap;
        }
        sortGaps(gaps);
    }



    void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

}

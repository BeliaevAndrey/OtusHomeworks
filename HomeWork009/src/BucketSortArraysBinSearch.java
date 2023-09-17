import java.util.Arrays;
import java.util.Random;

public class BucketSortArraysBinSearch {
    int[] arr;
    int N;
    int upperLim;

    Bucket[] buckets = new Bucket[0];

    public static void main(String[] args) {
        BucketSortArraysBinSearch bs = new BucketSortArraysBinSearch();
        bs.setRandom(10_000_000, 99999);
        System.out.println(Arrays.toString(bs.arr));
        bs.sort();
        System.out.println(Arrays.toString(bs.arr));
        System.out.println(bs.isAscending());
    }

    void sort() {
        for (int i = 0; i < N; i++) {
            int bn = arr[i] / 100;
            setBucket(bn, arr[i]);
        }
        int index = 0;
        for (Bucket b : buckets) {
            if (b != null)
                for (int i = 0; i < b.values.length; i++) {
                    arr[index] = b.values[i];
                    index++;
                }
        }
    }

    void setBucket(int number, int value) {
        if (number < buckets.length) {
            if (buckets[number] == null) {
                buckets[number] = new Bucket(number);
            }
            buckets[number].put(value);
        } else {
            Bucket[] newBuckets = new Bucket[number + 1];
            for (int i = 0; i < buckets.length; i++) {
                newBuckets[i] = buckets[i];
            }
            buckets = newBuckets;
            buckets[number] = new Bucket(number);
            buckets[number].put(value);
        }
    }


    public BucketSortArraysBinSearch(int[] arr) {
        this.N = arr.length;
        this.arr = arr;
    }


    public BucketSortArraysBinSearch(int N, int upperLim) {
        this.N = N;
        this.upperLim = upperLim;
    }

    public BucketSortArraysBinSearch() {
        this.N = 20;
        this.upperLim = 999;
        this.arr = new int[N];
    }

    void setRandom(int N, int newLim) {
        this.N = N;
        this.arr = new int[N];
        this.upperLim = newLim;
        Random rnd = new Random(12345);
        for (int i = 0; i < N; i++) {
            arr[i] = rnd.nextInt(upperLim);
        }
    }

    void setRandom() {
        setRandom(N, upperLim);
    }

    boolean isAscending() {
        boolean flag = true;
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.printf("[i - 1] %d value %d; [i] %d value %d\n", i - 1, arr[i - 1], i, arr[i]);
                flag = false;
            }
        }
        return flag;
    }

    class Bucket {
        int[] values;
        int number;

        public Bucket(int number) {
            this.number = number;
            this.values = new int[0];
        }

        void put(int newVal) {
            int[] newValues = new int[this.values.length + 1];
            int l = 0, r = values.length, m = (l + r) / 2;
            while (l < r) {
                if (values[m] < newVal)
                    l = m + 1;
                else r = m;
                m = (l + r) / 2;
            }
            for (int i = 0; i < m; i++) {
                newValues[i] = values[i];
            }
            newValues[m] = newVal;
            for (int i = m + 1; i < newValues.length; i++) {
                newValues[i] = values[i - 1];
            }
            values = newValues;
        }

        int[] getValues() {
            return values;
        }
    }
}

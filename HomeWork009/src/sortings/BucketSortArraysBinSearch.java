package sortings;

import java.util.Random;

public class BucketSortArraysBinSearch {
    int[] A;
    int N;

    Bucket[] buckets = new Bucket[0];


    public void init(int[] arr) {
        this.N = arr.length;
        this.A = arr;
    }
    public void sort() {
        for (int i = 0; i < N; i++) {
            int bn = A[i] / 100;
            setBucket(bn, A[i]);
        }
        int index = 0;
        for (Bucket b : buckets) {
            if (b != null)
                for (int i = 0; i < b.values.length; i++) {
                    A[index] = b.values[i];
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


    boolean isAscending() {
        boolean flag = true;
        for (int i = 1; i < N; i++) {
            if (A[i - 1] > A[i]) {
                System.out.printf("[i - 1] %d value %d; [i] %d value %d\n", i - 1, A[i - 1], i, A[i]);
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

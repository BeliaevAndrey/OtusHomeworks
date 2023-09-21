package sortings;


public class BucketSortSeminar {
    int N;
    int[] A;

    public void init(int[] arr) {
        N = arr.length;
        A = arr;
    }

    public void Bucket() {
        int max = A[0];
        for (int a : A)
            if (a > max)
                max = a;
        max++;
        BuckList[] bucket = new BuckList[N];
        for (int a : A) {
            int nr = (int)((long) a * (long) N / (long) max);
            bucket[nr] = new BuckList(a, bucket[nr]);
            BuckList item = bucket[nr];
            while (item.next != null) {
                if (item.value <= item.next.value)
                    break;
                int x = item.value;
                item.value = item.next.value;
                item.next.value = x;
                item = item.next;
            }
        }
        int j = 0;
        for (BuckList item : bucket) {
            while (item != null) {
                A[j++] = item.value;
                item = item.next;
            }
        }
    }

    class BuckList {

        public int value;
        BuckList next;

        public BuckList(int value, BuckList next) {
            this.value = value;
            this.next = next;
        }
    }
}
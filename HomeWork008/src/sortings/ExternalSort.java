package sortings;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;


public class ExternalSort {
    int[] arr;
    int N;

    public static void main(String[] args) {
        ExternalSort es = new ExternalSort();
        int lim = 61;
        es.init(es.setRandom(lim, lim));
        System.out.println(Arrays.toString(es.arr));
        es.externalSort();
        System.out.println(Arrays.toString(es.arr));

    }

    void externalSort() {
        MergeSort ms = new MergeSort();
        QuickSort qs = new QuickSort();
        ListLocal bufferA = new ListLocal();
        ListLocal bufferB = new ListLocal();
        int parity = 0;
        for (int i = 0; i < N; i += N / 4) {
            int len = (i + N / 4 < N) ? N / 4 : N - i;

            int[] tmp = new int[len];
            for (int j = 0; j < len; j++)
                tmp[j] = arr[i + j];
            qs.init(tmp);
            qs.qSort(0, tmp.length - 1);
            System.out.println(Arrays.toString(tmp));
            if ((parity & 1) == 0) buffering(tmp, bufferA, len);
            else buffering(tmp, bufferB, len);
            parity++;
//            System.out.println("A: " + bufferA.getString());
//            System.out.println("B: " + bufferB.getString());
        }

        mergeBuffers(bufferA, bufferB);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = bufferA.pop();
        }
    }

    void buffering(int[] tmp, ListLocal buffer, int len) {
        for (int i = 0; i < len; i++) {
            buffer.put(tmp[i]);
        }
    }

    void mergeBuffers(ListLocal A, ListLocal B) {
        System.out.println("A: " + A.getString());
        System.out.println("B: " + B.getString());
        if (B.isEmpty()) return;
        ListLocal resulting = new ListLocal();
        ListLocal bufferLoc = new ListLocal();
        boolean flag = true;
        int aTmp = A.pop();
        int bTmp = B.pop();
        while (!A.isEmpty() && !B.isEmpty()) {
            if (aTmp < bTmp && aTmp >= resulting.peekLast()) {
                if (flag) resulting.put(aTmp);
                else bufferLoc.put(aTmp);
                aTmp = A.pop();
            } else if (bTmp < aTmp && bTmp >= resulting.peekLast()) {
                if (flag) resulting.put(bTmp);
                else bufferLoc.put(aTmp);
                bTmp = B.pop();
            } else {
                flag = false;
            }
        }
        System.out.println("cbL: " + bufferLoc.isEmpty());
        System.out.println("cbR: " + resulting.isEmpty());
        mergeBuffers(resulting, bufferLoc);
    }

    void init(int[] array) {
        this.arr = array;
        this.N = array.length;
    }

    public int[] setRandom(int len, int uLim) {
        int[] newArr = new int[len];
        Random rnd = new Random(12345);
        for (int i = 0; i < len; i++) {
            newArr[i] = rnd.nextInt(uLim);
        }
        return newArr;
    }

    void testStack() {
        ListLocal stack = new ListLocal();
        for (int i = 0; i < N; i++) {
            stack.put(i);
        }
        int count = 0;
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
            count++;
        }
        System.out.println("\ncount: " + count);
    }

}


class ListLocal {
    NodeLocal head;
    NodeLocal tail;


    void put(int data) {
        if (head == null) {
            head = new NodeLocal(data);
        } else {
            NodeLocal node = new NodeLocal(data);
            node.next = head;
            head.prev = node;
            head = node;
            if (tail == null) {
                tail = node.next;
            }

        }
    }

    int pop() {
        if (tail == null)
            throw new EmptyStackException();
        int data = tail.data;
        tail.next = null;
        tail = tail.prev;
        return data;
    }

    int peekLast() {
        if (isEmpty()) throw new EmptyStackException();
        return tail.data;
    }

//    int popFirst() {
//        if (head == null)
//            throw new EmptyStackException();
//        int data = head.data;
//        head.prev = null;
//        head = head.next;
//        return data;
//    }

    boolean isEmpty() {
        return tail == null;
    }


    public String getString() {
        StringBuilder sb = new StringBuilder();
        NodeLocal current = tail;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.prev;
        }
        return sb.toString();
    }


    class NodeLocal {
        int data;
        NodeLocal next;
        NodeLocal prev;

        public NodeLocal(int data) {
            this.data = data;
        }
    }
}

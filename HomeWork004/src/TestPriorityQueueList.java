import queues.PriorityQueueList;

import java.util.Random;

public class TestPriorityQueueList {

    public static void main(String[] args) {
        TestPriorityQueueList tester = new TestPriorityQueueList();
        PriorityQueueList<String> pq = new PriorityQueueList<>();
        tester.testEnq(pq, 20);
        System.out.println(pq);
        try {
            tester.testDeq(pq);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(pq);

    }

    void testEnq(PriorityQueueList<String> pq, int total) {
        Random rnd = new Random();
        int prLim = total / 2;
        for (int i = 0; i < total; i++) {
            int priority = rnd.nextInt(prLim);
            pq.enqueue(priority, "tester " + i);
        }
    }

    void testDeq(PriorityQueueList<String> pq) {
        String item;
        int count = 1;
        while ((item = pq.dequeue()) != null) {
            System.out.println("dequeuing: " + count + " " + item);
            count++;
        }
    }
}

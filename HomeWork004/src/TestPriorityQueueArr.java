import java.util.Random;

import queues.PriorityQueueArr;

public class TestPriorityQueueArr {
    public static void main(String[] args) {
        TestPriorityQueueArr tester = new TestPriorityQueueArr();
        PriorityQueueArr <String> pqa = new PriorityQueueArr<>(5);
        tester.testAddPQ(pqa, 20);
        tester.testGetPQ(pqa);
    }

    void testAddPQ(PriorityQueueArr<String> q, int limit){
        Random rnd = new Random();
        for (int i = 0; i < limit; i++) {
            int p = 1 + rnd.nextInt(q.priorities());
            System.out.println("priority=" + p + ";\t item=" + i);
            q.enqueue(p, String.valueOf(i));
        }
    }

    void testGetPQ(PriorityQueueArr<String> pq){
        while (!pq.isEmpty()){
            System.out.println(pq.dequeue());
        }
    }


}

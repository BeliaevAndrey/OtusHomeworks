package queues;

public interface IPriorityQueue<T> {

    void enqueue(int priority, T item);

    T dequeue();

    boolean isEmpty();

    int priorities();
}

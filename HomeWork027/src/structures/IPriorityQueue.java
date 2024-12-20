package structures;

public interface IPriorityQueue<T> {

    void enqueue(int priority, T item);

    T dequeue();

    T poll();

    boolean isEmpty();

    int priorities();

    int size();
}

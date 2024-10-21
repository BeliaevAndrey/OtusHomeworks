package structures;


public interface IQueue<T> {

    T enqueue(T o);
    T dequeue();
    int size();
    boolean isEmpty();
    void clear();

}

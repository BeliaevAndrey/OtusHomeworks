package structures;

public interface IStack<T> {

    T pop();

    T peek();

    T push(T o);

    boolean isEmpty();

    int size();

    void clear();

}

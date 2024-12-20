package structures;

public interface IArray<T> {
    int size();

    void add(T item);

    T get(int index);

    // HW
    void add(T item, int index); // with shift to tail

    T remove(int index); // return deleted element

    boolean isEmpty();
}

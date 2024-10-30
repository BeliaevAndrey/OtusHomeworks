package structures;

public class CustomQueue<T extends Comparable<T>> implements IQueue<T> {

    CustomLinkedList<T> storage;

    public CustomQueue() {
        storage = new CustomLinkedList<>();
    }

    @Override
    public T enqueue(T o) {
        storage.add(o);
        return o;
    }

    @Override
    public T dequeue() {
        if (storage.isEmpty()) throw new IllegalStateException("Dequeue from empty queue.");
        T o = storage.get(0);
        storage.remove(0);
        return o;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}

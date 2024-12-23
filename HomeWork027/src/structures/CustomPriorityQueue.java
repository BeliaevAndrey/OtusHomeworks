package structures;


public class CustomPriorityQueue<T> implements IPriorityQueue<T> {

    IArray<IArray<T>> arrays;
    private int priorities;
    int size;


    public CustomPriorityQueue(int priorities) {
        this.priorities = priorities;
        this.size = 0;
        arrays = new CustomSingleArray<>();
        for (int i = 0; i < priorities; i++) {
            arrays.add(new CustomSingleArray<>());
        }
    }

    @Override
    public void enqueue(int priority, T item) {
        if (priority > priorities || priority < 1)
            throw new IllegalArgumentException(String.format("Priority exceeds limits: [1, %d]", priorities));
        arrays.get(priority - 1).add(item, 0);
        size++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty())
            return null;
        T item;
        for (int p = priorities - 1; p >= 0 ; p--) {
            IArray<T> array = arrays.get(p);
            if (!array.isEmpty() && (item = array.remove(array.size() - 1)) != null) {
                size--;
                return item;
            }
        }
        return null;
    }

    @Override
    public T poll() {
        if (this.isEmpty())
            return null;
        T item;
        for (int p = 0; p < priorities ; p++) {
            IArray<T> array = arrays.get(p);
            if (!array.isEmpty() && (item = array.remove(array.size() - 1)) != null) {
                size--;
                return item;
            }
        }
        return null;
    }

    @Override
    public int priorities() { return this.priorities; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public int size() { return this.size; }
}

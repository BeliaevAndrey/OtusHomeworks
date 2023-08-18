package model;

public class VectorArray<T> implements IArray<T> {

    private Object[] array;
    private int vector;
    private int size;

    public VectorArray(int vector) {
        this.vector = vector;
        array = new Object[0];
        size = 0;
    }

    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // HW
    @Override
    public void add(T item, int index) {
        Object[] newArray = new Object[size() + 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = this.array[i];
        }
        newArray[index] = item;
        for (int i = index + 1; i < size() + 1; i++) {
            newArray[i] = this.array[i - 1];
        }
        size++;
        this.array = newArray;

    }

    @Override
    public T remove(int index) {
        Object[] newArray = new Object[size() - 1];
        T tmp = (T) this.array[index];
        for (int i = 0; i < index; i++) {
            newArray[i] = this.array[i];
        }
        for (int i = index + 1; i < size(); i++) {
            newArray[i - 1] = this.array[i];
        }
        size--;
        this.array = newArray;
        return tmp;
    }

}

package model;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector)
            array.add(new SingleArray<>());
        array.get(size / vector).add(item);
        size++;
    }

    @Override
    public T get(int index) {
        return array.get(index / vector).get(index % vector);
    }

    // HW
    @Override
    public void add(T item, int index) {
        if (this.array.size() * vector == this.size)
            array.add(new SingleArray<>());
        int currentArr = index / vector;
        int currentItem = index % vector;
        array.get(currentArr).add(item, currentItem);
        size++;
        for (int arr = currentArr; arr < array.size() - 1; arr++) {
            this.array.get(arr + 1).add(array.get(arr).remove(array.get(arr).size() - 1), 0);
        }
    }

    @Override
    public T remove(int index) {
        int currentArr = index / vector;
        int currentItem = index % vector;
        T tmp = this.array.get(currentArr).get(currentItem);
        this.array.get(currentArr).remove(currentItem);
        size--;
        for (int arrPos = currentArr; arrPos < array.size() - 1; arrPos++) {
            this.array.get(arrPos).add(array.get(arrPos + 1).remove(0), array.get(arrPos).size());
        }
        return tmp;
    }

}

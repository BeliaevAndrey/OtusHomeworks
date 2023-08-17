package model;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray () {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
//        for (int j = 0; j < size(); j ++)
//            newArray[j] = array[j];
        array = newArray;
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
        this.array = newArray;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index){
        Object[] newArray = new Object[size() - 1];
        T tmp = (T) this.array[index];
        for (int i = 0; i < index; i++) {
            newArray[i] = this.array[i];
        }
        for (int i = index + 1; i < size(); i++) {
            newArray[i - 1] = this.array[i];
        }
        this.array = newArray;
        return tmp;
    }
}

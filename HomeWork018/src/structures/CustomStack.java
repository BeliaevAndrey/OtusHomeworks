package structures;

import java.util.EmptyStackException;

public class CustomStack implements IStack{

    private Object[] objects;

    private int count = -1;
    private int capacity = 10;

    public CustomStack() {
        objects = new Object[capacity];
    }

    private void resize() {
        Object[] tmp = new Object[capacity + 10];
        capacity += 10;
        for (int i = 0; i <= count; i++) tmp[i] = objects[i];
        objects = tmp;
    }

    private void truncate() {
        Object[] tmp = new Object[capacity - 10];
        capacity -= 10;
        for (int i = 0; i <= count; i++) tmp[i] = objects[i];
        objects = tmp;
    }



    @Override
    public Object pop() {
        if (count == -1) throw new EmptyStackException();
        Object out = objects[count];
        objects[count] = null;
        count--;
        if (capacity == count + 11) truncate();
        return out;
    }

    @Override
    public Object peek() {
        if (count == -1) throw new EmptyStackException();
        return objects[count];
    }

    @Override
    public Object push(Object o) {
        if (count + 1 == capacity) { resize(); }
        objects[++count] = o;
        return o;
    }

    @Override
    public boolean isEmpty() {
        return count == -1;
    }

    public int size() {return count;}

    public void clear() {
        while (!isEmpty()) pop();
    }
}

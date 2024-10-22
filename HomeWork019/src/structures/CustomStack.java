package structures;

import java.util.EmptyStackException;

public class CustomStack<T> implements IStack<T>{

    private Node<T>[] nodes;

    private int count = -1;
    private int capacity = 10;

    public CustomStack() {
        nodes = new Node[capacity];
    }

    private void resize() {
        Node<T>[] tmp = new Node[capacity + 10];
        capacity += 10;
        for (int i = 0; i <= count; i++) tmp[i] = nodes[i];
        nodes = tmp;
    }

    private void truncate() {
        Node<T>[] tmp = new Node[capacity - 10];
        capacity -= 10;
        for (int i = 0; i <= count; i++) tmp[i] = nodes[i];
        nodes = tmp;
    }



    @Override
    public T pop() {
        if (count == -1) throw new EmptyStackException();
        T out = nodes[count].data;
        nodes[count] = null;
        count--;
        if (capacity == count + 11) truncate();
        return out;
    }

    @Override
    public T peek() {
        if (count == -1) throw new EmptyStackException();
        return nodes[count].data;
    }

    @Override
    public T push(T obj) {
        if (count + 1 == capacity) { resize(); }
        nodes[++count] = new Node(obj);
        return obj;
    }

    @Override
    public boolean isEmpty() {
        return count == -1;
    }

    @Override
    public int size() { return count; }

    public void clear() {
//        while (!isEmpty()) pop();
        this.nodes = new Node[capacity];
        count = -1;
    }

    private static class Node<E> {
        E data;
        Node(E data) { this.data = data; }
    }
}

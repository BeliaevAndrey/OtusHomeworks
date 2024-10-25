package structures;

public class CustomLinkedList<T extends Comparable<T>> {
    Node<T> head;
    private int size;

    public CustomLinkedList() {
        this.head = new Node<>(null);
    }

    Node<T> search(int index) {
        if (size == 0) throw new IllegalStateException("Empty list.");
        Node<T> current = this.head;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current;
    }

    public void add(int index, T item) {
        Node<T> newNode = new Node<>(item);
        if (index == 0) {
            head.data = item;
            size++;
            return;
        }
        if (index < 0)
            throw new IllegalArgumentException("Negative index value");
        if (index > this.size + 1)
            throw new IndexOutOfBoundsException(index + " for size " + size);
        Node<T> node = search(index - 1);
        newNode.next = node.next;
        node.next = newNode;
        this.size++;
    }

    public void add(T item) {
        add(size, item);
    }

    public T get(int index) {
        return search(index).data;
    }

    public T remove(int index) {
        T out = head.data;
        if (index == 0) {
            head = head.next;
            size--;
            if (head == null) head = new Node<>(null);
            return out;
        }
        Node<T> node = search(index - 1);
        out = node.next.data;
        node.next = node.next.next;
        size--;
        return out;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = new Node<>(null);
        this.size = 0;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    public void reverse() {
        CustomLinkedList<T> tmp = new CustomLinkedList<>();
        int index = size - 1;
        Node<T> current = head;
        while (index > -1) {
            tmp.add(get(index--));
            current = current.next;
        }
        this.head = tmp.head;
    }

    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            int pos = i;
            for (int j = i; j < size; j++) {
                if (search(j).compareTo(search(pos)) < 0) pos = j;
            }
            if (pos != i) swapData(i, pos);
        }
    }

    void swapData(int l, int r) {
        T tmp = get(l);
        search(l).data = search(r).data;
        search(r).data = tmp;
    }




    static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        Node<E> next;
        E data;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public int compareTo(Node<E> o) {
            return data.compareTo(o.data);
        }
    }
}

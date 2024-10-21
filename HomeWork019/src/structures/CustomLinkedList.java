package structures;

public class CustomLinkedList<T> {
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

    class Node<T> {
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }
}

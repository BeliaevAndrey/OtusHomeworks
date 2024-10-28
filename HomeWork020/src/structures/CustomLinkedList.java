package structures;

public class CustomLinkedList<T extends Comparable<T>> {
    private Node<T> head;

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

    public void add(T item) { add(size, item); }

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

    public int size() { return this.size; }

    public void clear() {
        this.head = new Node<>(null);
        this.size = 0;
    }

    public boolean isEmpty() { return size() == 0; }

    public void reverse() {
        if (size == 0) throw new IllegalStateException("Empty list exception");
        CustomLinkedList<T> tmp = new CustomLinkedList<>();
        int index = size - 1;
        Node<T> current = head;
        while (index > -1) {
            tmp.add(get(index--));
            current = current.next;
        }
        this.head = tmp.head;
    }


    // sortings

    // region selection sort (unstable)
    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < size; j++) {
                if (search(j).compareTo(search(pos)) < 0) pos = j;
            }
            if (pos != i) swapData(i, pos);
        }
    }

    private void swapData(int l, int r) {
        T tmp = get(l);
        search(l).data = search(r).data;
        search(r).data = tmp;
    }

    // endregion

    // stable
    public void sort() {
        int lim =  size * (size - 1) / 2;
        for (int i = 0; i < lim; i++) {
            Node<T> node = head;
            int l = 0;
            while (node.next != null) {
                if (node.compareTo(node.next) > 0) {
                    swapNodes(l, l + 1);
                    break;
                }
                node = node.next;
                l++;
            }
            if (i % 10 == 0 && isAscending()) { return; }
        }
    }

    void swapNodes(int l, int r) {
        insert(l, pop(r));
        insert(r, pop(l + 1));

    }

    private void insert(int index, Node<T> nodeIn) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Index is out of bounds for size: " + size);
        if (index == 0) {
            nodeIn.next = head;
            head = nodeIn;
            size++;
            return;
        }
        Node<T> node = search(index - 1);
        nodeIn.next = node.next;
        node.next = nodeIn;
        size++;
    }

    private Node<T> pop(int index) {
        if (size == 0) throw new IllegalStateException("Pop from empty list");
        Node<T> out = head;
        if (index == 0) {
            head = head.next;
            size--;
            if (head == null) head = new Node<>(null);
            return out;
        }
        Node<T> node = search(index - 1);
        out = node.next;
        node.next = node.next.next;
        size--;
        return out;
    }


    // check if list is sorted in ascending order
    public boolean isAscending() {
        Node<T> node = head;
        while (node.next != null) {
            if (node.compareTo(node.next) > 0) return false;
            node = node.next;
        }
        return true;
    }




    // Nested =================================================================
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

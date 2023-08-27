package queues;

public class PriorityQueueList<T> implements IPriorityQueue<T> {

    PriorityNode<T> head;
    int priorities;

    public PriorityQueueList() {
        this.head = new PriorityNode<>(0, null);
        priorities = 0;
    }


    @Override
    public void enqueue(int priority, T item) {
        PriorityNode<T> newNode = new PriorityNode<>(priority, item);
        if (this.priorities < priority) this.priorities = priority;
        if (head.data == null) {
            head = newNode;
            return;
        }

        if (head.priority < priority){
            newNode.next = head;
            this.head = newNode;
            return;
        }
        PriorityNode<T> current = head;

        while (current.next != null) {
            if (current.next.priority < priority) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
        current.next = newNode;

    }

    @Override
    public T dequeue() {
        if (this.head.data == null) {
            return null;
        }
        if (this.head.next == null) {
            T item = head.data;
            head.data = null;
            return item;
        }
        T item = head.data;
        head = head.next;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int priorities() {
        return this.priorities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        PriorityNode<T> current = this.head;
        while (current != null) {
            sb.append("pr: ").append(current.priority).append("; item: ").append(current.data).append(", ");
            current = current.next;
        }
        return sb.toString();
    }
}

class PriorityNode<T> {

    PriorityNode<T> next;
    int priority;
    T data;

    public PriorityNode(int priority, T data) {
        this.data = data;
        this.priority = priority;
    }
}
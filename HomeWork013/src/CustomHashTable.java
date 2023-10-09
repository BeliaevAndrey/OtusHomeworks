public class CustomHashTable {
    Node[] slots;

    int len;

    public CustomHashTable() {
        this.slots = new Node[10];
        len = 0;
    }

    public void put(String key, int value) {
        int index = getSlotIndex(key);
        Node node = search(key);
        if (node != null && node.key.equals(key)) node.value = value;
        else {
            node = slots[index];
            slots[index] = new Node(key, value, node);
            len++;
        }
        if (len == slots.length) rehash();
    }

    public boolean contains(String key) {
        return search(key) != null;
    }


    public int get(String key) {
        Node node = slots[getSlotIndex(key)];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return 0;
    }


    private Node search(String key) {
        int index = getSlotIndex(key);
        if (slots[index] == null) return null;
        Node node = slots[index];
        while (node != null) {
            if (node.key.equals(key)) return node;
            node = node.next;
        }
        return null;
    }

    private int getSlotIndex(String key) {
        int a = key.hashCode();
        return (a > 0 ? a : -a) % slots.length;
    }

    private void rehash() {
        int newLen = slots.length * 2;
        Node[] oldSlots = slots;
        this.slots = new Node[newLen];

        for (Node oldSlot : oldSlots) {
            if (oldSlot != null) {
                Node node = oldSlot;
                while (node != null) {
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

    static class Node {
        Node next;
        int value;
        String key;

        public Node(String key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}

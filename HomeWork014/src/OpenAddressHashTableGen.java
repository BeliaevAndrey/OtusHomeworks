import java.util.Objects;

public class OpenAddressHashTableGen<T, E> {

    OANode<T, E>[] slots;
    int length;

    public OpenAddressHashTableGen() {
        this.slots = new OANode[11];
        length = 0;
    }

    public void put(T key, E value) {
        int kh = getKeyHash(key);
        int i = 0;
        int address = getAddress(kh, i);
        while (slots[address] != null) address = getAddress(kh, ++i);
        slots[address] = new OANode<>(key, value);
        length++;
        if (length > slots.length / 2) rehash();
    }

    public E get(T key) {
        int kh = getKeyHash(key);
        int i = 0;
        int address = getAddress(kh, i);
        while (slots[address] != null) {
            if (slots[address].key.equals(key)) return slots[address].value;
            address = getAddress(kh, ++i);
        }
        return null;
    }

    public void remove(int key) {
    }

    private void rehash() {
        length = 0;
        OANode<T, E>[] oldSlots = slots;
        slots = new OANode[slots.length * 2 + 1];
        for (OANode<T, E> node : oldSlots)
            if (node!= null)
                put(node.key, node.value);
    }

    private int getAddress(int keyHash, int shift) {
        return (keyHash % slots.length + shift) % slots.length;
    }

    private int getKeyHash(T key) {
        int hk = Objects.hashCode(key);
        return hk >0 ? hk : -hk;
    }


    static class OANode<T, E> {
        T key;
        E value;

        public OANode(T key, E value) {
            this.key = key;
            this.value = value;
        }
    }

}

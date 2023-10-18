import java.util.Objects;


public class OpenAddressHashTableGen<T, E> {

    OANode<T, E>[] slots;
    int length;

    boolean flag = false;  // For quadratic probing
    public OpenAddressHashTableGen() {
        this.slots = new OANode[11];
        length = 0;
    }

    /*
    * Quadratic probing equation:
    * hash(k)=(hash′(k)+ (−1)^i * i^2) mod M, M≡3 mod 4
    * */
    public OpenAddressHashTableGen(boolean flag) {     // For quadratic probing
        this.slots = new OANode[11];
        length = 0;
        this.flag = flag;
    }


    public void put(T key, E value) {
        int kh = getKeyHash(key);
        int i = 0;
        int reserve = -1;
        int address = getAddress(kh, i);
        while (slots[address] != null) {
            if (slots[address].key.equals(key)) {
                slots[address].value = value;
                if (slots[address].isStub()) slots[address].stub = false;
                return;
            }
            if (slots[address].isStub() && reserve < 0) reserve = address;
            address = getAddress(kh, ++i);
        }
        slots[reserve > 0 ? reserve : address] = new OANode<>(key, value);
        length++;
        if (length > (slots.length >> 1)) rehash();
    }

    public E get(T key) {
        int kh = getKeyHash(key);
        int i = 0;
        int address = getAddress(kh, i);
        while (slots[address] != null) {
            if (slots[address].key.equals(key)) {
                return slots[address].isStub() ? null : slots[address].value;
            }
            address = getAddress(kh, ++i);
        }
        return null;
    }

    public void remove(T key) {
        int kh = getKeyHash(key);
        int i = 0;
        int address = getAddress(kh, i);
        while (slots[address] != null) {
            if (slots[address].key.equals(key)) slots[address] = new OANode<>(key);
            address = getAddress(kh, ++i);
        }
    }

    private void rehash() {
        length = 0;
        OANode<T, E>[] oldSlots = slots;
        if (flag)           // For quadratic probing
            slots = new OANode[calcNewLen()];
        else                // For linear probing
            slots = new OANode[oldSlots.length * 2 + 1];
        for (OANode<T, E> node : oldSlots)
            if (node != null && !node.isStub())
                put(node.key, node.value);
    }

    private int getAddress(int keyHash, int shift) {
        if (flag)           // For quadratic probing
            return (keyHash + (1 - 2 * (shift & 1)) * (shift * shift)) % slots.length;
        return (keyHash % slots.length + shift) % slots.length;
    }


    // For quadratic probing
    private int calcNewLen() {
        int M = slots.length * 2;
        while (M % 4 != 3)
            M += 1;
        return M;
    }

    private int getKeyHash(T key) {
        int hk = Objects.hashCode(key);
        return hk > 0 ? hk : -hk;
    }


    static class OANode<T, E> {
        private final T key;
        private E value;
        private boolean stub;

        public OANode(T key, E value) {
            this.key = key;
            this.value = value;
            stub = false;
        }

        public OANode(T key) {
            this.key = key;
            this.stub = true;
        }

        public boolean isStub() {
            return stub;
        }
    }

}

public class CustomPearsonHashTable {
    Node[] slots;

    int len;

    int[] PTABLE = new int[]{
            0, 47, 169, 238, 165, 44, 160, 144, 76, 220, 192, 90, 104, 166, 40, 229, 239, 112, 120, 51, 202,
            164, 219, 60, 66, 107, 10, 115, 133, 254, 161, 48, 46, 243, 196, 127, 128, 36, 111, 4, 194, 78, 70,
            141, 227, 249, 236, 248, 221, 149, 91, 17, 73, 179, 215, 206, 20, 240, 55, 211, 181, 63, 24, 233, 147,
            158, 216, 242, 134, 13, 190, 135, 94, 130, 100, 72, 224, 45, 171, 124, 154, 199, 193, 86, 176, 122,
            69, 68, 31, 95, 218, 79, 159, 118, 12, 230, 22, 80, 113, 153, 96, 103, 23, 117, 58, 65, 252, 177, 197,
            101, 250, 28, 29, 187, 97, 225, 35, 244, 21, 142, 11, 246, 26, 129, 143, 41, 136, 204, 168, 172, 183,
            75, 84, 119, 14, 167, 110, 3, 32, 62, 126, 174, 77, 156, 232, 205, 180, 19, 140, 121, 184, 212, 210,
            27, 234, 82, 200, 6, 178, 53, 201, 54, 247, 18, 228, 123, 155, 157, 146, 175, 195, 98, 125, 34, 162,
            106, 93, 255, 241, 226, 25, 188, 85, 56, 52, 245, 1, 5, 42, 152, 57, 189, 139, 88, 61, 253, 67, 209,
            9, 71, 170, 50, 145, 87, 7, 137, 64, 109, 43, 2, 114, 30, 186, 235, 150, 213, 203, 222, 33, 214, 138,
            231, 92, 182, 217, 151, 49, 74, 131, 81, 38, 191, 16, 105, 132, 237, 148, 15, 108, 39, 173, 83, 185, 37,
            163, 116, 251, 89, 208, 223, 99, 59, 102, 207, 8, 198
    };


    public CustomPearsonHashTable() {
        this.slots = new Node[11];
        len = 0;
    }

    Long pHash(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int h = PTABLE[(word.charAt(0) + i) % 256];
            for (char c : word.toCharArray()) {
                h = PTABLE[(h ^ c)];
            }
            sb.append(Long.toBinaryString(h));
        }
        return Long.valueOf(sb.toString(), 2);
    }

    public void put(String key, int value) {
        int index = (int) getSlotIndex(key);
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
        Node node = slots[(int)getSlotIndex(key)];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return 0;
    }


    private Node search(String key) {
        int index = (int) getSlotIndex(key);
        if (slots[index] == null) return null;
        Node node = slots[index];
        while (node != null) {
            if (node.key.equals(key)) return node;
            node = node.next;
        }
        return null;
    }

    private long getSlotIndex(String key) {
        long a = pHash(key);
        return (a > 0 ? a : -a) % slots.length;
    }

    private void rehash() {
        int newLen = slots.length * 2 + 1;
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

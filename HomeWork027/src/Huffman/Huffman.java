package Huffman;


import structures.CustomPriorityQueue;
import structures.IPriorityQueue;

import java.util.ArrayList;

public class Huffman {


    // region Compress File
    public void compressFile(String rawFile, String compressedFile) {
        FileUtil fr = new FileUtil();
        byte[] bytes = fr.readBytes(rawFile);
        byte[] compressed = compressBytes(bytes);
        fr.writeBytes(compressedFile, compressed);
    }

    private byte[] compressBytes(byte[] bytes) {

        int[] freqArr = countFrequencies(bytes);
        byte[] fileHeader = buildHead(bytes.length, freqArr);
        Node root = growHuffmanTree(freqArr);
        String[] codes = createHuffmanCode(root);
        byte[] bits = compress(bytes, codes);

        byte[] outArray = new byte[fileHeader.length + bits.length];
        int i = 0;
        for (; i < fileHeader.length; i++) outArray[i] = fileHeader[i];
        for (int j = 0; j < bits.length; i++, j++) outArray[i] = bits[j];

        return outArray;
    }

    private int[] countFrequencies(byte[] bytes) {

        int[] freqArr = new int[128];
        for (byte b : bytes) freqArr[b & 127]++;
        normalize(freqArr);
        return freqArr;
    }

    private void normalize(int[] freqArr) {
        int max = 1;
        for (int frq : freqArr) if (frq > max) max = frq;

        if (max < 127) return;

        for (int i = 0; i < freqArr.length; i++)
            if (freqArr[i] > 0) freqArr[i] = 1 + freqArr[i] * 127 / (max + 1);

    }

    private byte[] buildHead(int fileLen, int[] freqArr) {
        ArrayList<Byte> head = new ArrayList<>();

        /* This kind of number code/decode procedure restores wrong length for large files, e.g: 8900890 -> 125082 */
//        head.add(  (byte) (fileLen         & 127)  );
//        head.add(  (byte) ((fileLen >> 8)  & 127)  );
//        head.add(  (byte) ((fileLen >> 16) & 127)  );
//        head.add(  (byte) ((fileLen >> 24) & 127)  );

        head.add(  (byte) (fileLen         & 127)  );
        head.add(  (byte) ((fileLen >> 7)  & 127)  );
        head.add(  (byte) ((fileLen >> 14) & 127)  );
        head.add(  (byte) ((fileLen >> 21) & 127)  );

        System.out.printf("file len: %d: %d %d %d %d\n",
                fileLen, head.get(0), head.get(1), head.get(2), head.get(3));   // TODO: RM semaphore

        int count = 0;
        for (int i : freqArr) if (i > 0) count++;

        System.out.printf("count before: %d\n", count);     // TODO: RMS

        head.add((byte) count);

        for (int i = 0; i < freqArr.length; i++)
            if (freqArr[i] > 0) {
                head.add((byte) i);
                head.add((byte) freqArr[i]);
            }

        byte[] header = new byte[head.size()];
        for (int i = 0; i < head.size(); i++) header[i] = head.get(i);

        return header;
    }

    private Node growHuffmanTree(int[] freqArr) {
        int max = 0;
        for (int i : freqArr) max += i;
        IPriorityQueue<Node> pq = new CustomPriorityQueue<>(max + 1);

        for (int i = 0; i < freqArr.length; i++) {
            if (freqArr[i] > 0) pq.enqueue(freqArr[i], new Node((byte) i, freqArr[i]));
        }

        while (pq.size() > 1) {
            Node bit0 = pq.poll();
            Node bit1 = pq.poll();
            Node parent = new Node(bit0, bit1);
            pq.enqueue(parent.freq, parent);
        }

        return pq.poll();
    }

    private String[] createHuffmanCode(Node root) {
        String[] codes = new String[127];
        next(root, "", codes);
        return codes;
    }

    private void next(Node node, String code, String[] codes) {
        if (node.bit0 == null) codes[node.symbol] = code;
        else {
            next(node.bit0, code + "0", codes);
            next(node.bit1, code + "1", codes);
        }
    }

    private byte[] compress(byte[] bytes, String[] codes) {
        ArrayList<Byte> bits = new ArrayList<>();
        byte sum = 0;
        byte bit = 1;

        for (byte b : bytes) {
            for (char c : codes[b & 127].toCharArray()) {
                if (c == '1') sum |= bit;
                if ((bit & 128) == 128) {
                    bits.add(sum);
                    sum = 0;
                    bit = 1;
                } else {
                    bit <<= 1;
                }

            }
        }
        System.out.printf("\nbit %d, bit&128=%d, bit&255=%d, bit&127=%d\n", bit, bit&128, bit&255, bit&127);
        if ((bit & 255) > 1) bits.add(sum);
        byte[] result = new byte[bits.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bits.get(i);
        }
        return result;
    }

    // endregion

    // region Decompress File

    public void deCompressFile(String compressedFile, String deCompressedFile) {
        FileUtil fr = new FileUtil();
        byte[] bytes = fr.readBytes(compressedFile);
        byte[] decompressed = deCompressBytes(bytes);
        System.out.printf("Decompress: decompressed.length %d\n", decompressed.length);
        fr.writeBytes(deCompressedFile, decompressed);
    }


    private byte[] deCompressBytes(byte[] bytes) {
        int[] tmp = new int[2];


        int[] freqArr = parseHeader(bytes, tmp);
        int dataLen = tmp[0];
        int startIdx = tmp[1];

        Node root = growHuffmanTree(freqArr);

        return decompress(bytes, startIdx, dataLen, root);
    }

    private int[] parseHeader(byte[] bytes, int[] tmp) {
        /* This kind of number code/decode procedure restores wrong length for large files, e.g: 8900890 -> 479514 */
//        int dataLength = (bytes[0]       )  |
//                         (bytes[1]  << 8 )  |
//                         (bytes[2]  << 16)  |
//                         (bytes[3]  << 24);

        int dataLength = (bytes[0])         |
                (bytes[1]  << 7 )  |
                (bytes[2]  << 14)  |
                (bytes[3]  << 21);

        System.out.printf("Data length: %d\n", dataLength);     // TODO: RMS

        int count = bytes[4] & 127;
        if (count == 0) count = 128;

        int[] freqArr = new int[128];
        for (int i = 0; i < count; i++) {
            int pos = 5 + i * 2;
            int symbol = bytes[pos];
            freqArr[symbol] = bytes[pos + 1];
        }

        int startIndex = 5 + count * 2;     // header length + dictionary length
        System.out.printf("Start index: %d count: %d\n", startIndex, count);        // TODO: RMS
        tmp[0] = dataLength;
        tmp[1] = startIndex;

        return freqArr;
    }

    byte[] decompress(byte[] bytes, int startIdx, int dataLen, Node root) {

        int size = 0;
        byte[] data = new byte[dataLen];
        Node current = root;

        for (int i = startIdx; i < bytes.length; i++) {
            for (int bit = 1; bit <= 128; bit <<= 1) {
                if ((bytes[i] & bit) == 0) current = current.bit0;
                else current = current.bit1;

                if (current.bit0 == null) {
                    if (size < dataLen) data[size++] = current.symbol;
                    current = root;
                }
            }
        }

        return data;
    }

    // endregion

    static class Node {

        public byte symbol;
        public int freq;
        public Node bit0;
        public Node bit1;

        public Node(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
            this.bit0 = null;
            this.bit1 = null;
        }

        public Node(Node bit0, Node bit1) {
            this.bit0 = bit0;
            this.bit1 = bit1;
            this.freq = bit0.freq + bit1.freq;
        }
    }

}

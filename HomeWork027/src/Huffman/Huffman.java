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

        FileUtil fu = new FileUtil();

        int[] freqArr = countFrequencies(bytes);
        byte[] fileHeader = buildHead(bytes.length, freqArr);

        fu.writeBytes("fileHead.txt", fileHeader);

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

        int[] freqArr = new int[256];
        for (byte b : bytes) freqArr[b & 127]++;
        normalize(freqArr);
        return freqArr;
    }

    private void normalize(int[] freqArr) {
        int max = 1;
        for (int frq : freqArr) if (frq > max) max = frq;

        if (max < 127) return;
        System.out.printf("\n\t\tmax = %d\n", max);

        for (int i = 0; i < freqArr.length; i++)
            if (freqArr[i] > 0) freqArr[i] = 1 + freqArr[i] * 127 / (max + 1);

    }

    private byte[] buildHead(int fileLen, int[] freqArr) {
        ArrayList<Byte> head = new ArrayList<>();

        head.add(  (byte) (fileLen         & 255)  );
        head.add(  (byte) ((fileLen >> 8)  & 255)  );
        head.add(  (byte) ((fileLen >> 16) & 255)  );
        head.add(  (byte) ((fileLen >> 24) & 255)  );

        int count = 0;
        for (int i : freqArr) if (i > 0) count++;

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
        for (int i : freqArr) max += (i & 255);
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
        String[] codes = new String[256];
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
                if ((bit & 255) == 128) {
                    bits.add(sum);
                    sum = 0;
                    bit = 1;
                } else {
                    bit <<= 1;
                }

            }
        }
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
        System.out.println("dcmp: decompressed.length " +
                decompressed.length + " " +
                decompressed.length / 1024 / 1024);
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
        int dataLength = (bytes[0] & 255 )      |
                        ((bytes[1] & 255) << 8)  |
                        ((bytes[2] & 255) << 16) |
                        ((bytes[3] & 255) << 24);

        System.out.printf("DATA LEN: %d\n", dataLength);

        int count = bytes[4] & 255;
//        if (count == 0) count = 128;

        int[] freqArr = new int[256];
        for (int i = 0; i < count; i++) {
            int pos = 5 + i * 2;
            byte symbol = bytes[pos];
            freqArr[symbol] = bytes[pos + 1];
        }

        int startIndex = 5 + count * 2;
        System.out.printf("start index: %d count: %d\n", startIndex, count);
        tmp[0] = dataLength;
        tmp[1] = startIndex;

        return freqArr;
    }

    byte[] decompress(byte[] bytes, int startIdx, int dataLen, Node root) {

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(bytes[i] & 255);
        }

        int size = 0;
        byte[] data = new byte[dataLen];
        Node current = root;

        for (int i = startIdx; i < bytes.length; i++) {
            for (int bit = 1; bit <= 128; bit <<= 1) {
                if (((bytes[i] & 255) & bit) == 0) current = current.bit0;
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

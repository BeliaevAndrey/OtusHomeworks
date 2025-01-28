package Huffman;

public class TestHuffman {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String fileName = "test0";
        String extIn = ".txt", extOut = ".huffmanned";

        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

        fileName = "test1";

        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

        fileName = "test4";

        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

    }
}

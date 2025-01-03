package Huffman;

public class TestHuffman {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
//        String fileName = "test_picture";
        String fileName = "test0";
//        String fileName = "mueller_utf8-cl";
//        String extIn = ".bmp", extOut = ".huffmanned";
        String extIn = ".txt", extOut = ".huffmanned";
//
        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

    }
}

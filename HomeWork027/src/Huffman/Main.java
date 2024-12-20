package Huffman;

public class Main {

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String fileName = "test1";
        String extIn = ".txt";
        String extOut = ".huffmanned";

        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

    }
}

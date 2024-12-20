package Huffman;

public class Main {

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String fileName = "README";
        String extIn = ".md";
        String extOut = ".huffmanned";

        huffman.compressFile(fileName + extIn, fileName + extOut);
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

    }
}

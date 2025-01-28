package Huffman;

public class TestHuffman {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String fileName = "test0";
        String extIn = ".txt", extOut = ".huffmanned";

        System.out.println("\n========================================\ntest 1");
        System.out.println("test0.txt compress");
        huffman.compressFile(fileName + extIn, fileName + extOut);
        System.out.println("\ntest0.txt decompress");
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

        System.out.println("\n========================================\ntest 2");
        fileName = "test1";

        System.out.println("test1.txt compress");
        huffman.compressFile(fileName + extIn, fileName + extOut);
        System.out.println("\ntest1.txt decompress");
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);


        System.out.println("\n========================================\ntest 3");
        fileName = "test4";

        System.out.println("test4.txt compress");
        huffman.compressFile(fileName + extIn, fileName + extOut);
        System.out.println("\ntest4.txt decompress");
        huffman.deCompressFile(fileName + extOut, fileName + ".back" + extIn);

    }
}

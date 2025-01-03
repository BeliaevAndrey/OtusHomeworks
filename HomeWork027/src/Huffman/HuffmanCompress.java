package Huffman;

import java.util.Arrays;

public class HuffmanCompress {

    public static void main(String[] args) {
        HuffmanCompress hc = new HuffmanCompress();
        hc.parseArgs(args);
    }


    private void parseArgs(String[] args) { // TODO: Correct file names creation
        String fileIn, fileOut;
        String arcExt = ".huffmanned";
        System.out.println(Arrays.toString(args));
        if (args.length > 1) {
            fileIn = args[1];
            if (args.length > 2) fileOut = args[2] + arcExt;
            else {
                if (fileIn.contains(arcExt)) fileOut = fileIn.replaceAll(arcExt, "\\.back.txt");
                else fileOut = fileIn + arcExt;
                System.out.println("file out: " + fileOut);
            }
            if (args[0].equalsIgnoreCase("c")) {
                compress(fileIn, fileOut);
                return;
            }

            if (args[0].equalsIgnoreCase("d")) {
                decompress(fileIn, fileOut);
                return;
            }
        }
        printUsage();
    }

    private void compress(String fileIn, String fileOut) {
        System.out.printf("compressing: %s to %s\n", fileIn, fileOut);
        Huffman huffman = new Huffman();
        huffman.compressFile(fileIn, fileOut);
    }

    private void decompress(String fileIn, String fileOut) {
        if (!fileIn.matches(".+\\.huffmanned"))
            throw new IllegalArgumentException("Wrong file extension");

        Huffman huffman = new Huffman();

        huffman.deCompressFile(fileIn, fileOut);
    }


    private void printUsage() {
        String usage = """
                usage: HuffmanCompress COMMAND filenameIn [filenameOut]
                where:
                    COMMANDS:
                    c -- compress file
                    d -- decompress file
                    
                    filenameIn -- name of file to process
                    filenameOut -- name of resulting file (optional)
                    
                    If path is not set in filenameIn default path of "user.dir/Homework027/TestData/" will be used.
                    
                    """;
        System.out.println(usage);
    }


}

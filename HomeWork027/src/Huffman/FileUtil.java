package Huffman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {


    private final Path path;

    public FileUtil() {
        Path nPath = Path.of(System.getProperty("user.dir"), "HomeWork027", "TestData");
        this.path = nPath;
        if (!Files.isDirectory(nPath)) throw new IllegalArgumentException("Wrong file name/path");
    }

    public byte[] readBytes(String fileIn) {
        Path fullInPath = Path.of(path.toString(), fileIn);
        byte[] buffer = null;

        try (InputStream fis = Files.newInputStream(fullInPath)) {
            buffer = new byte[(int) Files.size(fullInPath)];
            int len = fis.read(buffer);
            System.out.printf("Reader. Bytes read: %d\n", len);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }

    public void writeBytes(String fileOut, byte[] bytes) {
        Path fullOutPath = Path.of(path.toString(), fileOut);

        if (Files.exists(fullOutPath)) {
            try {
                System.out.println("File found. Deleting");
                Files.delete(fullOutPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("File not found.");

        System.out.println("Writer: bytes.length: " + bytes.length);

        try (OutputStream fos = Files.newOutputStream(fullOutPath)) {
            fos.write(bytes);
            fos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

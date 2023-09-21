import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class CreateBinaryFile {

    Path basicPath = Path.of(System.getProperty("user.dir"), "HomeWork009", "Data");

    public static void main(String[] args) {
        CreateBinaryFile cbf = new CreateBinaryFile();
        int upperLim = 65535;
        int numbersAmt = 100_000_000;
        String fileName = "File1.bin";
        cbf.fileGenBufShort(numbersAmt, fileName, upperLim);
    }

    void fileGenBufShort(int len, String fileName, int upperLim) {
        Path outPath = Path.of(String.valueOf(basicPath), fileName);
        if (!checkOrCreatePath()) {
            System.out.println("Path ERROR.");
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(outPath.toString());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {
            int step = 1000;
            for (int i = 0; i < len; i += step) {
                int[] buffer = fillBuffer(upperLim, step);
                for (int num : buffer) dos.writeShort(num);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int[] fillBuffer(int upperLim, int bufLen) {
        Random rnd = new Random();
        int[] buffer = new int[bufLen];
        for (int i = 0; i < bufLen; i++) {
            buffer[i] = rnd.nextInt(upperLim);
        }
        System.out.print(".");
        return buffer;
    }

    boolean checkOrCreatePath() {
        if (Files.exists(basicPath)) return true;
        try {
            Files.createDirectory(basicPath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

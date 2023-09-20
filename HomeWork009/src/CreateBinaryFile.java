import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class CreateBinaryFile {

    Path basicPath = Path.of(System.getProperty("user.dir"), "HomeWork009", "Data");

    public static void main(String[] args) {
        CreateBinaryFile cbf = new CreateBinaryFile();
        int upperLim = 65535;
        int numbersAmt = (int) 1e9;
        String fileName = "File1.bin";
        cbf.fileGenBufShort(numbersAmt, fileName, upperLim);
    }

    void fileGenBufShort(int len, String fileName, int upperLim) {
        Path outPath = Path.of(String.valueOf(basicPath), fileName);
        if (!checkOrCreatePath()) return;
        try (FileOutputStream fos = new FileOutputStream(outPath.toString());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {
            for (int i = 0; i <= len; i += 1000) {
                int[] buffer = fillBuffer(upperLim);
                for (int num : buffer) dos.writeShort(num);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int[] fillBuffer(int upperLim) {
        Random rnd = new Random();
        int[] buffer = new int[100];
        for (int i = 0; i < 100; i++) {
            buffer[i] = rnd.nextInt(upperLim);
            System.out.print(buffer[i] + " ");
        }
        System.out.println();
        return buffer;
    }


    void fileReadShift(String fileName) {
        Path inPath = Path.of(basicPath.toString(), fileName);
        if (!Files.exists(inPath)) return;
        try (FileInputStream fis = new FileInputStream(inPath.toString());
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                int num = dis.readShort() & 65535;
                System.out.print(num + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

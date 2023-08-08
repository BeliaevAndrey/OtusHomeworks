import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Tester {
    static final String directory = "HomeWork001/testData";

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testPairs();
    }


    void testPairs() {
        HashMap<Long, Long> testValues = loadTests(Path.of(directory, "1.Tickets"));
        for (Long key : testValues.keySet()) {
            Long expected = testValues.get(key);
            double actual = new LuckyTicket().findAmount(key.intValue());
            boolean answer = (actual == expected);
            System.out.printf("for %3d-digits number -> expected: %-25d actual: %-25.0f %10b\n", key, expected, actual, answer);
        }
    }

    HashMap<Long, Long> loadTests(Path path) {

        HashMap<Long, Long> testPairs = new HashMap<>();

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            dirStream.forEach(file -> {
                if (file.toString().endsWith("in")) {
                    Long valueIn = readLongFromFile(file.toString());
                    if (valueIn != null) {
                        Long valueOut = readLongFromFile(file.toString().replaceAll("\\.in", ".out"));
                        testPairs.put(valueIn, valueOut);
                    }
                }
            });
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return testPairs;
    }

    Long readLongFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return Long.parseLong(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

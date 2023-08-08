import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Tester {
    static final String directory = "HomeWork001/testData";

    public static void main(String[] args) {
        Tester test = new Tester();

        HashMap<Long, Long> testValues = test.loadTests(Path.of(directory, "1.Tickets"));

        for (Long key : testValues.keySet()) {
            Long value = testValues.get(key);
            boolean answer = test.testPair(key, value);
            if (answer)
                System.out.printf("for %d-digits number: expected: %d  %b\n", key, value, answer);
            else {
                double actual = new LuckyTicket().findAmount(key.intValue());
                System.out.printf("for %d-digits number: expected: %d  actual: %f %b\n", key, value, actual, answer);
            }
        }
    }

    boolean testPair(Long valueIn, Long expected) {
        double actual = new LuckyTicket().findAmount(valueIn.intValue());
        return actual == expected;
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

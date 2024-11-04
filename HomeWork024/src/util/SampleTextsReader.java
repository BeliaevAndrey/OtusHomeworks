package util;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class SampleTextsReader {

    private final Path path;

    HashMap<Integer, ArrayList<String>> samples;
    private int count;



    public SampleTextsReader() {
        path = Path.of(System.getProperty("user.dir"), "HomeWork024", "TestData");
        if (!Files.isDirectory(path))
            throw new IllegalMonitorStateException("Path not found/Not a directory");
        count = 0;
        samples = new HashMap<>();
        getFiles();
    }

    public HashMap<Integer, ArrayList<String>> getSamples() { return samples; }

    public int getCount() { return count; }

    private void getFiles() {
        ArrayList<Path> testPaths = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path);) {
            ds.forEach(testPaths::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Path p : testPaths) {
            try (FileReader fr = new FileReader(p.toString());
                 BufferedReader br = new BufferedReader(fr)) {
                StringBuilder sb = new StringBuilder();
                while (br.ready()) {
                    sb.append(br.readLine()).append("\n");
                }
                parseTests(sb);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseTests(StringBuilder data) {
        samples.put(count, new ArrayList<>());
        int tStr = data.indexOf("<sample>");
        int tEnd = data.indexOf("</sample>");
        int pStr = data.indexOf("<pattern>", tEnd);
        int pEnd = data.indexOf("</pattern>", tEnd);
        if (tStr < 0 || tEnd < 0 || pStr < 0 || pEnd < 0)
            throw new IllegalStateException("Incorrect file format");
        samples.get(count).add(data.substring(tStr + 8, tEnd));
        while (pStr != -1 && pEnd != -1) {
            samples.get(count).add(data.substring(pStr + 9, pEnd));
            pStr = data.indexOf("<pattern>", pEnd);
            pEnd = data.indexOf("</pattern>", pStr);
        }
        count++;
    }

}

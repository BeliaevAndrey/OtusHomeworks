package testsArrays;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class WriteToFile {

    public static void writeToFile(HashMap<String, Double[]> results, String fileName, int recordsAmt) {
        final String outPathFile = "HomeWork004/testsResults/" + fileName;
        StringBuilder sb = new StringBuilder();
        String format = "%s,%s,%s,%s,%s,%s\n";
        sb.append(String.format(format,
                "Numbers", "WrapperOverArrayList", "SingleArray", "VectorArray", "FactorArray", "MatrixArray"));
        for (int i = 0; i < recordsAmt; i++) {
            sb.append(String.format(format,
                    results.get("Numbers")[i],
                    results.get("WrapperOverArrayList")[i],
                    results.get("SingleArray")[i],
                    results.get("VectorArray")[i],
                    results.get("FactorArray")[i],
                    results.get("MatrixArray")[i]
            ));
        }
        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(outPathFile), StandardOpenOption.CREATE)){
            bw.write(sb.toString());
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

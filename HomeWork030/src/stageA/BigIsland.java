package stageA;

import util.ReadFile;
import util.WriteResults;

import java.util.ArrayList;

public class BigIsland {

    static final String taskName = "big_island";

    ArrayList<String> testResults;

    public static void main(String[] args) {
        BigIsland island = new BigIsland();
        island.testResults = new ArrayList<>();

        ReadFile rf = new ReadFile(taskName);
        island.runTests(rf);

        WriteResults wr = new WriteResults(taskName);
        wr.writeFile(island.testResults);
    }


    private void runTests(ReadFile rf) {
        int testsAmt = rf.getTestAmt();
//        testsAmt = 1;

        for (int i = 0; i < testsAmt; i++) {
            String[] test = rf.getTest(i, true);
            int answer = -1;

            try {
                answer = Integer.parseInt(test[1]);
            } catch (NumberFormatException e) { e.printStackTrace(); }

            int result = countIslands(test[0]);

            String lineOut = String.format(
                    "test-%d. Answer: %s; Result: %s; Correct: %s\n",
                    i, answer, result, result == answer);
            testResults.add(lineOut);
            System.out.print(lineOut);
        }
    }

    int countIslands(String testData) {
        int[][] field = parseTestData(testData);
        int mark = 1;
        int size = field.length;

        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++)
                if (field[i][j] == 1) walkAround(field, size, i, j, ++mark);

        return mark - 1;
    }

    void walkAround(int[][] field, int size,  int row, int col, int mark) {

        if (row < 0 || col < 0) return;
        if (row  > size - 1 || col > size - 1) return;
        if (field[row][col] != 1) return;

        field[row][col] = mark;

        walkAround(field, size, row, col - 1, mark);
        walkAround(field, size, row, col + 1, mark);
        walkAround(field, size, row - 1, col, mark);
        walkAround(field, size, row + 1, col, mark);
    }


    int[][] parseTestData(String testData) {
        String[] rows = testData.split("\n");
        int size = rows.length;
        int [][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] row = rows[i].strip().split("\\s");
            for (int j = 0; j < size; j++) {
                try { matrix[i][j] = Integer.parseInt(row[j]);
                } catch (NumberFormatException e) { e.printStackTrace(); }
            }
        }
        return matrix;
    }

}

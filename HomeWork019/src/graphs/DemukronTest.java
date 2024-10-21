package graphs;

import util.GraphUtl;

public class DemukronTest {

    /*
        (1)------->(4)----.       1 | 2 4
         |                |       2 | 6 0
         |                |       3 | 2 0
         v                v       4 | 6 0
        (2)------------->(6)      5 | 0 0
         ^                |       6 | 5 0
         |                |
         |                v
        (3)              (5)

       | 1 | 2 | 3 | 4 | 5 | 6
    1  | 0 | 1 | 0 | 1 | 0 | 0
    2  | 0 | 0 | 0 | 0 | 0 | 1
    3  | 0 | 1 | 0 | 0 | 0 | 0
    4  | 0 | 0 | 0 | 0 | 0 | 1
    5  | 0 | 0 | 0 | 0 | 0 | 0
    6  | 0 | 0 | 0 | 0 | 1 | 0

    */


    public static void main(String[] args) {

        GraphUtl utl = new GraphUtl();


        int[][] vecAdj = {
                {2, 4},
                {6, 0},
                {2, 0},
                {6, 0},
                {0, 0},
                {5, 0}
        };
    /* sample matrix from seminar materials:

        int[][] matrix = {
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0}
        };
    */
        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj);
        utl.printMatrix(matrixAdj);
        Graph graph = new Graph(matrixAdj, matrixAdj.length);
        Demukron demukron = new Demukron(graph);

        System.out.println(demukron.tplSort());
        demukron.printPath();

    }
}

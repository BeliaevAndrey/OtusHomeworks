package tests;

import graphs.Graph;
import sortings.Tarjan;
import util.GraphUtl;

public class TarjanTest {


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

        int[][] matrixAdj = utl.vectorAjcToMatrixAjc(vecAdj);
        Graph graph = new Graph(matrixAdj, matrixAdj.length);
        Tarjan tarjan = new Tarjan(graph);
        System.out.println(tarjan.tplSort());

        System.out.println("Tarjan algorithm: ");
        if (tarjan.tplSort()) {
            System.out.println("Topological sort: ");
            int pathLen = tarjan.getPath().size();
            for (int i = 0; i < pathLen; i++) {
                System.out.printf("%d ", tarjan.getPath().get(i));
            }
            System.out.println();
        } else
            System.out.println("Topological sort not available.");

    }
}

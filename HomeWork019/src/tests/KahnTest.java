package tests;

import graphs.Graph;
import sortings.Kahn;
import util.GraphUtl;

public class KahnTest {
    public static void main(String[] args) {
        GraphUtl utl = new GraphUtl();


        int[][] vecAdj = {
                {  3  ,  13 ,  0  , 0 },
                {  13 ,  0  ,  0  , 0 },
                {  0  ,  0  ,  0  , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  3  ,  9  ,  10 , 0 },
                {  4  ,  11 ,  12 , 13},
                {  11 ,  0  ,  0  , 0 },
                {  2  ,  4  ,  6  , 7 },
                {  1  ,  14 ,  0  , 0 },
                {  1  ,  7  ,  12 , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  0  ,  0  ,  0  , 0 },
                {  3  ,  0  ,  0  , 0 },
                {  6  ,  0  ,  0  , 0 }};


        int[][]  matrix2= utl.vectorAjcToMatrixAjc(vecAdj);
        int size = matrix2.length;
        Graph graph = new Graph(matrix2, size);

        Kahn kahn = new Kahn(graph);

        System.out.println("Kahn algorithm: ");
        if (kahn.tplSort())
        {
            System.out.println("Topological sort: ");
            kahn.printPath();
            System.out.println();
        }
        else
            System.out.println("Topological sort not available!");

    }
}

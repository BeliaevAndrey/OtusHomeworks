package tests;

import Kosaraju.Kosaraju;
import structures.CustomLinkedList;
import util.GraphUtl;

public class KosarajuTest {

    /*
        1 | 2  0  0
        2 | 3  5  6
        3 | 4  7  0
        4 | 3  8  0
        5 | 1  6  0
        6 | 7  0  0
        7 | 6  0  0
        8 | 7  4  0
     */

    public static void main(String[] args) {

        int[][] adjVector = new int[][]{
                {2, 0, 0},
                {3, 5, 6},
                {4, 7, 0},
                {3, 8, 0},
                {1, 6, 0},
                {7, 0, 0},
                {6, 0, 0},
                {7, 4, 0}};

        GraphUtl utl = new GraphUtl();

        int[][] adjMatrix = utl.vectorAjcToMatrixAjc(adjVector);

//        utl.printMatrix(adjMatrix);

        Kosaraju kosaraju = new Kosaraju(adjMatrix);
        kosaraju.kosarajuSearch();


        CustomLinkedList<CustomLinkedList<Integer>> components = kosaraju.getCompAll();
        int len = components.size();
        for (int i = 0; i < len; i++) {
            CustomLinkedList<Integer> component = components.get(i);
            int subLen = component.size();
            for (int j = 0; j < subLen; j++) {
                System.out.printf("%d ", component.get(j));
            }
            System.out.println();
        }


    }



}

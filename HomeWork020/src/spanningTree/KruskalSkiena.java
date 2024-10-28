package spanningTree;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;

/**
 * Реализация по:
 * Скиена С. С. -  Алгоритмы. Руководство по разработке. -
 * 3-е изд.: Пер. с англ. - СПб.: БХВ-Петербург, 2022. - 848 с.: ил. -
 * сс. 288 - 293
 */
public class KruskalSkiena {

    private int count;

    private final UnionFindStruct ufs;

    private final CustomLinkedList<Edge> edges;

    private final int size;

    private int minWeight = 0;

    private Edge[] minWeightTree;


    public KruskalSkiena(Graph graph) {
        size = graph.getSize();
        edges = new CustomLinkedList<>();
        ufs = new UnionFindStruct(graph.getSize());
        setEdges(graph.getMatrixAdj());
        minWeightTree = new Edge[edges.size()];
    }

    private void setEdges(int[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (matrix[i][j] > 0) {
                    if (i < j) edges.add(new Edge(i, j, matrix[i][j]));
                    else edges.add(new Edge(j, i, matrix[i][j]));
                }
            }
        }
        edges.sort();
    }

    public void spanningTree() {
        int edgesAmt = edges.size();
        for (int i = 0; i < edgesAmt; i++) {
            Edge e = edges.get(count);
            if (!ufs.same_component(e.getBegin(), e.getEnd())) {
                minWeight += e.getWeight();
                ufs.union(e.getBegin(), e.getEnd());
                minWeightTree[count++] = e;
            }
        }
        truncateTree();
    }

    public int getMinWeight() { return minWeight; }

    public Edge[] getMinWeightTree() { return minWeightTree; }

    private void truncateTree() {
        Edge[] tmp = new Edge[count];
        for (int i = 0; i < count; i++) {
            tmp[i] = minWeightTree[i];
        }
        minWeightTree = tmp;
    }

    public void printTree() {
        for (int i = 0; i < count; i++) {
            System.out.println(minWeightTree[i]);
        }
    }
}

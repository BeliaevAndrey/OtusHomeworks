package spanningTree;

import graph.Edge;
import graph.Graph;
import structures.CustomLinkedList;

public class Boruvka {

    // region privates (basics)

    private final int[][] matrix;

    private final int size;

    private final CustomLinkedList<Edge> minWeightTree;

    private int minWeight = 0;

    private int totalWeight = 0;

    // endregion

    private int[] parents;

    public Boruvka(Graph graph) {
        this.matrix = graph.getMatrixAdj();
        this.size = graph.getSize();
        minWeightTree = new CustomLinkedList<>();
    }

    public void spanningTree() {
        parents = new int[size];
        for (int i = 0; i < size; i++) parents[i] = i;

        totalWeight = findTotalWeight();
        minWeight = 0;
        int begin = 0, end = 0;

        for (int i = 0; i < size; i++) {
            int cmw = totalWeight;                   // current minimal weight
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) continue;
                if (matrix[i][j] < cmw) {
                    cmw = matrix[i][j];
                    end = j;
                }
            }
            addEdge(i, end, cmw);
        }

        int amt = minWeightTree.size();

        while (amt < size - 1) {
            int cmw = totalWeight;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (getParent(i) == getParent(j)) continue;
                    if (matrix[i][j] == 0) continue;    // skip if no edge

                    if (matrix[i][j] < cmw) {           // min weight
                        begin = i;
                        end = j;
                        cmw = matrix[i][j];
                    }
                }
            }
            addEdge(begin, end, cmw);
            amt++;
        }
    }

    private void addEdge(int src, int dst, int wt) {

        if (src == dst) return;

        if (src > dst) {
            int tmp = dst;
            dst = src;
            src = tmp;
        }

        if (getParent(dst) == getParent(src)) return;

        minWeightTree.add(new Edge(src, dst, wt));
        minWeight += wt;

        if (getParent(dst) == dst) parents[dst] = getParent(src);
        if (getParent(src) == src) parents[src] = getParent(dst);


    }


    private int getParent(int v) {  /* find() in union-find */
        return parents[v] == v ? v :  getParent(parents[v]);
    }

    private int findTotalWeight() {
        int weight = 0;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                weight += matrix[i][j];

        return weight;
    }


    // region getters

    public CustomLinkedList<Edge> getMinWeightTree() {return minWeightTree;}

    public int getMinWeight() {return minWeight;}

    // endregion


}

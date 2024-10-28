package spanningTree;

/**
 * Реализация по:
 * Скиена С. С. -  Алгоритмы. Руководство по разработке. -
 * 3-е изд.: Пер. с англ. - СПб.: БХВ-Петербург, 2022. - 848 с.: ил. -
 * сс. 288 - 293
 */
public class UnionFindStruct {

    private final int[] parents;
    private final int[] weights;
    private final int n;

    public UnionFindStruct(int n) {
        this.n = n;
        this.parents = new int[n];
        this.weights = new int[n];
        init();
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            weights[i] = 1;
        }
    }

    public int[] getParents() { return parents; }

    public int[] getWeights() { return weights; }

    int find(int v) {
        if (parents[v] == v) return v;
        return find(parents[v]);
    }

    void union(int s1, int s2) {
        int r1, r2;

        r1 = find(s1);
        r2 = find(s2);

        if (r1 == r2) return;

        if (weights[r1] >= weights[r2]) {
            weights[r1] = weights[r1] + weights[r2];
            parents[r2] = r1;
        } else {
            weights[r2] = weights[r1] + weights[r2];
            parents[r1] = r2;
        }
    }

    boolean same_component(int s1, int s2) {
        return (find(s1) == find(s2));
    }


}

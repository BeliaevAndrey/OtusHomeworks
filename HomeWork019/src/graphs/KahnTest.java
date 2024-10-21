package graphs;

public class KahnTest {
    public static void main(String[] args) {

        int[][] matrix = new int[][]
                {
                        {0, 1, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0},
                };
        int size = matrix.length;
        Graph graph = new Graph(matrix, size);

        Kahn kahn = new Kahn(graph);
        
        if (kahn.tplSort())
        {
            System.out.println("Топологическая сортировка: ");
            for (int i = 0; i < kahn.path.size(); i++) {
                System.out.print(kahn.path.get(i) + " ");
            }
            System.out.println();
        }
        else
            System.out.println("Топологическая сортировка невозможна!");

    }
}

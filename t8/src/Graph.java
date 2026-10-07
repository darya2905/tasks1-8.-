import java.awt.Point;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Point> nodes = new ArrayList<>();
    private ArrayList<int[]> edges = new ArrayList<>();

    public void addNode(int x, int y) {
        nodes.add(new Point(x, y));
    }

    public void addEdge(int i, int j) {
        if (i < 0 || i >= nodes.size() || j < 0 ||  j >= nodes.size()) return;
        if (i == j) return;

        for (int[] edge : edges) {
            if ((edge[0] == i && edge[1] == j) ||  (edge[0] == j && edge[1] == i)) {
                return;
            }
        }

        edges.add(new int[]{i, j});
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Point getNode(int index) {
        return nodes.get(index);
    }

    public void moveNode(int index, int x, int y) {
        if (index >= 0 && index < nodes.size()) {
            nodes.get(index).x = x;
            nodes.get(index).y = y;
        }
    }

    public int[] getDegrees() {
        int[] deg = new int[nodes.size()];
        for (int[] edge : edges) {
            deg[edge[0]]++;
            deg[edge[1]]++;
        }
        return deg;
    }

    public int[][] getAdjacencyMatrix() {
        int n = nodes.size();
        int[][] matrix = new int[n][n];
        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = 1;
            matrix[edge[1]][edge[0]] = 1;
        }
        return matrix;
    }

    public ArrayList<int[]> getEdges() {
        return edges;
    }

    public ArrayList<Point> getNodes() {
        return nodes;
    }

    public void clear() {
        nodes.clear();
        edges.clear();
    }
}

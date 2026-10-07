import java.io.*;
import java.util.*;

public class Graph {
    private final int vertices;
    private final int[][] matrix;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
    }

    public void addEdge(int u, int v) {
        if (u >= 0 && u < vertices && v >= 0 && v < vertices) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }
    }

    public boolean isConnected() {
        if (vertices == 0) return true;
        boolean[] visited = new boolean[vertices];
        dfs(0, visited);
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int i = 0; i < vertices; i++) {
            if (matrix[v][i] == 1 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder("graph G {\n");
        for (int i = 0; i < vertices; i++) {
            sb.append("  ").append(i).append(";\n");
            for (int j = i; j < vertices; j++) {
                if (matrix[i][j] == 1) {
                    sb.append("  ").append(i).append(" -- ").append(j).append(";\n");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static Graph loadFromDot(File file) throws Exception {
        Scanner sc = new Scanner(file);
        List<int[]> edges = new ArrayList<>();
        int maxV = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.contains("--")) {
                String[] parts = line.replace(";", "").split("--");
                int u = Integer.parseInt(parts[0].trim());
                int v = Integer.parseInt(parts[1].trim());
                edges.add(new int[]{u, v});
                maxV = Math.max(maxV, Math.max(u, v));
            }
        }
        sc.close();

        Graph g = new Graph(maxV + 1);
        for (int[] e : edges) g.addEdge(e[0], e[1]);
        return g;
    }
}
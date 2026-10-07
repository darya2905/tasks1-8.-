import java.util.Arrays;

public class IsomorphismChecker {

    public int[] findIsomorphism(Graph g1, Graph g2) {
        int n = g1.getNodeCount();

        if (n != g2.getNodeCount()) return null;

        int[][] matrix1 = g1.getAdjacencyMatrix();
        int[][] matrix2 = g2.getAdjacencyMatrix();

        int[] match = new int[n];
        Arrays.fill(match, -1);
        boolean[] used = new boolean[n];

        boolean found = tryMatch(matrix1, matrix2, match, used, 0);

        if (found) {
            return match;
        } else {
            return null;
        }
    }

    private boolean tryMatch(int[][] m1, int[][] m2, int[] match, boolean[] used, int step) {
        int n = m1.length;

        if (step == n) {
            return true;
        }

        for (int j = 0; j < n; j++) {
            if (used[j]) continue;

            boolean compatible = true;
            for (int i = 0; i < step; i++) {
                if (m1[step][i] != m2[j][match[i]]) {
                    compatible = false;
                    break;
                }
            }

            if (compatible) {
                match[step] = j;
                used[j] = true;

                if (tryMatch(m1, m2, match, used, step + 1)) {
                    return true;
                }

                match[step] = -1;
                used[j] = false;
            }
        }

        return false;
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GraphIsomorphismFrame extends JFrame {

    private Graph graph1 = new Graph();
    private Graph graph2 = new Graph();

    private GraphPanel panel1;
    private GraphPanel panel2;

    private JLabel resultLabel;

    private boolean edgeMode = false;
    private int selectedNode1 = -1;
    private int selectedNode2 = -1;

    public GraphIsomorphismFrame() {
        setTitle("Graph Isomorphism Checker");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel with buttons
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);

        JButton nodeModeBtn = new JButton("Add Nodes");
        JButton edgeModeBtn = new JButton("Add Edges");
        JButton checkBtn = new JButton("Check Isomorphism");
        JButton clearBtn = new JButton("Clear All");

        topPanel.add(nodeModeBtn);
        topPanel.add(edgeModeBtn);
        topPanel.add(checkBtn);
        topPanel.add(clearBtn);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with two drawing areas
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        panel1 = new GraphPanel(graph1, "Graph 1", new Color(233, 68, 96));
        panel2 = new GraphPanel(graph2, "Graph 2", new Color(14, 165, 233));
        centerPanel.add(panel1);
        centerPanel.add(panel2);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with result
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(resultLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button handlers
        nodeModeBtn.addActionListener(e -> setNodeMode());
        edgeModeBtn.addActionListener(e -> setEdgeMode());
        checkBtn.addActionListener(e -> checkIsomorphism());
        clearBtn.addActionListener(e -> clearAll());

        // Pass frame reference to panels
        panel1.setParentFrame(this);
        panel2.setParentFrame(this);

        setVisible(true);
    }

    public void setNodeMode() {
        edgeMode = false;
        selectedNode1 = -1;
        selectedNode2 = -1;
    }

    public void setEdgeMode() {
        edgeMode = true;
        selectedNode1 = -1;
        selectedNode2 = -1;
    }

    public void onNodeClicked(int panelNumber, int nodeIndex) {
        if (!edgeMode) return;

        if (panelNumber == 1) {
            if (selectedNode1 == -1) {
                selectedNode1 = nodeIndex;
            } else {
                if (selectedNode1 != nodeIndex) {
                    graph1.addEdge(selectedNode1, nodeIndex);
                }
                selectedNode1 = -1;
                panel1.repaint();
            }
        } else {
            if (selectedNode2 == -1) {
                selectedNode2 = nodeIndex;
            } else {
                if (selectedNode2 != nodeIndex) {
                    graph2.addEdge(selectedNode2, nodeIndex);
                }
                selectedNode2 = -1;
                panel2.repaint();
            }
        }
    }

    private void checkIsomorphism() {
        int n1 = graph1.getNodeCount();
        int n2 = graph2.getNodeCount();

        if (n1 != n2) {
            resultLabel.setText("Not isomorphic!");
            return;
        }

        if (graph1.getEdgeCount() != graph2.getEdgeCount()) {
            resultLabel.setText("Not isomorphic!");
            return;
        }

        if (n1 == 0) {
            resultLabel.setText("Graphs are empty.");
            return;
        }

        int[] deg1 = graph1.getDegrees();
        int[] deg2 = graph2.getDegrees();
        Arrays.sort(deg1);
        Arrays.sort(deg2);

        if (!Arrays.equals(deg1, deg2)) {
            resultLabel.setText("Not isomorphic!");
            return;
        }IsomorphismChecker checker = new IsomorphismChecker();
        int[] match = checker.findIsomorphism(graph1, graph2);

        if (match != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Isomorphic! Mapping: ");
            for (int i = 0; i < n1; i++) {
                sb.append(i).append("?").append(match[i]);
                if (i < n1 - 1) sb.append(", ");
            }
            resultLabel.setText(sb.toString());
        } else {
            resultLabel.setText("Not isomorphic!");
        }
    }

    private void clearAll() {
        graph1.clear();
        graph2.clear();
        selectedNode1 = -1;
        selectedNode2 = -1;
        resultLabel.setText(" ");
        panel1.repaint();
        panel2.repaint();
    }
}

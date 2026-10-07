import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    private Graph graph;
    private String title;
    private Color nodeColor;
    private int panelNumber;
    private GraphIsomorphismFrame parentFrame;
    private int draggedNode = -1;

    public GraphPanel(Graph graph, String title, Color nodeColor) {
        this.graph = graph;
        this.title = title;
        this.nodeColor = nodeColor;
        this.panelNumber = title.equals("Graph 1") ? 1 : 2;

        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedNode = -1;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleDrag(e);
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public void setParentFrame(GraphIsomorphismFrame frame) {
        this.parentFrame = frame;
    }

    private void handlePress(MouseEvent e) {
        int nodeIdx = findNodeAt(e.getX(), e.getY());

        if (nodeIdx >= 0) {
            if (parentFrame != null) {
                parentFrame.onNodeClicked(panelNumber, nodeIdx);
            }
            draggedNode = nodeIdx;
        } else {
            graph.addNode(e.getX(), e.getY());
            repaint();
        }
    }

    private void handleDrag(MouseEvent e) {
        if (draggedNode >= 0) {
            graph.moveNode(draggedNode, e.getX(), e.getY());
            repaint();
        }
    }

    private int findNodeAt(int x, int y) {
        ArrayList<Point> nodes = graph.getNodes();
        for (int i = nodes.size() - 1; i >= 0; i--) {
            Point p = nodes.get(i);
            double dist = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
            if (dist < 20) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ArrayList<Point> nodes = graph.getNodes();
        ArrayList<int[]> edges = graph.getEdges();

        // Draw edges
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(2));
        for (int[] edge : edges) {
            if (edge[0] < nodes.size() && edge[1] < nodes.size()) {
                Point p1 = nodes.get(edge[0]);
                Point p2 = nodes.get(edge[1]);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        // Draw vertices
        for (int i = 0; i < nodes.size(); i++) {
            Point p = nodes.get(i);

            g2.setColor(nodeColor);
            g2.fillOval(p.x - 18, p.y - 18, 36, 36);

            g2.setColor(Color.BLACK);
            g2.drawOval(p.x - 18, p.y - 18, 36, 36);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            String label = String.valueOf(i);
            FontMetrics fm = g2.getFontMetrics();
            int tw = fm.stringWidth(label);
            int th = fm.getAscent();
            g2.drawString(label, p.x - tw / 2, p.y + th / 3);
        }

        // Draw title
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(title, 10, 20);
    }
}

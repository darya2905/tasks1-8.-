import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {
    private Graph graph;
    private JTextField vField = new JTextField("4", 5);
    private JTextArea edgesArea = new JTextArea("0 1\n1 2\n2 3", 6, 15);
    private JTextArea dotArea = new JTextArea(8, 20);
    private JLabel resultLabel = new JLabel("Результат: ");

    public MainFrame() {
        setTitle("Проверка связности графа");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton buildBtn = new JButton("Построить");
        JButton checkBtn = new JButton("Проверить");
        JButton saveBtn = new JButton("Сохранить");
        JButton loadBtn = new JButton("Загрузить");

        dotArea.setEditable(false);

        add(new JLabel("Вершин:"));
        add(vField);
        add(new JScrollPane(edgesArea));
        add(buildBtn);
        add(checkBtn);
        add(saveBtn);
        add(loadBtn);
        add(new JScrollPane(dotArea));
        add(resultLabel);

        buildBtn.addActionListener(e -> build());

        checkBtn.addActionListener(e -> {
            if (build()) {
                resultLabel.setText("Связный? " + (graph.isConnected() ? "ДА" : "НЕТ"));
            }
        });

        saveBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())) {
                    pw.print(dotArea.getText());
                    JOptionPane.showMessageDialog(this, "Файл сохранен!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка сохранения!");
                }
            }
        });

        loadBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    graph = Graph.loadFromDot(fc.getSelectedFile());
                    dotArea.setText(graph.toDot());
                    resultLabel.setText("Загружено из файла. Связный? " + (graph.isConnected() ? "ДА" : "НЕТ"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка загрузки файла!");
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private boolean build() {
        try {
            int n = Integer.parseInt(vField.getText().trim());
            graph = new Graph(n);

            String text = edgesArea.getText().trim();
            if (!text.isEmpty()) {
                for (String line : text.split("\n")) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    String[] p = line.split("\\s+");
                    if (p.length >= 2) {
                        graph.addEdge(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                    }
                }
            }

            dotArea.setText(graph.toDot());
            resultLabel.setText("Граф построен");
            return true;
        } catch (Exception e) {
            resultLabel.setText("Ошибка вводе данных!");
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

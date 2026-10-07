package ui;

import models.LinkedQueue;
import processors.QueueEvenProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Queue;

public class QueueApp extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JComboBox<String> implComboBox;
    private JLabel statusLabel;

    public QueueApp() {
        initUI();
    }

    private void initUI() {
        setTitle("Queue - Keep Only Even Numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ????
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));

        JLabel inputLabel = new JLabel("Enter integers:");
        inputPanel.add(inputLabel, BorderLayout.NORTH);

        inputArea = new JTextArea(6, 40);
        inputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputPanel.add(inputScroll, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);

        // ??????
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        implComboBox = new JComboBox<>(new String[]{
                "1) Custom Queue",
                "2) Standard Queue"
        });

        JButton processButton = new JButton("Process Queue");
        JButton clearButton = new JButton("Clear");

        processButton.addActionListener(e -> processQueue());
        clearButton.addActionListener(e -> clearAll());

        controlPanel.add(implComboBox);
        controlPanel.add(processButton);
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.CENTER);

        // ?????
        JPanel outputPanel = new JPanel(new BorderLayout(5, 5));
        outputPanel.setBorder(BorderFactory.createTitledBorder("Result (Even Numbers Only)"));

        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outputPanel.add(statusLabel, BorderLayout.SOUTH);

        add(outputPanel, BorderLayout.SOUTH);
    }

    private void processQueue() {
        String text = inputArea.getText().trim();

        if (text.isEmpty()) {
            showError("Please enter data");
            return;
        }

        int[] numbers;
        try {
            String[] tokens = text.split("[,\\s]+");
            numbers = Arrays.stream(tokens)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (numbers.length == 0) {
                showError("No numbers found");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Error: Please enter only integers");
            return;
        }

        boolean useCustom = (implComboBox.getSelectedIndex() == 0);
        String result;

        try {
            if (useCustom) {
                LinkedQueue<Integer> queue = QueueEvenProcessor.createCustomQueue(numbers);
                statusLabel.setText("Processing (custom queue)");
                LinkedQueue<Integer> evenQueue = QueueEvenProcessor.keepEvenOnlyCustom(queue);
                result = evenQueue.toString();
            } else {
                Queue<Integer> queue = QueueEvenProcessor.createStdQueue(numbers);
                statusLabel.setText("Processing (standard queue)");
                Queue<Integer> evenQueue = QueueEvenProcessor.keepEvenOnlyStd(queue);
                result = evenQueue.toString();
            }


            outputArea.setText(result);

        } catch (Exception ex) {
            showError("Processing error: " + ex.getMessage());
        }
    }

    private void clearAll() {
        inputArea.setText("");
        outputArea.setText("");
        statusLabel.setText("Cleared");
    }

    private void showError(String message) {
        statusLabel.setText("Error: " + message);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

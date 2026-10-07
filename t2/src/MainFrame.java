import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class MainFrame extends JFrame {
    private MyLinkedList list = new MyLinkedList();

    private JTextField inputField = new JTextField(20);
    private JTextArea outputArea = new JTextArea(10, 30);

    public MainFrame() {
        setTitle("Локальные экстремумы списка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton inputButton = new JButton("Ввести из строки");
        JButton fileButton = new JButton("Загрузить из файла");
        JButton calculateButton = new JButton("Рассчитать");

        outputArea.setEditable(false);

        add(new JLabel("Числа (через пробел):"));
        add(inputField);
        add(inputButton);
        add(fileButton);
        add(calculateButton);
        add(new JScrollPane(outputArea));

        inputButton.addActionListener(e -> {
            list.clear();
            String text = inputField.getText();
            Scanner scanner = new Scanner(text);
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            outputArea.setText("Список загружен: " + list.toString());
        });

        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (Scanner scanner = new Scanner(file)) {
                    list.clear();
                    while (scanner.hasNextInt()) {
                        list.add(scanner.nextInt());
                    }
                    outputArea.setText("Загружено из файла: " + list.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка чтения файла!");
                }
            }
        });

        calculateButton.addActionListener(e -> {
            if (list.head == null) {
                outputArea.setText("Список пуст!");
                return;
            }
            int maxCount = list.countLocalMaxima();
            int minCount = list.countLocalMinima();

            outputArea.setText("Элементы списка: " + list.toString() + "\n\n" +
                    "Количество локальных максимумов: " + maxCount + "\n" +
                    "Количество локальных минимумов: " + minCount);
        });

        pack();
        setLocationRelativeTo(null);
    }
}
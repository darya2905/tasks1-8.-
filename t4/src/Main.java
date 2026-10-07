import ui.QueueApp;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QueueApp app = new QueueApp();
            app.setVisible(true);
        });
    }
}

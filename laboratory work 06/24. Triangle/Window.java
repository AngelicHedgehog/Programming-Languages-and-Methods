import com.company.CanvasPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Window {
    private JPanel mainPanel;
    private JSpinner sideSpinner;
    private CanvasPanel canvasPanel;
    private static final JFrame frame = new JFrame("Triangle"); // для изменения размеров окна в пределах всего класса

    public Window() {
        sideSpinner.setModel(new SpinnerNumberModel(181, 0, 500, 1)); // задание диапазона спинеру
        sideSpinner.addChangeListener(e -> {
            int x = (int) ((int) sideSpinner.getValue() / Math.sqrt(3));
            frame.setSize(Math.max(269, x * 2 + 60), Math.max(225, x * 2 + 110));
            canvasPanel.setSide(x);
        });
        canvasPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                canvasPanel.mousePress(e);
            }
        });
        canvasPanel.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                canvasPanel.mouseRelease(e);
            }
        });
        canvasPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                canvasPanel.mouseMove(e);
            }
        });
        canvasPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                canvasPanel.mouseDrag(e);
            }
        });
    }

    public static void main(String[] args) {
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new Window().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(700, 200);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}

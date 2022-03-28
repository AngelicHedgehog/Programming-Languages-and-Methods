import com.company.CanvasPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Window {
    private JPanel mainPanel;
    private CanvasPanel canvasPanel;
    private JSlider sliderN;

    public Window() {
        canvasPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasPanel.mouseClick(e);
            }
        });
        sliderN.addChangeListener(e -> canvasPanel.newSize(sliderN.getValue()));
        canvasPanel.getInputMap().put(KeyStroke.getKeyStroke("F5"), canvasPanel);
        canvasPanel.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 116)
                    canvasPanel.restart();
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        }); // привязка нажания кнопки F5 к событию перезапуска игры
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Крестики-нолики (F5 для перезапуска)");
        frame.setContentPane(new Window().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(700, 200);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
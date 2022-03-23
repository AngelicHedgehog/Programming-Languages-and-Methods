package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CanvasPanel extends JPanel {
    private double angle_cos = 0, angle_sin = -1;
    private double x = 104;
    private boolean mouse_on = false;
    private final double sin_30 = Math.sqrt(3) / 2;

    public void setSide(int new_x) {
        x = new_x;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xxx = {
                (int) (x * angle_cos + x + 10),
                (int) (x * (angle_cos * (-0.5) - angle_sin * sin_30) + x + 10),
                (int) (x * (angle_cos * (-0.5) + angle_sin * sin_30) + x + 10)};
        int[] yyy = {
                (int) (x * angle_sin + x + 10),
                (int) (x * (angle_sin * (-0.5) + angle_cos * sin_30) + x + 10),
                (int) (x * (angle_sin * (-0.5) - angle_cos * sin_30) + x + 10)};
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new GradientPaint((float) x, (float) x, Color.magenta, (float) xxx[0], (float) yyy[0], Color.yellow));
        g2.fillPolygon(xxx, yyy, 3);
        g2.setColor(Color.red);
        g2.fillOval(xxx[0] - 10, yyy[0] - 10, 20, 20);
    }

    public void mousePress() {if (mouse_on) setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));}

    public void mouseRelease(MouseEvent me) {mouseMove(me);}

    public void mouseMove(MouseEvent me) {
        int X = me.getX(), Y = me.getY();
        double x0 = x * angle_cos + x + 10, y0 = x * angle_sin + x + 10;
        mouse_on = Math.pow(X - x0, 2) + Math.pow(Y - y0, 2) <= 100;
        setCursor(Cursor.getPredefinedCursor(mouse_on ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
    }

    public void mouseDrag(MouseEvent me) {
        if (mouse_on) {
            double X = me.getX() - x - 10, Y = me.getY() - x - 10;
            double len = Math.sqrt(X * X + Y * Y);
            if (len != 0) {
                angle_cos = X / len;
                angle_sin = Y / len;
                repaint();
            }
        }
    }
}

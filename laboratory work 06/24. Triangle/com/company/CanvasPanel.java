package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CanvasPanel extends JPanel {
    private double angle_cos = 0, angle_sin = -1; //            параметры, задающие угол наклона треугольника
    private double x = 104; //                                  размер стороны треугольника
    private boolean mouse_on = false, mouse_press = false; //   флаги контроля мыши
    private final double sin_30 = Math.sqrt(3) / 2; //          заранее вычсленный синус 30

    public void setSide(int new_x) {
        x = new_x;
        repaint();
    } // изменение длины стороны

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
        g2.setPaint(new GradientPaint((float) x + 10, (float) x + 10, Color.magenta, (float) xxx[0], (float) yyy[0], Color.yellow));
        g2.fillPolygon(xxx, yyy, 3);
        g2.setColor(Color.red);
        g2.fillOval(xxx[0] - 10, yyy[0] - 10, 20, 20);
    } // отрисовка треугольника с кружком

    public void mousePress(MouseEvent e) {
        if (mouse_on && e.getButton() == 1) {
            mouse_press = true;
            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    } // изменение курсора и флага нажатия

    public void mouseRelease(MouseEvent e) {
        if (e.getButton() == 1) {
            mouse_press = false;
            mouseMove(e);
        }
    } // рассмотрение положения курсора после отпуска ЛКМ

    public void mouseMove(MouseEvent e) {
        int X = e.getX(), Y = e.getY();
        double x0 = x * angle_cos + x + 10, y0 = x * angle_sin + x + 10;
        mouse_on = Math.pow(X - x0, 2) + Math.pow(Y - y0, 2) <= 100;
        setCursor(Cursor.getPredefinedCursor(mouse_on ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
    } // расчёт значения флага нахождения мыши на кружке

    public void mouseDrag(MouseEvent e) {
        if (mouse_press) {
            double X = e.getX() - x - 10, Y = e.getY() - x - 10;
            double len = Math.sqrt(X * X + Y * Y);
            if (len != 0) {
                angle_cos = X / len;
                angle_sin = Y / len;
                repaint();
            }
        }
    } // изменение угла наклона треугольника при движении мышки с зажатой ЛКМ
}

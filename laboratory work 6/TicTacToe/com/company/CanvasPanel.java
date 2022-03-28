package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class CanvasPanel extends JPanel {
    private final int frame = 500; //                       размер поля в пикселях
    private int n = 3; //                                   размер поля в клетках
    private double side = (double) frame / n; //            размер клетки в пикселях
    private int goal = 3; //                                требуемое кол-во крестиков(ноликов) в ряд для победы
    private boolean[][][] field = new boolean[n][n][2]; //  значения клеток поля с разделением по типу закраски
    private boolean turn = false, win = false; //           параметры хода и окончания игры

    public void newSize(int N) {
        boolean[][][] new_field = new boolean[N][N][2];
        for (int i = Math.min(n, N) - 1; i >= 0; i--)
            System.arraycopy(field[i], 0, new_field[i], 0, Math.min(n, N));
        n = N;
        side = (double) frame / n;
        goal = n > 6 ? 5 : n > 4 ? 4 : 3;
        field = new_field;
        repaint();
    } // изменение размера поля и обновление игры

    public void restart() {
        field = new boolean[n][n][2];
        turn = false;
        win = false;
        repaint();
    } // перезапуск игры

    protected void paintComponent(Graphics g) {
        // ниже отрисовка границ клеток и всех крестиков и ноликов
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8));
        g2.setColor(Color.black);
        for (int i = 0; i <= n; i++) {
            g2.drawLine((int) (side * i), 0, (int) (side * i), frame);
            g2.drawLine(0, (int) (side * i), frame, (int) (side * i));
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (field[i][j][0]) {
                    g2.setColor(Color.red);
                    g2.drawLine((int) (side * (i + .25)), (int) (side * (j + .25)), (int) (side * (i + .75)), (int) (side * (j + .75)));
                    g2.drawLine((int) (side * (i + .25)), (int) (side * (j + .75)), (int) (side * (i + .75)), (int) (side * (j + .25)));
                } else if (field[i][j][1]) {
                    g2.setColor(Color.green);
                    g2.drawOval((int) (side * (i + .2)), (int) (side * (j + .2)), (int) (side * .6), (int) (side * .6));
                }
        // рассчёт наличия победной комбинации на поле
        win = false;
        g2.setColor(Color.orange);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (field[i][j][0] || field[i][j][1]) {
                    int who = field[i][j][0] ? 0 : 1;
                    boolean[] count = {true, true, true, true};
                    for (int k = 1; k < goal; k++) {
                        if (i + k >= n || count[0] && !field[i + k][j][who])
                            count[0] = false;
                        if (i + k >= n || j + k >= n || count[1] && !field[i + k][j + k][who])
                            count[1] = false;
                        if (j + k >= n || count[2] && !field[i][j + k][who])
                            count[2] = false;
                        if (i + k >= n || j - k < 0 || count[3] && !field[i + k][j - k][who])
                            count[3] = false;
                    }
                    if (count[0]) {
                        win = true;
                        g2.drawOval((int) (side * i), (int) (side * j), (int) (side * goal), (int) side);
                    }
                    if (count[1]) {
                        win = true;
                        Shape el = new Ellipse2D.Double(side * (i - goal * (Math.sqrt(2) - 1) / 2), side * (j + (goal * .5) - .5), side * goal * Math.sqrt(2), side);
                        g2.rotate(Math.PI / 4, side * (i + goal * .5), side * (j + goal * .5));
                        g2.draw(el);
                        g2.rotate(-Math.PI / 4, side * (i + goal * .5), side * (j + goal * .5));
                    }
                    if (count[2]) {
                        win = true;
                        g2.drawOval((int) (side * i), (int) (side * j), (int) side, (int) (side * goal));
                    }
                    if (count[3]) {
                        win = true;
                        Shape el = new Ellipse2D.Double(side * (i - goal * (Math.sqrt(2) - 1) / 2), side * (j - (goal * .5) + .5), side * goal * Math.sqrt(2), side);
                        g2.rotate(-Math.PI / 4, side * (i + goal * .5), side * (j - goal * .5 + 1));
                        g2.draw(el);
                        g2.rotate(Math.PI / 4, side * (i + goal * .5), side * (j - goal * .5 + 1));
                    }
                }
    }

    public void mouseClick(MouseEvent e) {
        if (!win) {
            int x = (int) (e.getX() / side), y = (int) (e.getY() / side);
            if (x >= 0 && y >= 0 && x < n && y < n && !(field[x][y][0] || field[x][y][1])) {
                field[x][y][turn ? 1 : 0] = true;
                turn = !turn;
                repaint();
            }
        }
    } // установка очередного крестика или нолика
}

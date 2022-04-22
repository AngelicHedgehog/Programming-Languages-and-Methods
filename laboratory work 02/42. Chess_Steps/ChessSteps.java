package com.company;

public class ChessSteps {

    private static final String rows = "12345678", cols = "ABCDEFHG";
    private byte[][] poses = new byte[32][4];
    private int move = 0, size = 4;

    public ChessSteps() {
        for (byte i = 0; i < 32; i++)
            poses[i][0] = (byte) ((1 << 6) + ((i < 16) ? i : i + 32));
    }

    private int numOfCell(String cell) {
        if (cell.length() != 2) return -1;
        int a = rows.indexOf(cell.charAt(1)),
                b = cols.indexOf(cell.toUpperCase().charAt(0));
        return (a != -1 && b != -1) ? a * 8 + b : -1;
    }

    private String cellByNum(long num) {
        return "" + cols.charAt((int) (num % 8)) + rows.charAt((int) ((num >> 3) % 8));
    }

    private void increaseMas() {
        byte[][] poses_new = new byte[32][size * 2];
        for (int i = 0; i < 32; i++)
            System.arraycopy(poses[i], 0, poses_new[i], 0, size);

        this.poses = poses_new;
        this.size *= 2;
    }

    public void newStep(String start, String end) {
        int from = numOfCell(start), to = numOfCell(end);
        if (from == -1 || to == -1) {
            System.out.println("Неправильный формат входный данных.");
            return;
        }

        for (int i = 0; i < 32; i++) {
            if (poses[i][move] == 64 + from) {
                if (move + 1 == size) increaseMas();

                for (int j = 0; j < 32; j++)
                    if (i == j)
                        poses[j][move + 1] = (byte) (64 + to);
                    else if (poses[j][move] == 64 + to)
                        poses[j][move + 1] = 0;
                    else
                        poses[j][move + 1] = poses[j][move];
                this.move++;
                return;
            }
        }

        System.out.println("На этой клетке сейчас нет фигур.");
    }

    public String wereNow(String start, int step) {
        int from = numOfCell(start);
        if (from == -1)
            return "Неправильный формат входный данных.";
        if (16 <= from && from < 48)
            return "На этой клетке в начале партии не было фигур.";
        if (step * 2 > move)
            return "Этот ход ещё не сделан.";

        byte figure = poses[(from >= 32) ? from - 32 : from][step * 2];
        if (64 > figure)
            return "На момент этого хода фигура съедена.";
        else
            return cellByNum(figure);
    }

    public String toString() {
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            format.append(cellByNum((i >= 16) ? (i + 32) : i)).append(" -> ");
            format.append((poses[i][move] >= 64) ? cellByNum(poses[i][move]) : "dead").append("\n");
        }
        return format.toString();
    }
}

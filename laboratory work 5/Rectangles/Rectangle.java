package com.company;

public class Rectangle {
    private final boolean convex;
    private final double area, sum_diags;

    Rectangle(double[] x, double[] y) {
        double t1 = ((x[3] - x[0]) * (y[1] - y[0]) - (y[3] - y[0]) * (x[1] - x[0])),
               t2 = ((x[3] - x[1]) * (y[2] - y[1]) - (y[3] - y[1]) * (x[2] - x[1])),
               t3 = ((x[3] - x[2]) * (y[0] - y[2]) - (y[3] - y[2]) * (x[0] - x[2])),
               t4 = ((x[0] - x[2]) * (y[1] - y[2]) - (y[0] - y[2]) * (x[1] - x[2]));
        convex = t1 * t2 * t3 * t4 > 0;
        area = ((x[0] - x[1]) * (y[0] + y[1]) +
                (x[1] - x[2]) * (y[1] + y[2]) +
                (x[2] - x[3]) * (y[2] + y[3]) +
                (x[3] - x[0]) * (y[3] + y[0])) / 2;
        sum_diags = Math.pow(x[0] - x[2], 2) +
                    Math.pow(y[0] - y[2], 2) +
                    Math.pow(x[1] - x[3], 2) +
                    Math.pow(y[1] - y[3], 2);
    }

    public double getArea() {return area;}

    public double getSumDiags() {return sum_diags;}

    public boolean isConvex() {return convex;}
}
package com.company;

public class Atom {
    private final double x, y, z;
    private static int n = 0;

    public Atom(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        n += 1;
    }

    public double getX() {return x;}

    public double getY() {return y;}

    public double getZ() {return z;}

    static public int count() {return n;}

    public double distanceTo(Atom other) {
        return Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2) + Math.pow(z - other.getZ(), 2);
    }
}

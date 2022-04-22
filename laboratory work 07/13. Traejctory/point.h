class Point {
    public:
        Point();
        Point(double, double, double);
        double getX();
        double getY();
        double getZ();
        double distanceTo(Point);
    private:
        double x, y, z;
};

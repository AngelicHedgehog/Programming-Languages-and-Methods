#include "point.cpp"

class Trajectory {
    public:
        Trajectory();
        Trajectory(const Trajectory&);
        ~Trajectory();
        int moveTime();
        Point& operator[] (int);
        void addPoint(Point);
        double moveDistance();
    private:
        double distance;
        int cap_mas;
        int all_time;
        Point *points;
};

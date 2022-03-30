#include "point.cpp"

class Trajectory {
    public:
        Trajectory();
        Trajectory(const Trajectory&);// конструктор копий
        ~Trajectory();// деструктор
        Trajectory operator=(Trajectory);// операция присваивания
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

#include "declaration.h"

Trajectory::Trajectory()
{
    Trajectory::distance = 0;
    Trajectory::cap_mas = 4;
    Trajectory::all_time = 0;
    Trajectory::points = new Point[4];
}

Trajectory::Trajectory(const Trajectory &obj)
{
    distance = obj.distance;
    cap_mas = obj.cap_mas;
    all_time = obj.all_time;
    points = new Point[cap_mas];
    for (int i = 0; i <= all_time; i++)
        points[i] = obj.points[i];
}

Trajectory:: ~Trajectory()
{
    delete []points;
}

int Trajectory::moveTime()
{
    return all_time;
}

Point& Trajectory::operator[] (int t)
{
    return points[t];
}

void Trajectory::addPoint(Point point)
{
    Trajectory::all_time++;
    if (all_time == cap_mas)
    {
        Point *buf_points = new Point[cap_mas];
        for (int i = 0; i < cap_mas; i++)
            buf_points[i] = points[i];
        delete []points;
        points = new Point[cap_mas * 2];
        for (int i = 0; i < cap_mas; i++)
            points[i] = buf_points[i];
        delete []buf_points;
        Trajectory::cap_mas *= 2;
    }
    Trajectory::points[all_time] = point;
    Trajectory::distance += points[all_time].distanceTo(points[all_time - 1]);
}

double Trajectory::moveDistance()
{
    return distance;
}

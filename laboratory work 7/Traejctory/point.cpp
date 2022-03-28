#include "point.h"
#include <cmath>

Point::Point()
{
    Point::x = 0;
    Point::y = 0;
    Point::z = 0;
}

Point::Point(double x, double y, double z)
{
    Point::x = x;
    Point::y = y;
    Point::z = z;
}

double Point::getX()
{
    return x;
}

double Point::getY()
{
    return y;
}

double Point::getZ()
{
    return z;
}

double Point::distanceTo(Point other)
{
    double del_x = x - other.getX(),
            del_y = y - other.getY(),
            del_z = z - other.getZ();
    return sqrt(del_x * del_x + del_y * del_y + del_z * del_z);
}

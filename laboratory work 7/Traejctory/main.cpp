#include <iostream>
#include "implementation.cpp"

int main()
{
    Trajectory way;
    way.addPoint(Point(3.0, 0.0, 0.0)); //d = 0 + 3
    way.addPoint(Point(3.0, 5.0, 0.0)); //d = 3 + 5
    way.addPoint(Point(-2.0, 5.0, 0.0));//d = 8 + 5
    way.addPoint(Point(-2.0, 5.0, 1.0));//d = 13 + 1

    std::cout << way.moveDistance() << '\n' << way.moveTime() << '\n';

    for (int i = 0; i < 5; i++)
        std::cout << way[i].getX() << ' ' << way[i].getY() << ' ' << way[i].getZ() << '\n';

    return 0;
}

#include <iostream>
#include "implementation.cpp"

int main()
{
    Trajectory way;// создание объекта класса в автоматической памяти
    way.addPoint(Point(3.0, 0.0, 0.0)); //d = 0 + 3
    // передачу объекта класса по значению в функцию
    // 3. добавление нового положения точки в конец траектории
    way.addPoint(Point(3.0, 5.0, 0.0)); //d = 3 + 5
    way.addPoint(Point(-2.0, 5.0, 0.0));//d = 8 + 5
    way.addPoint(Point(-2.0, 5.0, 1.0));//d = 13 + 1
    Trajectory way_ = way;// присваивание объекта класса переменной

    std::cout << way_.moveDistance() << '\n';// 4. вычисление пройденного расстояния
    std::cout << way_.moveTime() << '\n';// 1. получение общего времени движения точки

    for (int i = 0; i < 5; i++)
        std::cout << way_[i].getX() << ' ' <<
                     way_[i].getY() << ' ' <<
                     way_[i].getZ() << '\n';// 2. получение ссылки на положение точки в момент времени t

    return 0;
}

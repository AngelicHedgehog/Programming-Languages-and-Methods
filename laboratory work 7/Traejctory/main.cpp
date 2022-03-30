#include <iostream>
#include "implementation.cpp"

int main()
{
    Trajectory way;// �������� ������� ������ � �������������� ������
    way.addPoint(Point(3.0, 0.0, 0.0)); //d = 0 + 3
    // �������� ������� ������ �� �������� � �������
    // 3. ���������� ������ ��������� ����� � ����� ����������
    way.addPoint(Point(3.0, 5.0, 0.0)); //d = 3 + 5
    way.addPoint(Point(-2.0, 5.0, 0.0));//d = 8 + 5
    way.addPoint(Point(-2.0, 5.0, 1.0));//d = 13 + 1
    Trajectory way_ = way;// ������������ ������� ������ ����������

    std::cout << way_.moveDistance() << '\n';// 4. ���������� ����������� ����������
    std::cout << way_.moveTime() << '\n';// 1. ��������� ������ ������� �������� �����

    for (int i = 0; i < 5; i++)
        std::cout << way_[i].getX() << ' ' <<
                     way_[i].getY() << ' ' <<
                     way_[i].getZ() << '\n';// 2. ��������� ������ �� ��������� ����� � ������ ������� t

    return 0;
}

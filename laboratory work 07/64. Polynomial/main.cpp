#include "implementation.cpp"

int main()
{
    Polynomial p = Polynomial(3, new Fraction[4]{
                              Fraction(25, 5),
                              Fraction(12, 6),
                              Fraction(-27, 9),
                              Fraction(4, 2)});
    // �������� ������� ������ �� �������� � �������
    // �������� ������� ������ � �������������� ������
    Polynomial f = p;// ������������ ������� ������ ����������
    int f_power = f.getPower(); // 2. ��������� ������� ��������;
    std::cout << "f(x) = ";
    for (int i = 0; i <= f_power; i++)
    {
        f[i].display(); // 3. ��������� ������ �� ��������� ����������� ��������;
        std::cout << " * x^" << i << (i == f_power ? "" : " + ");
    }
    std::cout << '\n';
    std::cout << "f(3) = ";
    f.eval(3).display();// 1. ���������� �������� ��� ��������� x;
    std::cout << '\n';


    Polynomial g = Polynomial(2, new Fraction[3]{
                              Fraction(-15, 15),
                              Fraction(7, 7),
                              Fraction(0, 4)});
    int g_power = g.getPower();
    std::cout << "g(x) = ";
    g.display();
    std::cout << '\n';
    std::cout << "g(3) = ";
    g.eval(3).display();
    std::cout << '\n';

    Polynomial h = f / g;// 4. ������� �� �������
    std::cout << "f(x) / g(x) = ";
    h.display();

    return 0;
}

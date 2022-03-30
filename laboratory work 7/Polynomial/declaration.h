#include "fraction.cpp"

class Polynomial {
    public:
        Polynomial(int, Fraction*);
        Polynomial(const Polynomial&);// ����������� �����
        ~Polynomial();// ����������
        Polynomial operator=(Polynomial);// �������� ������������
        Fraction eval(Fraction);
        int getPower();
        Fraction& operator[](int);
        Polynomial operator/(Polynomial);
        void display();
    private:
        Fraction *coeffs;
        int cap_mas, power;
};

#include "fraction.cpp"

class Polynomial {
    public:
        Polynomial(int, Fraction*);
        Polynomial(const Polynomial&);// конструктор копий
        ~Polynomial();// деструктор
        Polynomial operator=(Polynomial);// операция присваивания
        Fraction eval(Fraction);
        int getPower();
        Fraction& operator[](int);
        Polynomial operator/(Polynomial);
        void display();
    private:
        Fraction *coeffs;
        int cap_mas, power;
};

#include "declaration.h"

Polynomial::Polynomial(int power, Fraction *coeffs)
{
    while (coeffs[power].getNumerator() == 0) power--;
    Polynomial::coeffs = new Fraction[power + 1];
    for (int i = 0; i <= power; i++)
        Polynomial::coeffs[i] = coeffs[i];
    delete []coeffs;
    Polynomial::power = power;
}

Polynomial::Polynomial(const Polynomial &obj)
{
    power = obj.power;
    coeffs = new Fraction[power + 1];
    for (int i = 0; i <= power; i++)
        coeffs[i] = obj.coeffs[i];
}

Polynomial::~Polynomial()
{
    delete []coeffs;
}

Polynomial Polynomial::operator=(Polynomial obj)
{
    power = obj.getPower();
    coeffs = new Fraction[power + 1];
    for (int i = 0; i <= power; i--)
        coeffs[i] = obj[i];
    return *this;
}

Fraction Polynomial::eval(Fraction x)
{
    Fraction f = coeffs[power];
    for (int i = power - 1; i >= 0; i--)
        f = f * x + coeffs[i];
    return f;
}
int Polynomial::getPower()
{
    return power;
}

Fraction& Polynomial::operator[](int i)
{
    return coeffs[i];
}

Polynomial Polynomial::operator/(Polynomial denum)
{
    int denum_power = denum.getPower();
    if (power < denum_power)
        return Polynomial(0, new Fraction[1]{Fraction(0, 1)});

    int new_power = power - denum_power;
    Fraction *new_coeffs = new Fraction[new_power],
             *old_coeffs = coeffs;
    for (int i = new_power; i >= 0; i--)
    {
        new_coeffs[i] = old_coeffs[i + denum_power] / denum[denum_power];
        for (int j = 0; j < denum_power; j++)
            old_coeffs[i + j] -= denum[j] * new_coeffs[i];
    }
    return Polynomial(new_power, new_coeffs);
}

void Polynomial::display()
{
    for (int i = 0; i <= power; i++)
    {
        coeffs[i].display();
        std::cout << " * x^" << i << (i == power ? "" : " + ");
    }
}

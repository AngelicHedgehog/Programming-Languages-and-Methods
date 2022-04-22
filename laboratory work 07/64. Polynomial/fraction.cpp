#include <iostream>
#include "fraction.h"

Fraction::Fraction(int numerator = 0, int denumerator = 1)
{
    int a = numerator, b = denumerator, c;
    while (a != 0)
    {
        c = a;
        a = b % a;
        b = c;
    }
    if (b == 0)
    {
        num = 0;
        denum = 0;
    }
    else
    {
        num = numerator / b;
        denum = denumerator / b;
        if (denum < 0)
        {
            num *= -1;
            denum *= -1;
        }
    }
}


int Fraction::getNumerator()
{
    return num;
}

int Fraction::getDenumerator()
{
    return denum;
}

Fraction Fraction::operator+(Fraction other_fraction)
{
    int num2 = other_fraction.getNumerator(),
        denum2 = other_fraction.getDenumerator();
    return Fraction(num * denum2 + num2 * denum, denum * denum2);
}

Fraction& Fraction::operator-=(Fraction other_fraction)
{
    int num2 = other_fraction.getNumerator(),
        denum2 = other_fraction.getDenumerator();
    num = num * denum2 - num2 * denum;
    denum = denum * denum2;
    return *this;
}

Fraction Fraction::operator*(Fraction other_fraction)
{
    return Fraction(num * other_fraction.getNumerator(),
                    denum * other_fraction.getDenumerator());
}

Fraction Fraction::operator/(Fraction other_fraction)
{
    return Fraction(num * other_fraction.getDenumerator(),
                    denum * other_fraction.getNumerator());
}

void Fraction::display()
{
    std::cout << num << '/' << denum;
}

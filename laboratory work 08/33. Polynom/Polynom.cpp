#include "Polynom.h"
#include <iostream>
#include <cstddef>
#include <math.h>
using namespace std;

template<typename T, int N>
Polynom<T, N>::Polynom(T* coefficients)
{
	coefs = coefficients;
}

template<typename T, int N>
T& Polynom<T, N>::operator[](int i)
{
	return coefs[i];
}

template<typename T, int N>
T Polynom<T, N>::eval(T x)
{
	T f_x = coefs[N];
	for (size_t i = N; i > 0; i--) {
		f_x = f_x * x + coefs[i - 1];
    }
	return f_x;
}

template<typename T, int N>
template<int M>
Polynom<T, (N > M ? N : M)> Polynom<T, N>::operator+(Polynom<T, M> other)
{
	constexpr int new_power = max(N, M);
	T *new_coefs = new T[new_power];
	for (size_t i = 0; i <= new_power; i++)
		if (i > N)
			new_coefs[i] = other[i];
		else if (i > M)
			new_coefs[i] = coefs[i];
		else
			new_coefs[i] = coefs[i] + other[i];
	return Polynom<T, new_power>(new_coefs);
}

template<typename T, int N>
Polynom<T, (N > 0 ? N - 1 : 0)> Polynom<T, N>::diff()
{
    constexpr int new_power = max(N - 1, 0);
    T *new_coefs = new T[new_power];
    new_coefs[0] = 0;
    for (size_t i = N; i > 0; i--)
        new_coefs[i - 1] = coefs[i] * i;
    return Polynom<T, new_power>(new_coefs);
}

template<typename T, int N>
ostream& operator<<(ostream &os, Polynom<T, N> &poly)
{
    os << poly[0] << "*x^0";
    for (size_t i = 1; i <= N; i++)
    {
        os << " + " << poly[i] << "*x^" << i;
    }
    return os;
}





template<typename T>
Polynom<T, 2>::Polynom(T* coefficients)
{
	a = coefficients[2];
	b = coefficients[1];
	c = coefficients[0];
}

template<typename T>
T& Polynom<T, 2>::operator[](int i)
{
	return i == 2 ? a : i == 1 ? b : c;
}

template<typename T>
T Polynom<T, 2>::eval(T x)
{
	return (a * x + b) * x + c;
}

template<typename T>
template<int M>
Polynom<T, (2 > M ? 2 : M)> Polynom<T, 2>::operator+(Polynom<T, M> other)
{
	constexpr int new_power = max(2, M);
	T *new_coefs = new T[new_power];
	new_coefs[0] = c;
	new_coefs[1] = b;
	new_coefs[2] = a;
	for (size_t i = 0; i <= new_power; i++)
		new_coefs[i] += other[i];
	return Polynom<T, new_power>(new_coefs);
}

template<typename T>
Polynom<T, 1> Polynom<T, 2>::diff()
{
    return Polynom<T, 1>(new T[2] {b, a * 2});
}

template<typename T>
T* Polynom<T, 2>::roots()
{
    T D = b * b - 4 * a * c;
    if (D < 0)
        return new T[0] {};
    if (D == 0)
        return new T[1] {-b / 2 / a};
    T D_sqrt = sqrt(D);
    return new T[2] {(-D_sqrt - b) / 2 / a, (D_sqrt - b) / 2 / a};
}

template<typename T>
ostream& operator<<(ostream &os, Polynom<T, 2> &poly)
{
    return os << poly[0] << "*x^0 + " << poly[1] << "*x^1 + " << poly[2] << "*x^2";
}





template<typename T>
Polynom<T, 1>::Polynom(T* coefficients)
{
	a = coefficients[1];
	b = coefficients[0];
}

template<typename T>
T& Polynom<T, 1>::operator[](int i)
{
	return i == 1 ? a : b;
}

template<typename T>
T Polynom<T, 1>::eval(T x)
{
	return a * x + b;
}

template<typename T>
template<int M>
Polynom<T, (1 > M ? 1 : M)> Polynom<T, 1>::operator+(Polynom<T, M> other)
{
	constexpr int new_power = max(1, M);
	T *new_coefs = new T[new_power];
	new_coefs[0] = b;
	new_coefs[1] = a;
	for (size_t i = 0; i <= new_power; i++)
		new_coefs[i] += other[i];
	return Polynom<T, new_power>(new_coefs);
}

template<typename T>
Polynom<T, 0> Polynom<T, 1>::diff()
{
    return Polynom<T, 0>(new T[1] {a});
}

template<typename T>
T Polynom<T, 1>::root()
{
    return -b / a;
}

template<typename T>
ostream& operator<<(ostream &os, Polynom<T, 1> &poly)
{
    return os << poly[0] << "*x^0 + " << poly[1] << "*x^1";
}

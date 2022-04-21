#include "shape.h"
using namespace std;

template<typename T>
Shape<T>::Shape(T x0, T y0, T x1, T y1)
{
    lambda = [=](T x, T y){return x0 <= x && x <= x1 && y0 <= y && y <= y1;};
}

template<typename T>
Shape<T>::Shape(T x0, T y0, T r)
{
    lambda = [=](T x, T y){return (x - x0) * (x - x0) + (y - y0) * (y - y0) <= r * r;};
}

template<typename T>
Shape<T> Shape<T>::operator+(Shape<T> other)
{
    return Shape<T>([l = lambda, l_ = other.get_lambda()](T x, T y){return l(x, y) || l_(x, y);});
}

template<typename T>
Shape<T> Shape<T>::operator-(Shape<T> other)
{
    return Shape<T>([l = lambda, l_ = other.get_lambda()](T x, T y){return l(x, y) && !l_(x, y);});
}

template<typename T>
bool Shape<T>::operator()(T x, T y)
{
    return lambda(x, y);
}

template<typename T>
Shape<T>::Shape(function<bool(T, T)> lambda)
{
    this->lambda = lambda;
}

template<typename T>
function<bool(T, T)> Shape<T>::get_lambda()
{
    return lambda;
}

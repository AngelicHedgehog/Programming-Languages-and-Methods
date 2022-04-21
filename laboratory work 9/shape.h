#include <functional>

template<typename T>
class Shape {
    public:
        Shape(T, T, T, T);
        Shape(T, T, T);
        Shape<T> operator+(Shape<T>);
        Shape<T> operator-(Shape<T>);
        bool operator()(T, T);
    private:
        Shape(std::function<bool(T, T)>);
        std::function<bool(T, T)> get_lambda();
        std::function<bool(T, T)> lambda;
};

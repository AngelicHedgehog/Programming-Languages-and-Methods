#include "triangle_digits.cpp"
#include <iostream>

void print(int n, int* digits)
{
    for (int* i : TriangleDigits(n, digits))
        cout << i[0] << ' ' << i[1] << ' ' << i[2] << endl;
    cout << endl;
}

int main()
{
    print(6, new int[6]{40, 48, 95, 92, 17, 26});
    print(3, new int[3]{3, 5, 4});
    print(3, new int[3]{3, 8, 4});
    print(2, new int[2]{2, 3});
    print(0, new int[0]{});
    return 0;
}

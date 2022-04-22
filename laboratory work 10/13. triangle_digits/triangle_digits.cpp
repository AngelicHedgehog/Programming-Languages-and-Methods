#include "triangle_digits.h"
using namespace std;

TriangleDigits::TriangleDigits(int n, int *digits)
{
    this->n = n;
    this->digits = digits;
}

Iterator TriangleDigits::begin() const
{
    int* it = new int[n + 4]{0, 1, 2, n};
    for (size_t i = 0; i < n; i++) it[i + 4] = digits[i];
    return it;
}

Iterator TriangleDigits::end() const
{
    int* it = new int[n + 4]{n, 0, 0, n};
    for (size_t i = 0; i < n; i++) it[i + 4] = digits[i];
    return it;
}

Iterator::Iterator(int *p) : p(p) { }

Iterator::Iterator(const Iterator& it) : p(it.p) { }

bool Iterator::operator!=(Iterator const& other) const
{
    for (;;)
    {
        if (p[0] >= p[3])
            return false;
        int m = max(max(p[p[0] + 4], p[p[1] + 4]), p[p[2] + 4]),
            s = p[p[0] + 4] + p[p[1] + 4] + p[p[2] + 4] - m;
        if (m > s)
        {
            if (++p[2] >= p[3])
            {
                if (++p[1] >= p[3])
                    p[1] = ++p[0] + 1;
                p[2] = p[1] + 1;
            }
        }
        else
            return true;
    }
}

int* Iterator::operator*() const
{
    return new int[3]{p[p[0] + 4], p[p[1] + 4], p[p[2] + 4]};
}

Iterator& Iterator::operator++()
{
    if (++p[2] >= p[3])
    {
        if (++p[1] >= p[3])
            p[1] = ++p[0] + 1;
        p[2] = p[1] + 1;
    }
    return *this;
}

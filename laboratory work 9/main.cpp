#include "shape.cpp"
#include <iostream>

void draw(Shape<int> s, int x0, int y0, int x1, int y1)
{
    for (int y = y1; y >= y0; y--)
    {
        for (int x = x0; x <= x1; x++)
            cout << (s(x, y) ? "**" : "__");
        cout << endl;
    }
}

int main()
{
    Shape body = Shape<int>(0, 0, 17);
    Shape smile1 = Shape<int>(0, -5, 10);
    Shape smile2 = Shape<int>(0, 0, 10);
    Shape eye11 = Shape<int>(-8, 4, 4);
    Shape eye21 = Shape<int>(8, 4, 4);
    Shape eye12 = Shape<int>(-7, 5, 1);
    Shape eye22 = Shape<int>(9, 5, 1);
    Shape hat1 = Shape<int>(-18, 12, 18, 17);
    Shape hat2 = Shape<int>(-10, 17, 10, 33);

    Shape face = body - (smile1 - smile2) - eye11 + eye12 - (eye21 - eye22) + hat1 + hat2;

    draw(face, -20, -20, 20, 35);
    return 0;
}

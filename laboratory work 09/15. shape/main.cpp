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
    /**/
    Shape s0 = Shape<int>(0, 0, 15);
    Shape s1 = Shape<int>(5, 5, 10);
    Shape s2 = Shape<int>(2, -6, 9, 3);
    Shape s3 = Shape<int>(-15, -10, -8, -3);
    Shape s4 = Shape<int>(-8, -3, 3);
    Shape s5 = Shape<int>(3, 15, 4);
    Shape s = s0 - s1 + s2 - (s3 - s4) + s5;
    draw(s, -20, -20, 20, 20);
    /*/
    Shape head = Shape<int>(0, 0, 17);
    Shape smile1 = Shape<int>(0, -5, 10);
    Shape smile2 = Shape<int>(0, 0, 10);
    Shape eye11 = Shape<int>(-8, 4, 4);
    Shape eye21 = Shape<int>(8, 4, 4);
    Shape eye12 = Shape<int>(-7, 5, 1);
    Shape eye22 = Shape<int>(9, 5, 1);
    Shape hat1 = Shape<int>(-18, 12, 18, 17);
    Shape hat2 = Shape<int>(-10, 17, 10, 33);
    Shape face = head - (smile1 - smile2) - eye11 + eye12 - (eye21 - eye22) - hat1 + (hat1 - head) + hat2;
    draw(face, -20, -20, 20, 35);
    /**/
    return 0;
}

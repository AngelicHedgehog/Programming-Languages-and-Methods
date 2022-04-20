#include "Polynom.cpp"

int main()
{
	Polynom f = Polynom<int, 2>(new int[3]{5, 6, 1});
    cout << "f(x) = " << f << endl;
	Polynom g = Polynom<int, 3>(new int[4]{-4, -6, -1, 1});
	cout << "g(x) = " << g << endl;
	Polynom f_g = f + g;
	cout << "f(x) + g(x) = " << f_g << endl;
	cout << "f(2) = " << f_g.eval(2) << endl;
	Polynom f_ = f.diff();
	cout << "f'(x) = " << f_ << endl;
	int* roots = f.roots();
	cout << "f(x) = 0 => x = {" << roots[0] << "; " << roots[1] << '}' << endl;
	cout << "f'(x) = 0 => x = " << f_.root() << endl;
    return 0;
}

template<typename T, int N>
class Polynom {
	public:
		Polynom(T*);
		T& operator[](int);
		T eval(T);
		template<int M>
		Polynom<T, (N > M ? N : M)> operator+(Polynom<T, M>);
		Polynom<T, (N > 0 ? N - 1 : 0)> diff();
	private:
		T* coefs;
};

template<typename T>
class Polynom<T, 2> {
	public:
		Polynom(T*);
		T& operator[](int);
		T eval(T);
		template<int M>
		Polynom<T, (2 > M ? 2 : M)> operator+(Polynom<T, M>);
		Polynom<T, 1> diff();
		T* roots();
	private:
		T a, b, c;
};

template<typename T>
class Polynom<T, 1> {
	public:
		Polynom(T*);
		T& operator[](int);
		T eval(T);
		template<int M>
		Polynom<T, (1 > M ? 1 : M)> operator+(Polynom<T, M>);
		Polynom<T, 0> diff();
		T root();
	private:
		T a, b;
};

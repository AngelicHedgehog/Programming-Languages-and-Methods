class Fraction {
    public:
        Fraction(int, int);
        int getNumerator();
        int getDenumerator();
        Fraction operator+(Fraction);
        Fraction& operator-=(Fraction);
        Fraction operator*(Fraction);
        Fraction operator/(Fraction);
        void display();
    private:
        int num, denum;
};

#include <iterator>

class Iterator;

class TriangleDigits
{
public:
    TriangleDigits(int, int*);
    typedef Iterator const_iterator;
    const_iterator begin() const;
    const_iterator end() const;
private:
    int n;
    int* digits;
};

class Iterator: public std::iterator<std::forward_iterator_tag, const int>
{
    friend class TriangleDigits;
private:
    Iterator(int* p);
public:
    Iterator(const Iterator&);
    bool operator!=(Iterator const&) const;
    int* operator*() const;
    Iterator& operator++();
private:
    int* p;
    void next();
};

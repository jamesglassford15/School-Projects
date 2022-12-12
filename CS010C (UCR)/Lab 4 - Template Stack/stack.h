#include <vector>
#include <stdexcept>
#include <iostream>
using namespace std;

const int MAX_SIZE = 20;
template <typename T>
class stack
{
private:
    T data[MAX_SIZE];
    int size;

public:
    stack();
    void push(T val);
    void pop();
    void pop_two();
    T top();
    bool empty();
};
template <typename T>
stack<T>::stack()
{
    size = 0;
};
template <typename T>
void stack<T>::push(T val)
{
    if (size == MAX_SIZE)
    {
        throw overflow_error("Called push on full stack.");
    }
    else
    {
        data[size] = val;
        size++;
    }
};
template <typename T>
void stack<T>::pop()
{
    if (size == 0)
    {
        throw out_of_range("Called pop on empty stack.");
    }
    else
    {
        size--;
    }
};
template <typename T>
void stack<T>::pop_two()
{
    if (size == 1)
    {
        throw out_of_range("Called pop_two on a stack of size 1.");
    }
    else if (size == 0)
    {
        throw out_of_range("Called pop_two on empty stack.");
    }
    else
    {
        size = size - 2;
    }
};

template <typename T>
T stack<T>::top()
{
    if (size == 0)
    {
        throw underflow_error("Called top on empty stack.");
    }
    return data[size - 1];
};

template <typename T>
bool stack<T>::empty()
{
    if (size == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}
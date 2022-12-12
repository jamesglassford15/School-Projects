#ifndef NODE_H
#define NODE_H
#include<iostream>
#include<string>
using namespace std;

class Node {
    private:
    string data;
    Node *leftChild;
    Node *rightChild;
    Node *parentNode;
    int count;
    public:
    Node();
    Node(string newData);
    string getData();
    Node *getLeftChild();
    Node *getRightChild();
    int getCount();
    void setData(string newData);
    void setLeftChild(Node *newChild);
    void setRightChild(Node *newChild);
    void setCount(int newCount);
    Node* getParent();
    void setParent(Node*);
};

#endif
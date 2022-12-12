#include"Node.h"

Node::Node()
{
    this->data = "";
    this->count = 0;
    this->leftChild = 0;
    this->rightChild = 0;
}

Node::Node(string newData)
{
    this->data = newData;
    this->count = 1;
    this->leftChild = 0;
    this->rightChild = 0;
}

string Node::getData()
{
    return data;
}

Node *Node::getLeftChild()
{
    return leftChild;
}

Node *Node::getRightChild()
{
    return rightChild;
}

int Node::getCount()
{
    return count;
}

void Node::setData(string newData)
{
    this->data = newData;
}

void Node::setLeftChild(Node *newChild)
{
    this->leftChild = newChild;
}

void Node::setRightChild(Node *newChild)
{
    this->rightChild = newChild;
}

void Node::setCount(int newCount)
{
    this->count = newCount;
}

Node* Node::getParent() {
    return parentNode;
}

void Node::setParent(Node* newParent) {
    this->parentNode = newParent;
}
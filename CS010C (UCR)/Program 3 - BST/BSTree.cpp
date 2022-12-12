#include"BSTree.h"
#include<string>

BSTree::BSTree()
{
    this->root = 0;
}

BSTree::~BSTree()
{
    deleteTree(root);
}

void BSTree::deleteTree(Node *node) {
    if(node) {
        if(node->getLeftChild()) {
            deleteTree(node->getLeftChild());
        }
        if(node->getRightChild()) {
            deleteTree(node->getRightChild());
        }
    node = 0;
    }
    
}

void BSTree::insert(const string &newString)
{
    Node *newNode = new Node(newString);
    if (this->root == 0)
    {
        this->root = newNode;
    }
    else
    {
        Node *currNode = root;
        insert(currNode, newNode);
    }
}

void BSTree::insert(Node *currNode, Node *newNode) {
    //will recursively call if node to be inserted has to move down the tree
    if(currNode->getData() == newNode->getData()) {
        currNode->setCount(currNode->getCount() + 1);
    }
    else if(currNode->getData() > newNode->getData()) {
        if(currNode->getLeftChild() == 0) {
            currNode->setLeftChild(newNode);
        }
        else {
            insert(currNode->getLeftChild(), newNode);
        }
    }
    else {
        if(currNode->getRightChild() == 0) {
            currNode->setRightChild(newNode);
        }
        else {
            insert(currNode->getRightChild(), newNode);
        }
    }
}

//takes key and sets up for remove
void BSTree::remove(const string &key)
{
    Node *currNode = root;
    Node *parentNode = root;
    if(root && search(key)) {
        remove(parentNode, currNode, key);
    }
}

//recursive helper function to search through and find node to delete
void BSTree::remove(Node *parentNode, Node *currNode, string key) {
    if(currNode->getData() == key) {
        if(currNode->getCount() > 1) {
            currNode->setCount(currNode->getCount() - 1);
        }
        else {
            remove(currNode, parentNode);
        }
    }
    else if(currNode->getData() < key) {
        parentNode = currNode;
        remove(parentNode, currNode->getRightChild(), key);
    }
    else {
        parentNode = currNode;
        remove(parentNode, currNode->getLeftChild(), key);
    }
}

//removes node from the BST
void BSTree::remove(Node *deleteNode, Node *parentNode) {
    if(deleteNode->getLeftChild() == 0 && deleteNode->getRightChild() == 0) {
        if(deleteNode == root) {
            root = 0;
        }
        if(parentNode->getLeftChild() == deleteNode) {
            parentNode->setLeftChild(0);
        }
        else {
            parentNode->setRightChild(0);
        }
    }
    else if (deleteNode->getLeftChild() == 0) {
        parentNode = deleteNode;
        Node *toReplace = deleteNode->getRightChild();
        while(toReplace->getLeftChild() != 0) {
            parentNode = toReplace;
            toReplace = toReplace->getLeftChild();
        }
        deleteNode->setData(toReplace->getData());
        deleteNode->setCount(toReplace->getCount());
        remove(toReplace, parentNode);

    }
    else {
        parentNode = deleteNode;
        Node *toReplace = deleteNode->getLeftChild();
        while(toReplace->getRightChild() != 0) {
            parentNode = toReplace;
            toReplace = toReplace->getRightChild();
        }
        deleteNode->setData(toReplace->getData());
        deleteNode->setCount(toReplace->getCount());
        remove(toReplace, parentNode);
    }
}

bool BSTree::search(const string &key) const {
    Node *currNode = root;
    while(currNode) {
        if (currNode->getData() == key) {
            return true;
        }
        else if (currNode->getData() < key) {
            currNode = currNode->getRightChild();
        }
        else {
            currNode = currNode->getLeftChild();
        }
    }
    return false;
}

string BSTree::largest() const {
    if(root == 0) {
        return "";
    }
    Node *currNode = root;
    while(currNode->getRightChild() != 0) {
        currNode = currNode->getRightChild();
    }
    return currNode->getData();
}

string BSTree::smallest() const {
    if(root == 0) {
        return "";
    }
    Node *currNode = root;
    while(currNode->getLeftChild() != 0) {
        currNode = currNode->getLeftChild();
    }
    return currNode->getData();
}

int BSTree::height(const string &key) const {
    if(search(key)) {
        int height = 0;
        int highestNode = 0;
        Node *currNode = root;
        while(currNode != 0) {
            if(currNode->getData() == key) {
                highestNode = highest(0, currNode);
                currNode = 0;
            }
            else if (currNode->getData() < key) {
                height++;
                currNode = currNode->getRightChild();
            }
            else {
                height++;
                currNode = currNode->getLeftChild();
            }
        }
        return highestNode;
    }
    else {
        return -1;
    }
}

int BSTree::highest(int h, Node *node) const {
    int highestNode = h;
    int left_height = highestNode;
    int right_height = highestNode;
    if(node->getLeftChild()) {
        left_height = highest(highestNode, node->getLeftChild()) + 1;
    }
    if(node->getRightChild()) {
        right_height = highest(highestNode, node->getRightChild()) + 1;
    }
    if(left_height > right_height) {
        highestNode += left_height;
    }
    else {
        highestNode += right_height;
    }
    return highestNode;
}
void BSTree::preOrder() const {
    this->preOrder(root);
    cout << endl;
}

void BSTree::preOrder(Node *node) const {
    if(node == 0) {
        return;
    }
    cout << node->getData() << "(" << node->getCount() << "), ";
    preOrder(node->getLeftChild());
    preOrder(node->getRightChild());
}

void BSTree::postOrder() const {
    this->postOrder(root);
    cout << endl;
}

void BSTree::postOrder(Node *node) const {
    if(node == 0) {
        return;
    }
    postOrder(node->getLeftChild());
    postOrder(node->getRightChild());
    cout << node->getData() << "(" << node->getCount() << "), ";
}

void BSTree::inOrder() const {
    this->inOrder(root);
    cout << endl;
}

void BSTree::inOrder(Node *node) const {
    if(node == 0) {
        return;
    }
    inOrder(node->getLeftChild());
    cout << node->getData() << "(" << node->getCount() << "), ";
    inOrder(node->getRightChild());
}
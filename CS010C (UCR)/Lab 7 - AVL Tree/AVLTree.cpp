#include"AVLTree.h"
#include<string>
#include<iostream>
#include<fstream>

AVLTree::AVLTree()
{
    this->root = 0;
}

AVLTree::~AVLTree()
{
    deleteTree(root);
}

void AVLTree::deleteTree(Node *node) {
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

void AVLTree::insert(const string &newString)
{
    Node *newNode = new Node(newString);
    if (this->root == 0)
    {
        this->root = newNode;
    }
    else
    {
        Node *currNode = root;
        //actual insertion
        insert(currNode, newNode);
        //checks whole tree to make sure everything is still balanced
        balanceTree(root);
    }
}

void AVLTree::insert(Node *currNode, Node *newNode) {
    //will recursively call if node to be inserted has to move down the tree
    if(currNode->getData() == newNode->getData()) {
        currNode->setCount(currNode->getCount() + 1);
    }
    else if(currNode->getData() > newNode->getData()) {
        if(currNode->getLeftChild() == 0) {
            currNode->setLeftChild(newNode);
            newNode->setParent(currNode);
        }
        else {
            insert(currNode->getLeftChild(), newNode);
        }
    }
    else {
        if(currNode->getRightChild() == 0) {
            currNode->setRightChild(newNode);
            newNode->setParent(currNode);
        }
        else {
            insert(currNode->getRightChild(), newNode);
        }
    }
}

void AVLTree::visualizeTree(const string &outputFilename){
    ofstream outFS(outputFilename.c_str());
    if(!outFS.is_open()){
        cout<<"Error"<<endl;
        return;
    }
    outFS<<"digraph G {"<<endl;
    visualizeTree(outFS,root);
    outFS<<"}";
    outFS.close();
    string jpgFilename = outputFilename.substr(0,outputFilename.size()-4)+".jpg";
    string command = "dot -Tjpg " + outputFilename + " -o " + jpgFilename;
    system(command.c_str());
}

void AVLTree::visualizeTree(ofstream & outFS, Node *n){
    if(n){
        if(n->getLeftChild()){
            visualizeTree(outFS,n->getLeftChild());
            outFS<<n->getData() <<" -> " <<n->getLeftChild()->getData()<<";"<<endl;    
        }

        if(n->getRightChild()){
            visualizeTree(outFS,n->getRightChild());
            outFS<<n->getData() <<" -> " <<n->getRightChild()->getData()<<";"<<endl;    
        }
    }
}

int AVLTree::height(const string &key, Node *node) {
    //will only check height if key is in the tree
    if(search(key)) {
        int height = 0;
        int highestNode = 0;
        Node *currNode = node;
        while(currNode != 0) {
            //when key is found
            if(currNode->getData() == key) {
                //call helper function
                highestNode = highest(0, currNode);
                //ends while loop
                currNode = 0;
            }
            //otherwise, continues to search for node
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

int AVLTree::highest(int h, Node *node)  {
    int highestNode = h;
    int left_height = highestNode;
    int right_height = highestNode;
    //next two for loops ensure that all subtrees under current node are checked
    if(node->getLeftChild()) {
        left_height = highest(highestNode, node->getLeftChild()) + 1;
    }
    if(node->getRightChild()) {
        right_height = highest(highestNode, node->getRightChild()) + 1;
    }
    //if left tree is higher, that value is used 
    if(left_height > right_height) {
        highestNode += left_height;
    }
    else {
        highestNode += right_height;
    }
    //will eventually return highest subtree from a given starting node
    return highestNode;
}

bool AVLTree::search(const string &key)  {
    Node *currNode = root;
    //checks every left and right subtree recursively until correct key is found
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

int AVLTree::balanceFactor(Node *rootNode) {
    Node *leftNode = rootNode->getLeftChild();
    Node *rightNode = rootNode->getRightChild();
    int leftHeight = 0;
    int rightHeight = 0;
    if(leftNode) {
        leftHeight = height(leftNode->getData(), leftNode) + 1;
    }
    if(rightNode) {
        rightHeight = height(rightNode->getData(), rightNode) + 1;
    }
    return leftHeight-rightHeight;

}

void AVLTree::printBalanceFactors() {
    printBalanceFactors(root);
    cout << endl;
}

void AVLTree::printBalanceFactors(Node *node) {
    if(node->getLeftChild()) {
        printBalanceFactors(node->getLeftChild());
    }
    cout <<  node->getData() << "(" << balanceFactor(node) << "), ";
    if(node->getRightChild()) {
        printBalanceFactors(node->getRightChild());
    }
}

void AVLTree::rotate(Node *node)  {
    //if true, there is a left subtree inbalance
    if(balanceFactor(node) == 2 ) {
        //checks for left-right case
        if(balanceFactor(node->getLeftChild()) == -1) {
            rotateLeft(node->getLeftChild());
        }
        //both left-left and left-right have a right rotation at the end
        rotateRight(node);
    }
    //right subtree inbalance
    else if(balanceFactor(node) == -2) {
        //checks for right-left case
        if(balanceFactor(node->getRightChild()) == 1) {
            rotateRight(node->getRightChild());
        }
        rotateLeft(node);
    }
}

void AVLTree::rotateLeft(Node *node)  {
    //D is the imbalanced node
    Node *nodeD = node;
    //D's parent
    Node *parent = node->getParent();
    //D's right child
    Node *nodeB = node->getRightChild();
    //If B has a subchild, it will also be balanced
    Node *nodeC = nodeB->getLeftChild();
    //for cases where leftmost subtree has a right child
    if(nodeC) {
        nodeC->setParent(nodeD);
    }
    //D and its child now have the same parent - B is going to take D's place
    nodeB->setParent(nodeD->getParent());
    //checks which side child the topmost node is - left, right, or root node
    if(nodeD != root) {
        if(parent->getLeftChild() == nodeD) {
        parent->setLeftChild(nodeB);
    }
    else if(parent->getRightChild() == nodeD) {
        parent->setRightChild(nodeB);
    }
    }
    else {
        root = nodeB;
    }
    //NodeD is set to NodeB's child
    nodeD->setParent(nodeB);
    nodeB->setLeftChild(nodeD);
    //If nodeC exists, it will be D's right child - otherwise set to null, which works out
    nodeD->setRightChild(nodeC);
}

void AVLTree::rotateRight(Node *node)  {
    Node *nodeD = node;
    Node *parent = node->getParent();
    Node *nodeB = node->getLeftChild();
    Node *nodeC = nodeB->getRightChild();
    if(nodeC) {
        nodeC->setParent(nodeD);
    }
    nodeB->setParent(nodeD->getParent());
    if(nodeD != root) {
        if(parent->getLeftChild() == nodeD) {
        parent->setLeftChild(nodeB);
    }
        else if(parent->getRightChild() == nodeD) {
        parent->setRightChild(nodeB);
    }
    }
    else {
        root = nodeB;
    }
    nodeD->setParent(nodeB);
    nodeB->setRightChild(nodeD);
    nodeD->setLeftChild(nodeC);

}

void AVLTree::balanceTree(Node *node)  {
//recursively balances tree starting at leaves, ending at root
if(node->getLeftChild()) {
    rotate(node->getLeftChild());
}
if(node->getRightChild()) {
    rotate(node->getRightChild());
}
rotate(node);
}
#ifndef __AVLTREE_H__
#define __AVLTREE_H__
#include"Node.h"

class AVLTree {
    private:
        Node *root;
    public:
        /* Constructors */
        /* Default constructor */
        AVLTree();
        ~AVLTree();
      void deleteTree(Node *node);
        /* Mutators */
        /* Insert an item into the binary search tree. 
           Be sure to keep BST properties. 
           When an item is first inserted into the tree the count should be set to 1. 
           When adding a duplicate string (case sensitive), rather than adding another node, 
           the count variable should be incremented 
         */
        void insert(const string &newString);

        void insert(Node * currNode, Node *newNode);

        int balanceFactor(Node *);

        void printBalanceFactors();

        void visualizeTree(const string &);

        Node *findUnbalancedNode(Node *node);

        void rotate(Node *node) ;

        void rotateLeft(Node* node) ;

        void rotateRight(Node *node) ;

        void printBalanceFactors(Node *);

        void visualizeTree(ofstream &, Node *);

        int height(const string &key, Node *node) ;

        int highest(int h, Node *node) ;

        bool search(const string &key) ;

        void balanceTree(Node *) ;
};

#endif // __AVLTree_H__
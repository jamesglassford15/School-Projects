#include <iostream>
#include <limits>
#include "BSTree.h"
//#include"test_bst.h"
//#include"BSTree.cpp"
//#include"Node.cpp"

using namespace std;

void printOrders(BSTree *tree) {
  cout << "Preorder = ";
  tree->preOrder( );
  cout << "Inorder = ";
  tree->inOrder( );
  cout << "Postorder = ";
  tree->postOrder( );
}

int menu() {
  int choice = 0;
  cout << endl << "Enter menu choice: ";
  cout << endl;
  cout 
    << "1. Insert" << endl
    << "2. Remove" << endl
    << "3. Print" << endl
    << "4. Search" << endl
    << "5. Smallest" << endl
    << "6. Largest" << endl
    << "7. Height" << endl
    << "8. Quit" << endl;
  cin >> choice;
  
  // fix buffer just in case non-numeric choice entered
  // also gets rid of newline character
  cin.clear();
  cin.ignore(numeric_limits<streamsize>::max(), '\n');
  return choice;
}

int main( ) {
    // Pass all your tests before running the executable main
    //run_tests();  // TODO: Remove before submitting
    //return 0;

    BSTree tree;

    int choice = menu();

    string entry;
  
    while (choice != 8) {
    
        if (choice == 1) {
          string toInsert;
          cout << "Enter string to insert: \n";
          getline(cin, toInsert);
          tree.insert(toInsert);
        } else if (choice == 2) {
          string toRemove;
          cout << "Enter string to remove: ";
          getline(cin, toRemove);
          tree.remove(toRemove);
          cout << endl;
        } else if (choice == 3) {
          printOrders(&tree);
        } else if (choice == 4) {
          string toSearch;
          cout << "Enter string to search for: \n";
          getline(cin, toSearch);
          if(tree.search(toSearch)) {
            cout << "Found\n";
          }
          else {
            cout << "Not Found\n";
          }
        } else if (choice == 5) {
          cout << "Smallest: " << tree.smallest() << endl;
        } else if (choice == 6) {
          cout << "Largest: " << tree.largest() << endl;
        } else if (choice == 7) {
          string toFind;
          int height;
          cout << "Enter string: \n";
          getline(cin, toFind);
          height = tree.height(toFind);
          cout << "Height of subtree rooted at " << toFind << ": " << height << endl;
        }
        //fix buffer just in case non-numeric choice entered
        choice = menu();
    }
    return 0;
}

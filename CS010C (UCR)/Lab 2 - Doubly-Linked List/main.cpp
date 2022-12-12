#include"IntList.h"
#include<iostream>
using namespace std;
int main(){
    IntList *list = new IntList();
    int userSelection;
    int dataVal;
    bool isDone = false;
    while (!isDone) {
        cout << "Test cases:\n1. Add to front of list\n2. Remove from front of list" <<
        "\n3. Add to back of list\n4. Remove from back of list\n5. Check if list is empty" <<
        "\n6. Print list in reverse\n7. print list\n8. quit\nWhat would you like to do?";
        cin >> userSelection;
        switch(userSelection){
            case 1:
            cout << "Input data value to store in list:\n";
            cin >> dataVal;
            list->push_front(dataVal);
            break;
            case 2:
            list->pop_front();
            break;
            case 3:
            cout << "Input data value to store in list:\n";
            cin >> dataVal;
            list->push_back(dataVal);
            break;
            case 4:
            list->pop_back();
            break;
            case 5:
            if(list->empty()) {
                cout << "List is empty\n\n";
            }
            else {
                cout << "List is not empty\n\n";
            }
            break;
            case 6:
            list->printReverse();
            break;
            case 7:
            cout << *list;
            break;
            case 8:
            list->~IntList();
            isDone = true;
            break;
        };
    };
    return 0;

}
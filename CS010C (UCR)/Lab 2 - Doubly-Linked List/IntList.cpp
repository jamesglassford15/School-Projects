#include"IntList.h"

IntList::IntList() {
    dummyHead = new IntNode(0);
    dummyTail = new IntNode(0);
    dummyHead->next = dummyTail;
    dummyTail->prev = dummyHead;
};

IntList::~IntList() {
    if(!empty()) {
        IntNode *currNode = dummyHead->next;
        while (currNode != dummyTail) {
            delete currNode->prev;
            currNode = currNode->next;
        }
    }
    delete dummyTail;
};

void IntList::push_front(int value) {
    IntNode *newNode = new IntNode(value);
    newNode->next = dummyHead->next;
    newNode->next->prev = newNode;
    newNode->prev = dummyHead;
    dummyHead->next = newNode;
};

void IntList::pop_front() {
    if(!empty()){
        dummyHead->next = dummyHead->next->next;
        delete dummyHead->next->prev;
        dummyHead->next->prev = dummyHead;
    }
};

void IntList::push_back(int value) {
    IntNode *newNode = new IntNode(value);
    newNode->next = dummyTail;
    newNode->prev = dummyTail->prev;
    dummyTail->prev = newNode;
    newNode->prev->next = newNode;
};

void IntList::pop_back() {
    if(!empty()) {
        dummyTail->prev = dummyTail->prev->prev;
        delete dummyTail->prev->next;
        dummyTail->prev->next = dummyTail;
    }
    
};

bool IntList::empty() const{
    if(dummyHead->next == dummyTail){
        return true;
    }
    else{
        return false;
    }
};

ostream& operator<<(ostream &out, const IntList &rhs) {
    IntNode* currNode = rhs.dummyHead->next;
    while(currNode != rhs.dummyTail) {
        out << currNode->data;
        if(currNode->next != rhs.dummyTail) {
            out << " ";
        }
        currNode = currNode->next;
    }
    return out;
}
void IntList::printReverse() const {
    IntNode* currNode = dummyTail->prev;
    while(currNode != dummyHead) {
        cout << currNode->data;
        if(currNode->prev != dummyHead) {
            cout << " ";
        }
        currNode = currNode->prev;
    }
};
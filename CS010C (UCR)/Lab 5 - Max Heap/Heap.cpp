#include "Heap.h"
#include <iostream>
using namespace std;
Heap::Heap()
{
    this->numItems = 0;
}

void Heap::enqueue(PrintJob *newJob)
{
    if (numItems < MAX_HEAP_SIZE)
    {
        PrintJob *tempJob = newJob;
        int currIndex = numItems;
        bool foundPlace = false;
        this->arr[numItems] = tempJob;
        numItems++;
        while (!foundPlace)
        {

            if (this->arr[currIndex]->getPriority() > this->arr[(currIndex - 1) / 2]->getPriority())
            {
                this->arr[currIndex] = this->arr[(currIndex - 1) / 2];
                this->arr[(currIndex - 1) / 2] = tempJob;
                currIndex = (currIndex - 1) / 2;
            }
            else
            {
                foundPlace = true;
            }
        }
    }
}

void Heap::dequeue()
{
    if (!(numItems == 0))
    {
        this->arr[0] = this->arr[numItems - 1];
        numItems--;
        for (int i = 0; i < numItems; i++)
        {
            trickleDown(i);
        }
    }
}

PrintJob *Heap::highest()
{
    if (!(numItems == 0))
    {
        return this->arr[0];
    }
    else
    {
        return 0;
    }
}

void Heap::print()
{
    if (!(numItems == 0))
    {
        cout << "Priority: " << highest()->getPriority() << ", Job Number: " << highest()->getJobNumber() << ", Number of Pages: " << highest()->getPages() << endl;
    }
}

void Heap::trickleDown(int parent)
{
    int child1 = (parent * 2) + 1;
    int child2 = (parent * 2) + 2;
    if (child1 > numItems || child2 > numItems)
    {
        return;
    }
    int parentPriority = this->arr[parent]->getPriority();
    int c1Priority = this->arr[child1]->getPriority();
    int c2Priority = this->arr[child2]->getPriority();
    int leftOrRight = c1Priority - c2Priority;
    if (parentPriority <= c1Priority && leftOrRight >= 0)
    {
        swap(this->arr[parent], this->arr[child1]);
        trickleDown(child1);
    }
    else if (parentPriority <= c2Priority)
    {
        swap(this->arr[parent], this->arr[child2]);
        trickleDown(child2);
    }
    else if (parentPriority > (c1Priority && c2Priority))
    {
        return;
    }
    else
    {
        cout << "You done goofed son\n";
    }
}

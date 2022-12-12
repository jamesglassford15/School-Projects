#include "WordLadder.h"
#include <fstream>
#include <queue>
#include <stack>
using namespace std;

WordLadder::WordLadder(const string &file)
{
    string word;
    ifstream input;
    input.open(file);
    if (!input.is_open())
    {
        cout << "Error opening file.\n";
        return;
    }
    dict = list<string>();
    int counter = 0;
    //loads words from file into list; if word is not 5 letters, throw error and return
    while (!input.fail())
    {
        input >> word;
        if (word.length() != 5)
        {
            cout << "Error! word is not 5 letters, closing.\n";
            return;
        }
        counter++;
        dict.push_back(word);
    }
}

void WordLadder::outputLadder(const string &start, const string &end, const string &outputFile)
{
    string startingWord = start;
    string endWord = end;
    bool startValid = false;
    bool endValid = false;
    list<string>::iterator it = dict.begin();
    //checks to make sure start and end words are in the list
    while (it != dict.end())
    {
        if (*it == start)
        {
            startValid = true;
        }
        if (*it == end)
        {
            endValid = true;
        }
        it++;
    }

    if (!startValid)
    {
        cout << "Error! starting word is not in the list.\n";
        return;
    }

    if (!endValid)
    {
        cout << "Error! end word is not in the list.\n";
        return;
    }

    // create a stack containing just the first word in the ladder
    stack<string> firstStack;
    firstStack.push(startingWord);
    // enqueue this stack onto a queue of stacks
    queue<stack<string>> stackQueue;
    stackQueue.push(firstStack);
    if(startingWord == endWord) {
        printLadder(firstStack, outputFile);
        return;
    }
    // while this queue of stacks is not empty
    while (!stackQueue.empty())
    {
        // get the word on top of the front stack of this queue
        stack<string> currStack = stackQueue.front();
        string currWord = currStack.top();
        // for each word in the dictionary
        it = dict.begin();
        while (it != dict.end())
        {
            int differences = 0;
            string temp = *it;
            it++;
            //count the number of differences between what "it" is pointing to and every other word in the list
            for (int i = 0; i < 5; ++i)
            {
                if (temp.at(i) != currWord.at(i))
                {

                    differences++;
                }
            }
            // if the word is off by just one letter from the top word
            if (differences == 1)
            {
                //create a new stack that includes the new word, add it to queue
                stack<string> newStack = stack<string>(currStack);
                newStack.push(temp);
                stackQueue.push(newStack);
                //if end word is found, return
                if (temp == endWord)
                {
                    printLadder(newStack, outputFile);
                    return;
                }
                //if not, remove word from list
                else
                {
                    dict.remove(temp);
                }
            }
        }
        //remove old iteration of word stack
        stackQueue.pop();
    }
    //we only get here if the word ladder fails
    stack<string> failed;
    failed.push("No Word Ladder Found.");
    printLadder(failed, outputFile);
}

void WordLadder::printLadder(stack<string> stack, string outputFile)
{
    ofstream output;
    output.open(outputFile);
    if (!output.is_open())
    {
        cout << "Error opening output file.\n";
        return;
    }
    std::stack<string> reverseOutput;
    int size = stack.size();
    for (int i = 0; i < size; i++)
    {
        reverseOutput.push(stack.top());
        stack.pop();
    }
    for (int i = 0; i < size; i++)
    {
        output << reverseOutput.top() << endl;
        reverseOutput.pop();
    }
    output.close();
}
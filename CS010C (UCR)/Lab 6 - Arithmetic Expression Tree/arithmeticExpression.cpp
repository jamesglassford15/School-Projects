

#include "arithmeticExpression.h"
#include <stack>
#include <string>
#include <sstream>
#include <fstream>
using namespace std;

arithmeticExpression::arithmeticExpression(const string &newEx)
{
    infixExpression = newEx;
    root = NULL;
}

void arithmeticExpression::buildTree()
{
    string postFix = infix_to_postfix();
    char key = 'a';

    root = addNode(postFix, key);
}

TreeNode *arithmeticExpression::addNode(string &postFixStr, char &key)
{
    if (!postFixStr.empty())
    {
        TreeNode* node = new TreeNode(postFixStr.at(postFixStr.size() - 1), static_cast<char>(key));
        key++;
        postFixStr.pop_back();

        if (priority(node->data) > 0)
        {
            node->right = addNode(postFixStr, key);
            node->left = addNode(postFixStr, key);
        }

        return node;
    }

    return nullptr;
}

void arithmeticExpression::infix()
{
    infix(root);
}

void arithmeticExpression::infix(TreeNode *node)
{
    if (node != nullptr)
    {
        if (node->left != nullptr)
        {
            cout << '(';
        }

        infix(node->left);
        cout << node->data;
        infix(node->right);

        if (node->left != nullptr)
        {
            cout << ')';
        }
    }
}

void arithmeticExpression::prefix()
{
    prefix(root);
}

void arithmeticExpression::prefix(TreeNode *node)
{
    if (node != nullptr)
    {
        cout << node->data;
        prefix(node->left);
        prefix(node->right);
    }
}

void arithmeticExpression::postfix()
{
    postfix(root);
}

void arithmeticExpression::postfix(TreeNode *node)
{
    if (node != nullptr)
    {
        postfix(node->left);
        postfix(node->right);
        cout << node->data;
    }
}

int arithmeticExpression::priority(char op)
{
    int priority = 0;
    if(op == '(')
    {
        priority =  3;
    }
    else if(op == '*' || op == '/')
    {
        priority = 2;
    }
    else if(op == '+' || op == '-')
    {
        priority = 1;
    }
    return priority;
}

string arithmeticExpression::infix_to_postfix()
{
    stack<char> s;
    ostringstream oss;
    char c;
    for(unsigned i = 0; i< infixExpression.size();++i)
    {
        c = infixExpression.at(i);
        if(c == ' ')
        {
            continue;
        }
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
        { //c is an operator
            if( c == '(')
            {
                s.push(c);
            }
            else if(c == ')')
            {
                while(s.top() != '(')
                {
                    oss << s.top();
                    s.pop();
                }
                s.pop();
            }
            else
            {
                while(!s.empty() && priority(c) <= priority(s.top()))
                {
                    if(s.top() == '(')
                    {
                        break;
                    }
                    oss << s.top();
                    s.pop();
                }
                s.push(c);
            }
        }
        else
        { //c is an operand
            oss << c;
        }
    }
    while(!s.empty())
    {
        oss << s.top();
        s.pop();
    }
    return oss.str();
}

/*void arithmeticExpression::visualizeTree(const string &outputFilename){
    ofstream outFS(outputFilename.c_str());
    if(!outFS.is_open()){
        cout<<"Error opening "<< outputFilename<<endl;
        return;
    }
    outFS<<"digraph G {"<<endl;
    visualizeTree(outFS,root);
    outFS<<"}";
    outFS.close();
    string jpgFilename = outputFilename.substr(0,outputFilename.size()-4)+".jpg";
    string command = "dot -Tjpg " + outputFilename + " -o " + jpgFilename;
    system(command.c_str());
}*/
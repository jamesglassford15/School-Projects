#include<iostream>
using namespace std;
#include<vector>
#include<ctime>
#include<stdexcept>
template <typename T>

unsigned min_index(const vector<T> &vals, unsigned index) {
    unsigned counter;
    unsigned minValue;
    //iterates through vector
    for (counter = index; counter < vals.size(); counter++) {
        //if smaller value found, store that value
        if(vals.at(minValue) > vals.at(counter)) {
            minValue = counter;
        }
    };
    return minValue;
};

template<typename T>
void selection_sort(vector<T> &vals) {
    unsigned counter1;
    unsigned counter2;
    unsigned min;
    //iterates through vector
    for (counter1 = 0; counter1 < vals.size(); counter1++) {
        //by default, set to counter1 so if a smaller value isn't found it will swap with itself (no change)
        min = counter1;
        //iterates through a second time in order to compare each item in vector
        for (counter2 = counter1+1; counter2 < vals.size(); counter2++) {
            //if smaller value found, store position
            if (vals.at(min) > vals.at(counter2)) {
                min = counter2;
            }
        }
        //swap with stored position
        swap(vals.at(min), vals.at(counter1));
    }
}
template <typename T>
T getElement(vector<T> vals, int index) {
    return vals.at(index);
}

vector<char> createVector(){
    int vecSize = rand() % 26;
    char c = 'a';
    vector<char> vals;
    for(int i = 0; i < vecSize; i++)
    {
        vals.push_back(c);
        c++;
    }
    return vals;
}

int main(){
    vector<string> test4{ "hello", "cs10c", "polarize", "polarbear", "tiktok" };

    // should return 1234
    cout << getElement(test4, 4);


    //Part B
     srand(time(0));
     vector<char> vals = createVector();
     char curChar;
     int index;
     int numOfRuns = 10;
     selection_sort(vals);
     while(--numOfRuns >= 0){
         cout << "Enter a number: " << endl;
         cin >> index;
         try {
            curChar = getElement(vals,index);
            cout << "Element located at " << index << ": is " << curChar << endl;
         }
        catch (const out_of_range& excpt) {
            cout << "out of range exception occurred" << endl;
        }
    }
    return 0;
}
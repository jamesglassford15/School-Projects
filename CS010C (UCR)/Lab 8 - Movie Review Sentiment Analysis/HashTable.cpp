#include "HashTable.h"
#include "WordEntry.h"
using namespace std;
/* HashTable constructor
*  input s is the size of the array
*  set s to be size
*  initialize array of lists of WordEntry
*/
HashTable::HashTable (int s) {
    size = s;
	hashTable = new list<WordEntry>[size];
}


/* computeHash
*  return an integer based on the input string
*  used for index into the array in hash table
*  be sure to use the size of the array to 
*  ensure array index doesn't go out of bounds
*/
int HashTable::computeHash(const string &s) {
    int indexValue = 0;
    string word = s;
    for(int i = 0; i < int(word.size()); i++) {
        indexValue += word[i];
    }
    return indexValue;
}


/* put
*  input: string word and int score to be inserted
*  First, look to see if word already exists in hash table
*   if so, addNewAppearence with the score to the WordEntry
*   if not, create a new Entry and push it on the list at the
*   appropriate array index
*/
void HashTable::put(const string &s, int score) {
	 int hashValue = computeHash(s);
     int indexValue = hashValue % this->size;
    list<WordEntry>::iterator itr;
    for(itr = hashTable[indexValue].begin(); itr != hashTable[indexValue].end(); itr++) {
        if(itr->getWord() == s) {
            itr->addNewAppearance(score);
            return;
        }
    }
    WordEntry temp(s, score);
    hashTable[indexValue].push_back(temp);
    return;
}

/* getAverage
*  input: string word 
*  output: the result of a call to getAverage()
*          from the WordEntry
*  Must first find the WordEntry in the hash table
*  then return the average
*  If not found, return the value 2.0 (neutral result)
*/

double HashTable::getAverage(const string &s) {
    int hashValue = computeHash(s);
    int index = hashValue % this->size;
    list<WordEntry>::iterator itr;
    for(itr = hashTable[index].begin(); itr != hashTable[index].end(); itr++) {
        if(itr->getWord() == s) {
            double avg = itr->getAverage();
            return avg;
        }
    }
    return 2.0;
}

/* contains
* input: string word
* output: true if word is in the hash table
*         false if word is not in the hash table
*/
bool HashTable::contains(const string &s) {
    int hashValue = computeHash(s);
    int index = hashValue % this->size;
    list<WordEntry>::iterator itr;
    for(itr = hashTable[index].begin(); itr != hashTable[index].end(); itr++) {
        if(itr->getWord() == s) {
            return true;
        }
    }

    return false;
}
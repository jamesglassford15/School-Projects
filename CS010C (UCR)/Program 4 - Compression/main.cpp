#include<iostream>
#include<string>
#include<vector>
#include<fstream>
#include<queue>
using namespace std;

typedef struct token {
    string token;
    int frequency;
    int code;
} token;

class HashTable {
    private:
    int size;
    public:
    token *hashTable;

    HashTable() {
        size = 0;
    }

    HashTable(int s) {
        size = s;
        hashTable = new token[s];
    }

    //computes hash for token->code only
    int computeHash(string s) {
        int indexValue = 0;
        string word = s;
        for(int i = 0; i > int(word.size()); i++) {
            indexValue+= word[i];
        }
        return indexValue % size;
    }

    //insert for code->token
    void insert(token tok, int code) {
        int hashValue = code;
        //linear probing, if needed
        while(hashTable[hashValue].code != 0) {
            hashValue++;
        }
        hashTable[hashValue] = tok;
    }

    //insert for token->code
    void insert(token tok, string s) {
        int hashValue = computeHash(s);
        //linear probing, if needed
        while(hashTable[hashValue].token != "") {
            hashValue++;
        }
        hashTable[hashValue] = tok;
    }

    //search for code->token
    string search(int code) {
        int hashValue = code;
        while(hashTable[hashValue].code != code) {
            hashValue++;
        }
        return hashTable[hashValue].token;
    }

    //search for token->code
    int search(string s) {
        int hashValue = computeHash(s);
        while(hashTable[hashValue].token != s) {
            hashValue++;
        }
        return hashTable[hashValue].code;
    }

};

vector<token> tokenList;

void createList(string fileName) {
    cout << "Creating initial list...\n";
    string word;
    ifstream input;
    input.open(fileName);
    //abandon function if input file isn't able to be opened
    if(!input.is_open()) {
        cout << "Error opening file. Maybe the file name is wrong?\n";
        return;
    }
    //load words from file to vector
    while(!input.fail()) {
        bool newToken = true;
        input >> word;
        for(int i = 0; i < tokenList.size(); i++) {
            //if word is found in vector, increment the freqency counter
            if(word == tokenList[i].token) {
                tokenList[i].frequency ++;
                newToken = false;
                i = tokenList.size(); //causes the for loop to finish - we're done with it
            }
        }
        //if newToken is still true, we know we need to add the word to the vector
        if(newToken) {
            token temp;
            temp.token = word;
            temp.frequency = 1;
            tokenList.push_back(temp);
        }
    }
    cout << "Done.\n\n";
}

//modified version of insertion sort given to us in lab 9
vector<token> sortList(vector<token> vector, int size) {
    int i = 0;
    int j = 0;
    token temp;

    for(i = 1; i < size; ++i) {
        j = i;
        
        while (j > 0 && vector[j].frequency > vector[j - 1].frequency) {
            temp = vector[j];
            vector[j] = vector[j-1];
            vector[j-1] = temp;
            --j;
        }
    }
    return vector;
}

//goes through vector sequentially and assigns codes to most frequent words
void assignCodes() {
    cout << "Assigning key codes...\n";
    for(int i = 0; i < tokenList.size(); i++) {
        tokenList[i].code = i+1;
    }
    cout << "Done.\n\n";
}

//function for testing purposes
void testSort() {
    cout << "Testing the sort by printing first 20 most frequents:\n";
    for(int i = 0; i <= 20; i++) {
        cout << i+1 <<". " << tokenList[i].token << ": " << tokenList[i].frequency << " with code " << tokenList[i].code << endl;
    }
    cout << "done\n";
};

//goes through file and replaces words with their respective codes
void compress(HashTable *key, string fileName, string outputFile) {
    cout << "Compressing file...\n";
    ofstream output;
    ifstream input;
    string inWord;
    int outInt;
    //checks if input opens properly
    input.open(fileName);
    if(!input.is_open()) {
        cout << "Error opening file for compression.\n";
        return;
    }
    //checks if output opens properly
    output.open(outputFile);
    if(!output.is_open()) {
        cout << "Error opening output file.\n";
        return;
    }
    while(!input.fail()) {
        input >> inWord;
        //looks for input word in encoder hash table, returns the stored code
        outInt = key->search(inWord);
        //if the length of the code is longer than the word, it isn't used
        if(inWord.size() > to_string(outInt).size()) {
            output << outInt << " ";
        }
        else {
            output << inWord << " ";
        }
    }
    cout << "Done.\n\n";
}

//creates key in separate file
void produceKey(HashTable *key, string fileName) {
    cout << "Producing key file...\n";
    ofstream output;
    ifstream input;
    output.open(fileName);
    if(!output.is_open()) {
        cout << "Error opening key file.\n";
        return;
    }
    for(int i = 0; i < tokenList.size(); i++) {
        output << tokenList[i].code << " "<< tokenList[i].token << endl;
    }
    cout << "Done.\n\n";
}

int main() {
    string fileName;
    string outputFile;
    string keyFile;
    cout << "Enter the name of the file to be compressed:\n";
    cin >> fileName;
    cout << "Enter the name of the output file:\n";
    cin >> outputFile;
    cout << "Enter the name of the folder for the decompression key:\n";
    cin >> keyFile;
    cout << endl;
    if(fileName != "") {
        createList(fileName);
        cout << "Sorting list...\n";
        tokenList = sortList(tokenList, tokenList.size());
        cout << "Done.\n\n";
        assignCodes();
        //testSort();
        HashTable *encoder = new HashTable(tokenList.size()*2);
        HashTable *decoder = new HashTable(tokenList.size()*2);
        cout << "Creating encoder and decoder tables...\n";
        for(int i = 0; i < tokenList.size(); i++) {
            encoder->insert(tokenList[i], tokenList[i].token);
            decoder->insert(tokenList[i], tokenList[i].code);
        }
        cout << "Done.\n\n";
    compress(encoder, fileName, outputFile);
    produceKey(decoder, keyFile);
    cout << "Done, exiting.\n";
    }
    return 0;
}


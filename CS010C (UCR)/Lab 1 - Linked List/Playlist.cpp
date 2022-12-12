#include "Playlist.h"
PlaylistNode *head = NULL;
// generally, used stack overflow to fix a lot of the pointers in this file. I was originally
// getting many errors claiming "Taking address of temporary". Still pretty confused as to why
// the original implementation wasn't working. Thinking it might've been because I was declaring 
// new PlaylistNode objects inside functions and not deleting them? Or maybe just the act of declaring
// inside the function caused compilation errors? Will ask in lab / lecture.

PlaylistNode::PlaylistNode() {
    this->uniqueID = "none";
    this->songName = "none";
    this->artistName = "none";
    this->songLength = 0;
    this->nextNodePtr = 0;
};
    
PlaylistNode::PlaylistNode(string uniqueID, string songName, string artistName, int songLength) {
    this->uniqueID = uniqueID;
    this->songName = songName; 
    this->artistName = artistName;
    this->songLength = songLength;
    this->nextNodePtr = 0;
    }

//checked the example Linked List in zybooks for how to implement this function
void PlaylistNode::InsertAfter(PlaylistNode* newNode) {
    PlaylistNode * temp = nextNodePtr;
    this->nextNodePtr = newNode;
    newNode->nextNodePtr = temp;
    };

void PlaylistNode::SetNext(PlaylistNode *nextNode) {
    this->nextNodePtr = nextNode;
    }

string PlaylistNode:: GetID() { return uniqueID; }

string PlaylistNode::GetSongName() {return songName; }

string PlaylistNode::GetArtistName() { return artistName; }

int PlaylistNode::GetSongLength() { return songLength; }

PlaylistNode* PlaylistNode::GetNext() { return nextNodePtr; }

void PlaylistNode::PrintPlaylistNode() {
    cout << "Unique ID: " << GetID() << "\nSong Name: " << GetSongName() 
    << "\nArtist Name: " << GetArtistName() << "\nSong Length (in seconds): " 
    << GetSongLength() << endl;
    };      

void PrintMenu(string title) {
    cout << title << " PLAYLIST MENU\na - Add song\nd - Remove song\nc - Change position of song\ns - Output songs by specific artist\nt -" 
    " Output total time of playlist (in seconds)\no - Output full playlist\nq - Quit\n\nChoose an option:\n";
    };

void OutputPlaylist (string title) {
    PlaylistNode *currNode = head;
    if (currNode == NULL) {
        cout << "Playlist is empty\n\n";
    }
    else {
        int counter = 1;
        while(currNode != NULL) {
            cout << counter << ".\n";
            currNode->PrintPlaylistNode();
            cout << endl;
            currNode = currNode->GetNext();
            counter++;
        }
    }
}

void AddSong () {
    string uniqueID;
    string songName;
    string artistName;
    int songLength;
    cout << "Enter song's unique ID:" << endl;
    getline(cin, uniqueID);
    getline(cin, uniqueID);
    cout << "Enter song's name:" << endl;
    getline(cin, songName);
    cout << "Enter artist's name:" << endl;
    getline(cin, artistName);
    cout << "Enter song's length (in seconds):\n";
    cin >> songLength;
    PlaylistNode *newNode = new PlaylistNode(uniqueID, songName, artistName, songLength);
    if(head == NULL) {
        head = newNode;
    }
    else {
        PlaylistNode *currNode = head;
        while(currNode->GetNext() != NULL) {
            currNode = currNode->GetNext();
        }
        currNode->SetNext(newNode);
    }
    cout << endl;
};

void RemoveSong () {
    string removeID;
    string removeName;
    PlaylistNode *currNode = head;
    PlaylistNode *prevNode;
    PlaylistNode *tempNode;
    cout << "Enter song's unique ID:" << endl;
    cin >> removeID;
    if (head == NULL) {
        return;
    }
    if(head->GetID() == removeID) {
        head = head->GetNext();
    }
    else {
        bool found = false;
        while(!found) {
            if (currNode->GetID() == removeID) {
                tempNode = currNode;
                removeName = currNode->GetSongName();
                if (currNode->GetNext() != NULL) {
                    prevNode->SetNext(currNode->GetNext());
                }
                else {
                    prevNode->SetNext(NULL);
                }
                cout << R"(")" << removeName << R"(" removed.)" << endl << endl;
                found = true;
            }
            else {
                prevNode = currNode;
                currNode = currNode->GetNext();
            }
        }   
    }
};

void ChangePosition() {
    int currentPos, newPos;
    int i;
    PlaylistNode *currNode = head;
    PlaylistNode *prevNode;
    PlaylistNode *storedNode;
    string name;

    cout << "Enter song's current position:\n";
    cin >> currentPos;
    if(currentPos <= 0) {
        cout << "error! choose a position number higher than zero. \n";
        ChangePosition();
    };
    cout << "Enter new position for song:\n";
    if (currentPos <= 0) {
        cout << "error! choose a position number higher than zero. \n";
        ChangePosition();
    };
    cin >> newPos;
    if (currentPos == newPos) {
        cout << "error! choose a different location to move song to.\n";
        ChangePosition();
    };
    if(currentPos == 1) {
        storedNode = currNode;
        head = currNode->GetNext();
    }
    else {
        for (i = 0; i < currentPos - 1; ++i) {
            if (currNode->GetNext() != NULL) {
                prevNode = currNode;
                currNode = currNode->GetNext();

            };
        }
        storedNode = currNode;
        prevNode->SetNext(currNode->GetNext());
    }
    name = storedNode->GetSongName();
    if (newPos == 1) {
        prevNode->SetNext(currNode->GetNext());
        storedNode->SetNext(head);
        head = storedNode;
    }
    else if (currentPos < newPos) {
        currNode = head;
        for (i = 1; i < newPos; ++i) {
            prevNode = currNode;
            currNode = currNode->GetNext();
        }
        prevNode->SetNext(storedNode);
        if(currNode != NULL) {
            storedNode->SetNext(currNode);
        }
        else {
            storedNode->SetNext(0);
        }

    }
    else if (currentPos > newPos) {
        currNode = head;
        for (i = 1; i < newPos; ++i) {
            prevNode = currNode;
            currNode = currNode->GetNext();
        }
        prevNode->SetNext(storedNode);
        storedNode->SetNext(currNode);
    }
    cout << R"(")" << name << R"(" moved to position )" << newPos << endl << endl;
};

void SpecificArtist() {
    int counter = 1;
    bool done = false;
    PlaylistNode *currNode = head;
    string artistName;
    cout << "Enter artist's name:\n" << endl;
    getline(cin, artistName);
    getline(cin, artistName);
    while (!done) {
            if (currNode->GetArtistName() == artistName) {
                cout << counter << ".\n";
                currNode->PrintPlaylistNode();
                cout << endl;
            }
        if(currNode->GetNext() == NULL) {
            done = true;
        }
        else {
            currNode = currNode->GetNext();
            counter++;
        }
    }
};

void TotalTime() {
    int time = 0;
    PlaylistNode *currNode = head;
    bool done = false;
    while (!done) {
        time = time + currNode->GetSongLength();
        if(currNode->GetNext() != NULL) {
            currNode = currNode->GetNext();
        }
        else {
            done = true;
        }       
    };
    cout << "Total time: " << time << " seconds\n\n";
};

void Quit() {
    PlaylistNode *currNode = head;
    bool done = false;
    while (!done) {
        if(currNode->GetNext() == NULL) {
            done = true;
            delete currNode;
        }
        else {
            PlaylistNode* prevNode = currNode;
            currNode = currNode->GetNext();
            delete prevNode;
        }
    }
}
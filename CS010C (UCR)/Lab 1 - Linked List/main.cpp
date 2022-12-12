//all previous submissions has #include"Playlist.cpp", hoping that solves the issue
//and apologies for the submission spam, I learned Java at my community college so C++ is new to me
#include"Playlist.cpp"
#include<iostream>
using namespace std;
int main() {
    string title;
    string userInput;
    bool done = false;
    cout << "Enter playlist's title:\n" << endl;
    getline(cin, title);
    while(done == false) {
        userInput = "";
        PrintMenu(title);
        cin >> userInput;
        if (userInput == "a") {
            cout << "ADD SONG\n";
            AddSong();
        }
        else if (userInput == "d") {
            cout << "REMOVE SONG\n";
            RemoveSong();
        }
        else if (userInput == "c") {
            cout << "CHANGE POSITION OF SONG\n";
            ChangePosition();
        }
        else if(userInput == "s") {
            cout << "OUTPUT SONGS BY SPECIFIC ARTIST\n";
            SpecificArtist();
        }
        else if (userInput == "t") {
            cout << "OUTPUT TOTAL TIME OF PLAYLIST (IN SECONDS)\n";
            TotalTime();
        }
        else if (userInput == "o") {\
            cout << title << " - OUTPUT FULL PLAYLIST\n";
            OutputPlaylist(title);
        }
        else if (userInput == "q") {
            Quit();
            done = true;
        }
    };
    cin.get();
    return 0;
};
#include<iostream>
#include<string>
using namespace std;


class PlaylistNode {
    private:
    string uniqueID;
    string songName;
    string artistName;
    int songLength;
    PlaylistNode* nextNodePtr;
    public:
        PlaylistNode();
        PlaylistNode(string uniqueID, string songName, string artistName, int songLength);
        void InsertAfter(PlaylistNode *newNode);
        void SetNext(PlaylistNode* nextNode);
        string GetID();
        string GetSongName();
        string GetArtistName();
        int GetSongLength();
        PlaylistNode* GetNext();
        void PrintPlaylistNode();
};
void PrintMenu(string title);
void OutputPlaylist(string title);
void AddSong();
void RemoveSong();
void ChangePosition();
void SpecificArtist();
void TotalTime();
void Quit();
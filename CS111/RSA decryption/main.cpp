#include <iostream>
#include <vector>
#include <algorithm> //gcd
#include<cmath>
using namespace std;

long long modSolver2(long long num, long long exponent, long long mod) {
    int even = exponent%2;
    if(exponent ==2) {
        return (num*num)%mod;
    }
    else if (exponent ==1) {
        return num%mod;
    }
    else if(even == 1) {
        long long temp = (num*num)%mod;
        return (modSolver2(num, exponent-1,mod)*(num%mod))%mod;
    }
    else if(even == 0) {
        int newExponent = exponent/2;
        return (modSolver2(num, newExponent, mod)*modSolver2(num,newExponent,mod))%mod;
    }
    else {return 1;}
}

bool isPrime(int num) {
    if (num <= 1) {
        return false;
    }
    for(int i = 2; i < num; ++i) {
        if(num%i == 0) {
            return false;
        }
    }
    return true;

}

bool isCoPrime(int numA, int numB) {
    if ( __gcd(numA, numB) == 1) {
        return true;
    }
    else {return false;}
}

int findPrime(int n) {
    for (int i = 2; i < n; i++) {
        if (isPrime(i)) {
            if (isPrime(n/i)&& n%i == 0) {
                return i;
            }
        }
    }
    return 0;
}

int findD(int e, int phi) {
    int phiCounter = phi + 1;
    bool isFound = false;
    while(!isFound) {
        if((phiCounter%e)==0) {
            isFound = true;
            return phiCounter/e;
        }
        phiCounter += phi;
    }
    return 0;
};

string assignLetter(int number)
{
    switch (number)
    {
    case 5:
        return "A";
    case 6:
        return "B";
    case 7:
        return "C";
    case 8:
        return "D";
    case 9:
        return "E";
    case 10:
        return "F";
    case 11:
        return "G";
    case 12:
        return "H";
    case 13:
        return "I";
    case 14:
        return "J";
    case 15:
        return "K";
    case 16:
        return "L";
    case 17:
        return "M";
    case 18:
        return "N";
    case 19:
        return "O";
    case 20:
        return "P";
    case 21:
        return "Q";
    case 22:
        return "R";
    case 23:
        return "S";
    case 24:
        return "T";
    case 25:
        return "U";
    case 26:
        return "V";
    case 27:
        return "W";
    case 28:
        return "X";
    case 29:
        return "Y";
    case 30:
        return "Z";
    case 31:
        return " ";
    case 32:
        return R"(")";
    case 33:
        return ",";
    case 34:
        return ".";
    case 35:
        return "'";
    };
    return "";
}

int main()
{
    int e, n;
    cin >> e >> n;
    int m;
    cin >> m;
    vector<int> secretMessage(m);
    for (int i = 0; i < m; i++)
    {
        cin >> secretMessage[i];
    }
    int p, q, phi, d;

    p = findPrime(n);
    q = n/p;
    phi = (p-1)*(q-1);
    
    if(!(isPrime(p) && isPrime(q)) || p == q || !isCoPrime(e,phi)) {
        cout << "Public key is not valid!";
        return 0;
    }
    d = findD(e, phi);
    vector<int> answerInts(m);
    for (int i = 0; i <= m; ++i)
    {
        answerInts[i] = modSolver2(secretMessage[i], d, n);
    }

    cout << p << " " << q << " " << phi << " " << d << endl;
    for (int i = 0; i < m; i++)
    {
        cout << answerInts[i] << " ";
    }
    cout << endl;

    for (int i = 0; i < m; i++)
    {
        cout << assignLetter(answerInts[i]);
    }
    answerInts.clear();
    secretMessage.clear();
    return 0;
}

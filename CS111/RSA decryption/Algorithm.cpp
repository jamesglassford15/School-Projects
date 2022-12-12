#include<iostream>
using namespace std;

class Algorithm {
    public:
    unsigned long long number, divisor;
    Algorithm() {
    }
    unsigned long long power(unsigned long long number, unsigned long long exponent) {
        unsigned long long total = number;
        for(int i = 1; i < exponent; ++i) {
            cout << total << endl;
            total = total*number;
        }
        return total;
    }

    unsigned long long Euclid (unsigned long long num, unsigned long long div) {
        if (num == div) {
            return num;
        }
        else if (num > div) {
            return Euclid(num%div, div);
        }
        else {
            return Euclid(num, div%num);
        }
    }

    unsigned long long modSolver(unsigned long long num, unsigned long long exponent, unsigned long long mod, unsigned long long originalNum) {
        if(exponent == 2) {
            return (num*originalNum)%mod;
        }
        else {
            unsigned long long temp = (num*originalNum)%mod;
            temp = modSolver(temp, exponent-1, mod, originalNum);
            return temp;
        }
    }

    string assignLetter(int number) {
        switch(number) {
            case 4:
                return "A";
            case 5:
                return "B";
            case 6:
                return "C";
            case 7:
                return "D";
            case 8:
                return "E";
            case 9:
                return "F";
            case 10:
                return "G";
            case 11:
                return "H";
            case 12:
                return "I";
            case 13:
                return "J";
            case 14:
                return "K";
            case 15:
                return "L";
            case 16:
                return "M";
            case 17:
                return "N";
            case 18:
                return "O";
            case 19:
                return "P";
            case 20:
                return "Q";
            case 21:
                return "R";
            case 22:
                return "S";
            case 23:
                return "T";
            case 24:
                return "U";
            case 25:
                return "V";
            case 26:
                return "W";
            case 27:
                return "X";
            case 28:
                return "Y";
            case 29:
                return "Z";
            case 30:
                return " ";
            case 31:
                return R"(")";
            case 32:
                return ".";
            case 33:
                return ",";
            case 34:
                return "'";
        };
    }
};
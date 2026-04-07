#include <stdio.h>
#include <string>
#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <unordered_map>
#include <queue>
#include <istream>
#include <stack>
#include<set>
#include <deque>

using namespace std;

int N;
int DP[1000001];



int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
   
    cin >> N;

    DP[0] = 0;
    DP[2] = 1;
    DP[3] = 1;
    for (int i = 3; i <= N; i++) {
        if (i % 3 != 0 && i % 2 != 0) {
            DP[i] = DP[i - 1] + 1;
        }
        else if (i % 3 == 0 && i % 2 != 0) {
            DP[i] = min(DP[i / 3], DP[i - 1]) + 1;
        }
        else if (i % 2 == 0 && i % 3 != 0) {
            DP[i] = min(DP[i / 2], DP[i - 1]) + 1;
        }
        else if (i % 3 == 0 && i % 2 == 0) {
            DP[i] =min(DP[i/2], min(DP[i / 3], DP[i - 1])) + 1;
        }
    }

    cout << DP[N]<<"\n";
    
}

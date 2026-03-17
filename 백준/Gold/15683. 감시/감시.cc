#include <iostream>
#include <queue>
#include <algorithm>
#include <cstring>
#define INF 987654321
using namespace std;

int N, M, Map[8][8], CamCnt;

struct Cam{
    int row, col, type;
};

//시계방향
int Dr[4] = {-1, 0, 1, 0};
int Dc[4] = {0, 1, 0, -1};

int CamView[5][5] ={
    {0, 1, 0, 0, 4},
    {0, 1, 0, 1, 2},
    {1, 1, 0, 0, 4},
    {1, 1, 0, 1, 4},
    {1, 1, 1, 1, 1},
};

Cam CCTV[8];

void Watch(int row, int col, int dir){
    int cr = row;
    int cc = col;
    
    while(1){
        int nr = cr + Dr[dir];
        int nc = cc + Dc[dir];
        
        if(nr<0 || nr>N-1 || nc<0 || nc>M-1) break;
        if(Map[nr][nc] == 6) break;
        
        Map[nr][nc] = 7;
        
        cr = nr;
        cc = nc;
    }
}

int solve(int cnt){
    if(cnt == CamCnt){
        int sum = 0;
        //사각지대 세기
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(Map[i][j] == 0) sum++;
            }
        }
        return sum;
    }
    //cout<<cnt<<", "<<CamCnt<<endl;
    
    int ret = INF;
    int backup[8][8];
    
    for(int i=0; i<CamView[CCTV[cnt].type-1][4]; i++){
        memcpy(backup, Map, sizeof(Map));
        for(int j=0; j<4; j++){//캠 종류에 따른 바라보는 방향 체크
            if(CamView[CCTV[cnt].type-1][j] == 1){
                Watch(CCTV[cnt].row, CCTV[cnt].col, (i+j) % 4);
            }
        }
        ret = min(ret, solve(cnt+1));
        memcpy(Map, backup, sizeof(Map));
    }
    
    return ret;
}

int main(){
    CamCnt = 0;
    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> Map[i][j];
            if(Map[i][j] >= 1 && Map[i][j] <= 5){
                CCTV[CamCnt].row = i;
                CCTV[CamCnt].col = j;
                CCTV[CamCnt].type = Map[i][j];
                CamCnt++;
            }
        }
    }
    cout<< solve(0) << endl;
    
    return 0;
}
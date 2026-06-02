class Solution {
    
    public int getGCD(int a, int b) {
        while(b!=0) {
            int r = a%b;
            a=b;
            b=r;
        }
        return a;
    }
    
    public boolean check(int t, int[] mod) {
        return (t%mod[0]>=mod[1]) && (t%mod[0]<=mod[2]);
    }
    
    public int solution(int[][] signals) {
        
        int[][] mods = new int[signals.length][3];
        
        for (int i=0; i<signals.length; i++) {
            mods[i][0] = signals[i][0] + signals[i][1] + signals[i][2];
            mods[i][1] = signals[i][0] + 1;
            mods[i][2] = signals[i][0] + signals[i][1];
        }
        
        int gcd = signals[0][0] + signals[0][1] + signals[0][2]; // 최소공배수
        int multi = gcd; // 주기의 곱
        for (int i=0; i<signals.length-1; i++) {
            int sum1 = gcd;
            int sum2 = signals[i+1][0] + signals[i+1][1] + signals[i+1][2];
            
            gcd = getGCD(sum1, sum2);
            multi *= sum2;
        }
        
        int maxDist = multi/gcd;
        for (int i=0; i<=maxDist; i++) {
            boolean flag = true;
            for (int[] signal : mods) {
                if(!check(i, signal)) {
                    flag = false;
                    break;
                }
            }
            if(flag) return i;
        }
        
        return -1;
    }
}
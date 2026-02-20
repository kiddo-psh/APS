import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int M, A;
	static int sum;
	
	static final int[] dr = {0, -1, 0, 1, 0}; // X-상-우-하-좌
	static final int[] dc = {0, 0, 1, 0, -1}; 
	
	static List<Charger> aCan = new ArrayList<>();
	static List<Charger> bCan = new ArrayList<>();
	
	static class Charger implements Comparable<Charger> {
		int r, c, len, power;
		public Charger(int r, int c, int len, int power) {
			this.r = r;
			this.c = c;
			this.len = len;
			this.power = power;
		}
		
		public boolean canCharge (Point o) {
			return (Math.abs(o.r-this.r) + Math.abs(o.c-this.c)) <= len;
		}
		
		public int compareTo(Charger o) {
			return o.power - this.power;
		}
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void move(int dir) {
			r = r + dr[dir];
			c = c + dc[dir];
		}
	}
	
	static void charge() {
		if (aCan.size()==0 && bCan.size()==0) {}
		else if (aCan.size()>=1 && bCan.size()==0) sum += aCan.get(0).power;
		else if (aCan.size()==0 && bCan.size()>=1) sum += bCan.get(0).power;
		else if (aCan.get(0).equals(bCan.get(0))) {
			if (aCan.size()==1 && bCan.size()==1) {
				sum += aCan.get(0).power;
			} else if (aCan.size()==2 && bCan.size()==1) {
				sum += aCan.get(1).power + bCan.get(0).power;
			} else if (aCan.size()==1 && bCan.size()==2) {
				sum += aCan.get(0).power + bCan.get(1).power;
			} else {
				sum += aCan.get(0).power + Math.max(aCan.get(1).power, bCan.get(1).power);
			}
		} 
		else {
			sum += aCan.get(0).power + bCan.get(0).power;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) { 
			/*
			 * @param M	이동정보 개수
			 * @param A	충전기 개수
			 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			int[] moveA = new int[M];
			int[] moveB = new int[M];
			Charger[] BC = new Charger[A];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken()) - 1;
				int R = Integer.parseInt(st.nextToken()) - 1;
				int Len = Integer.parseInt(st.nextToken());
				int Power = Integer.parseInt(st.nextToken());
				
				BC[i] = new Charger(R, C, Len, Power);
			}
			Arrays.sort(BC);
			
			/*
			 * @param personA A의 좌표
			 * @param personB B의 좌표
			 */
			Point personA = new Point(0, 0);
			Point personB = new Point(9, 9);
			
			sum = 0;
			
			for (int i=0; i<=M; i++) {
				
				aCan.clear();
				bCan.clear();
				
				// 충전량 계산
				for (int j=0; j<A; j++) {
					
					if (BC[j].canCharge(personA)) {
						if (aCan.size()<2) {
							aCan.add(BC[j]);
						}
					}
					if (BC[j].canCharge(personB)) {
						if (bCan.size()<2) {
							bCan.add(BC[j]);
						}
					}
				}
				charge();
				if(i==M) break;
				
				personA.move(moveA[i]);
				personB.move(moveB[i]);
			} 
			
			output.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(output);
	}
}

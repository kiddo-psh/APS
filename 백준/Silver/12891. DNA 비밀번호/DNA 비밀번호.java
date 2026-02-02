import java.io.*;
import java.util.Arrays;
/*
 * 
 * 
 * 
 * */
import java.util.StringTokenizer;

public class Main {
	
	static int[] alphaCount = new int['T'+1];
	
	static boolean isValid() {
		String s = "ACGT";
		for (int i=0; i<4; i++) {
			if (alphaCount[s.charAt(i)] < 0) return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String DNA = br.readLine();

		st = new StringTokenizer(br.readLine());
		String s = "ACGT";
		Arrays.fill(alphaCount, 0);
		for (int i=0; i<4; i++) {
			alphaCount[s.charAt(i)] -= Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for (int i=0; i<P; i++) {
			alphaCount[DNA.charAt(i)]++;
		}
		if (isValid()) ans++;
		
		for (int i=0; i<S-P; i++) {
			alphaCount[DNA.charAt(i)]--;
			alphaCount[DNA.charAt(i+P)]++;
			
			if (isValid()) ans++;
		}
		System.out.println(ans);
	}
}

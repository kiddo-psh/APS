import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	static LinkedList list = new LinkedList();
	static Map<String, Integer> map = new HashMap<>();
	static Map<String ,Node> nodeMap = new HashMap<>();
	static final int MAX_MEMORY = 100_000;
	
	static class Node {
		int start, end;
		Node prev, next;
		
		public Node(int s, int e) {
			start = s;
			end = e;
		}
		
		public String toString() {
			return "["+start+ ",  " + end +"]  ";
		}
	}
	
	static class LinkedList{
		Node head, tail;
		
		public LinkedList() {
			head = new Node(-1,-1);
			tail = new Node(100_001,100_001);
			
			head.next = tail;
			tail.prev = head;
		}
	}
	
	static String getVar(String command) {
		int idx = command.indexOf('=');
		return command.substring(0,idx);
	}
	
	static int getSize(String command) {
		int l = command.indexOf('(')+1;
		int r = command.indexOf(')');
		
		return Integer.parseInt(command.substring(l,r));
	}
	
	static String getTarget(String command) {
		int l = command.indexOf('(')+1;
		int r = command.indexOf(')');
		
		return command.substring(l,r);
	}
	
	static void addNode(String var, int size) {
		int cur = 1;
		Node nextNode = list.head.next;
		boolean isAllocated = false;
		
		while (cur + size - 1 <= MAX_MEMORY) {
			if (nextNode.start - cur < size) {
				if (nextNode == list.tail) break;
				
				cur = nextNode.end + 1;
				nextNode = nextNode.next;
				continue;
			}
			
			Node n = new Node(cur, cur+size-1);
			n.prev = nextNode.prev;
			n.next = nextNode;
			nextNode.prev.next = n;
			nextNode.prev = n;
			
			map.put(var, cur);
			nodeMap.put(var, n);
			
			isAllocated = true;
			break;
		}
		if (!isAllocated) {
			map.put(var, 0);
		}
	}
	
	static void removeNode(String target) {
		if (map.get(target)==null || map.get(target)==0) return;
		
		map.put(target, 0);
		
		Node n = nodeMap.get(target);
		n.prev.next = n.next;
		n.next.prev = n.prev;
		nodeMap.remove(target);
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			String command = br.readLine();
			
			// 1. free
			if (command.startsWith("free(")) {
				String target = getTarget(command);
				if (map.get(target)==null || map.get(target)==0) continue;
				
				removeNode(target);
				map.put(target, 0);
			}
			// 2. print
			else if (command.startsWith("print(")) {
				String target = getTarget(command);
				
				if (map.get(target)==null || map.get(target)==0) {
					output.append(0).append("\n");
					continue;
				} else {
					output.append(map.get(target)).append("\n");
				}
			}
			// 3. malloc
			else {
				String var = getVar(command);
				int size = getSize(command);
				
				addNode(var, size);
			}
			
		}
		
		System.out.println(output);
		br.close();
	}
	
	static void dbg(Object...objects) {
		System.out.println(Arrays.deepToString(objects));
	}
}

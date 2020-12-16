import java.util.*;

public class Day05 {

	public static String input = "day05input.txt";

	public static void main(String[] args) {
		partA();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		int max = 0;
		
//		List<Integer> allIds = new ArrayList<>();
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			int row = getTarget(line.substring(0, 7), 128, 'B');
			int col = getTarget(line.substring(7), 8, 'R');
			
			int seatId = row * 8 + col;
//			allIds.add(seatId);
			if (seatId > max) max = seatId;
		}
		
//		System.out.println(max);
//		Collections.sort(allIds);
//		for (int a : allIds) {
//			System.out.println(a);
//		}
		
		s.close();
	}
	
	public static int getTarget(String pattern, int size, char latter) {
		int start = 0;
		for (char c : pattern.toCharArray()) {
			size /= 2;
			if (c == latter) {
				start += size;
			}
		}
		return start;
	}
	
	public static void partB() {
		Set<Integer> seats = new HashSet<>();
		for (int i = 0; i <= 963; i++) {
			seats.add(i);
		}
		
		Scanner s = AOC.getScanner(input);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			int row = getTarget(line.substring(0, 7), 128, 'B');
			int col = getTarget(line.substring(7), 8, 'R');
			
			int seatId = row * 8 + col;
			seats.remove(seatId);
		}
		
		for (int n : seats) {
			if (!seats.contains(n - 1) && !seats.contains(n + 1))
				System.out.println(n);
		}
		s.close();
	}
	
}

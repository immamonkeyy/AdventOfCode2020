import java.util.*;

public class Day05 {

	public static String input = "input/day05.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		int max = 0;
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			int row = getTarget(line.substring(0, 7), 128, 'B');
			int col = getTarget(line.substring(7), 8, 'R');
			
			int seatId = row * 8 + col;
			if (seatId > max) max = seatId;
		}
		
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
	
	public static void part2() {
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

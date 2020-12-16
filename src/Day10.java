import java.util.*;

public class Day10 {

	public static String input = "input/day10.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		List<Integer> adapters = new ArrayList<>();
		while (s.hasNextLine()) {
			adapters.add(s.nextInt());
		}
		Collections.sort(adapters);
		int[] counts = new int[] {0,0,0,0};
		int prev = 0;
		for (int n : adapters) {
			counts[n - prev]++;
			prev = n;
		}
		counts[3]++;
		System.out.println(counts[1] * counts[3]);
		
		s.close();
	}
	
	public static void part2() {
		Scanner s = AOC.getScanner(input);
		List<Integer> adapters = new ArrayList<>();
		adapters.add(0);
		while (s.hasNextLine()) {
			adapters.add(s.nextInt());
		}
		Collections.sort(adapters);
		long[] ways = new long[adapters.size()];
		ways[ways.length - 1] = 1;
		for (int i = ways.length - 2; i >= 0; i--) {
			long total = 0;
			for (int j = 1; j <= 3; j++) {
				if (i + j < ways.length) {
					if (adapters.get(i + j) - adapters.get(i) < 4) {
						total += ways[i + j];
					}
				}
			}
			ways[i] = total;
		}
		System.out.println(ways[0]);
		s.close();
	}
	
	// Lol
//	public static int countWays(List<Integer> list, int start) {
//		if (list.size() == 1) return 1;
//		int n = list.get(start);
//		int result = 0;
//		for (int i = 1; i < 4; i++) {
//			if (start + i < list.size() && list.get(start + i) < n + 4) {
//				result += countWays(list, start + i);
//			}
//		}
//		return result;
//	}
	
	
}
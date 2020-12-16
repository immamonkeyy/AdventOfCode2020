import java.util.*;

public class Day15 {

	public static String input = "day15input.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		partA();
		
		final long endTime = System.currentTimeMillis();
		double seconds = (endTime - startTime) / 1000.0;
		double minutes = seconds / 60;
		System.out.println("\nTotal execution time:");
		System.out.println(seconds + " seconds");
		System.out.println(minutes + " minutes");
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		
		// num --> most recent turn it was spoken
		Map<Integer, Integer> nums = new HashMap<>();
		int turn = 0;
		
		String[] starting = s.nextLine().split(",");
		int lastSpoken = Integer.parseInt(starting[0]);
		
		for (int i = 1; i < starting.length; i++) {
			turn++;
			nums.put(lastSpoken, turn);
			System.out.println("Turn " + turn + ": " + lastSpoken);
			lastSpoken = Integer.parseInt(starting[i]);
		}
		
		int nextSpoken;
		while (turn < 30000000) {
			if (!nums.containsKey(lastSpoken)) {
				nextSpoken = 0;
			} else {
				nextSpoken = turn - nums.get(lastSpoken) + 1;
			}
			turn++;
			if (turn % 10000000 == 0) {
				System.out.println("Turn " + turn + ": " + lastSpoken);
			}
			nums.put(lastSpoken, turn);
			lastSpoken = nextSpoken;
		}
		
		s.close();
	}
	
	public static void partB() {
		// modified partA
	}
}
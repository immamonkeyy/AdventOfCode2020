import java.util.*;

public class Day15 {
	
	public static String input = "input/day15.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part1();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
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
	
	//part 2 modified part 1
}
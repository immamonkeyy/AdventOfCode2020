import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day01 {
	
	public static String input = "day01input.txt";
	public static int target = 2020;
	
	public static void main(String[] args) {
		partB();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		Set<Integer> nums = new HashSet<>();
		while (s.hasNextInt()) {
			int n = s.nextInt();
			if (nums.contains(target - n)) {
				System.out.println(n * (target - n));
				break;
			}
			nums.add(n);
		}
		s.close();
	}
	
	public static void partB() {
		Scanner s = AOC.getScanner(input);
		Set<Integer> nums = new HashSet<>();
		while (s.hasNextInt()) {
			nums.add(s.nextInt());
		}
		
		Map<Integer, int[]> sums = new HashMap<>();
		for (int a : nums) {
			for (int b : nums) {
				if (a != b && a + b < target) {
					sums.put(a + b, new int[] {a, b});
				}
			}
		}
		
		for (int n : nums) {
			if (sums.containsKey(target - n)) {
				int[] pair = sums.get(target - n);
				if (pair[0] != n && pair[1] != n) {
					System.out.println(pair[0] * pair [1] * n);
					break;
				}
			}
		}

		s.close();
	}

}

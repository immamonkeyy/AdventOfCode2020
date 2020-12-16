import java.util.*;

public class Day09 {

	public static String input = "day09input.txt";

	public static void main(String[] args) {
		partB();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		List<Integer> nums = new ArrayList<>(); 
		Set<Integer> prevs = new HashSet<>();
		
		while (s.hasNextLine()) {
			int n = s.nextInt();
			if (nums.size() >= 25) {
				int start = nums.size() - 25;
				prevs.remove(nums.get(start));
				boolean found = false;
				for (int i = start; i < start + 25; i++) {
					if (prevs.contains(n - nums.get(i)) && 2 * nums.get(i) != n) {
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println(n);
					break;
				}
			}
			nums.add(n);
			prevs.add(n);
		}
		
		s.close();
	}
	
//	public static void print(Collection<Integer> c) {
//		for (int a : c) {
//			System.out.println(a);
//		}
//		System.out.println();
//	}
	
	public static void partB() {
		long target = 88311122;
		
		Scanner s = AOC.getScanner(input);
		List<Long> nums = new ArrayList<>(); 
		
		while (s.hasNextLine()) {
			long n = s.nextLong();
			nums.add(n);
		}
		
		s.close();
		
		for (int i = 0; i < nums.size(); i++) {
			long sum = 0;
			long min = nums.get(i);
			long max = nums.get(i);
			for (int j = i; j < nums.size(); j++) {
				if (nums.get(j) < min) min = nums.get(j);
				if (nums.get(j) > max) max = nums.get(j);
				sum += nums.get(j);
				if (sum == target) {
					System.out.println(min + max);
					System.exit(1);
				} else if (sum > target) break;
			}
		}
	}
}
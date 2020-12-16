import java.util.*;

public class Day13 {

	public static String input = "input/day13.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2v2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		
		int yourTime = Integer.parseInt(s.nextLine());
		String[] buses = s.nextLine().split(",");
		
		int minDiff = yourTime;
		int busNum = 0;
		for (String bStr : buses) {
			if (!bStr.equals("x")) {
				int busDiff = Integer.parseInt(bStr);
				int busTime = (yourTime / busDiff) * busDiff;
				if (busTime < yourTime) busTime += busDiff;
				if (busTime - yourTime < minDiff) {
					minDiff = busTime - yourTime;
					busNum = busDiff;
				}
			}
		}
		
		System.out.println(busNum * minDiff);
		
		s.close();
	}
	
	public static void part2() {
		Scanner s = AOC.getScanner(input);
		
		s.nextLine(); // throw away first line
		String[] buses = s.nextLine().split(",");
		int[] nums = new int[buses.length];
		
		int max = 0;
		int maxIdx = 0;
		for (int i = 0; i < buses.length; i++) {
			if (!buses[i].equals("x")) {
				nums[i] = Integer.parseInt(buses[i]);
				if (nums[i] > max) {
					max = nums[i];
					maxIdx = i;
				}
			} else nums[i] = -1;
		}
		
		System.out.println("max: " + max);
		System.out.println("maxIdx: " + maxIdx);
		
		long mult = 135317997294l;
		boolean success = false;
		long start = 0;
		while (!success) {
			start = max * mult - maxIdx;
			success = true;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != -1 && (start + i) % nums[i] != 0) {
					success = false;
					break;
				}
//				System.out.print((start + i) + " ");
			}
//			System.out.println();
			mult++;
//			System.out.println(mult);
		}
		
		System.out.println("Answer: " + start);
		s.close();
	}
	
	public static void part2v2() {
		Scanner s = AOC.getScanner(input);
		
		s.nextLine(); // throw away first line
		String[] buses = s.nextLine().split(",");
		int[] nums = new int[buses.length];
		int[] idxs = new int[buses.length];
		
		int max = 0;
		int maxIdx = 0;
		int count = 0;
		for (int i = 0; i < buses.length; i++) {
			if (!buses[i].equals("x")) {
				nums[count] = Integer.parseInt(buses[i]);
				idxs[count] = i;
				if (nums[count] > max) {
					max = nums[count];
					maxIdx = count;
				}
				count++;
			}
		}
		
		System.out.println("max: " + max);
		System.out.println("maxIdx: " + maxIdx);
		
		long mult = 535317997294l;
		boolean success = false;
		long start = 0;
		while (!success) {
			start = max * mult - idxs[maxIdx];
			success = true;
			for (int i = 0; i < count; i++) {
				if ((start + idxs[i]) % nums[i] != 0) {
					success = false;
					break;
				}
			}
			mult++;

			if (mult % 100000000 == 0) {
				System.out.println(mult);
			}
		}
		
		System.out.println("Answer: " + start);
		s.close();
	}
}
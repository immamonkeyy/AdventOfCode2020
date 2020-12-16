import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Day06 {

	public static String input = "input/day06.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		int sum = 0;
		Set<Character> qs = new HashSet<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.isEmpty()) {
				sum += qs.size();
				qs.clear();
			} else {
				for (char c : line.toCharArray()) {
					qs.add(c);
				}
			}
		}
		sum += qs.size();
		System.out.println(sum);
		s.close();
	}
	
	public static void part2() {
		Scanner s = AOC.getScanner(input);
		int sum = 0;
		Set<Character> qs = new HashSet<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.isEmpty()) {
				sum += qs.size();
				qs.clear();
				if (s.hasNextLine()) {
					for (char c : s.nextLine().toCharArray()) {
						qs.add(c);
					}
				}
			} else {
				Set<Character> newLineSet = new HashSet<>();
				for (char c : line.toCharArray()) {
					newLineSet.add(c);
				}
				Iterator<Character> it = qs.iterator();
				while (it.hasNext()) {
					if (!newLineSet.contains(it.next())) {
						it.remove();
					}
				}
			}
		}
		sum += qs.size();
		System.out.println(sum);
		s.close();
	}
	
	
}
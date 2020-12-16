import java.util.Scanner;

public class Day03 {
	
	public static String input = "input/day03.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		int x = 0;
		int trees = 0;
		while (s.hasNextLine()) {
			char[] line = s.nextLine().toCharArray();
			if (line[x % line.length] == '#') trees++;
			x = x + 3;
		}
		System.out.println(trees);
	}
	
	public static void part2() {
		long product = 1;
		product *= getTrees(1, 1);
		product *= getTrees(3, 1);
		product *= getTrees(5, 1);
		product *= getTrees(7, 1);
		product *= getTrees(1, 2);
		System.out.println(product);
	}
	
	public static int getTrees(int deltaX, int deltaY) {
		Scanner s = AOC.getScanner(input);
		int x = 0;
		int trees = 0;
		while (s.hasNextLine()) {
			char[] line = s.nextLine().toCharArray();
			if (line[x % line.length] == '#') trees++;
			x = x + deltaX;
			if (deltaY == 2 && s.hasNextLine()) {
				s.nextLine();
			}
		}
		return trees;
	}
}

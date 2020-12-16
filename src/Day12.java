import java.awt.Point;
import java.util.*;

public class Day12 {

	public static String input = "input/day12.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		Point position = new Point();
		int dir = 1;
		char[] dirs = new char[] {'N', 'E', 'S', 'W'};
		while (s.hasNextLine()) {
			String line = s.nextLine();
			char c = line.charAt(0);
			int n = Integer.parseInt(line.substring(1));
			if (c == 'R') {
				dir += (n / 90);
				dir = dir % dirs.length;
			}
			if (c == 'L') {
				dir -= (n / 90);
				dir = dir % dirs.length;
				if (dir < 0) dir += dirs.length;
			}
			if (c == 'F') {
				c = dirs[dir];
			}
			switch (c) {
				case 'N':
					position.y += n;
					break;
				case 'E':
					position.x += n;
					break;
				case 'S':
					position.y -= n;
					break;
				case 'W':
					position.x -= n;
					break;
			}
		}
		System.out.println(Math.abs(position.x) + Math.abs(position.y));
		
		s.close();
	}
	
	public static void part2() {
		Scanner s = AOC.getScanner(input);
		Point position = new Point();
		Point waypoint = new Point(10, 1);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			char c = line.charAt(0);
			int n = Integer.parseInt(line.substring(1));
			
			if (c == 'R') {
				for (int i = 0; i < n / 90; i++) {
					waypoint = new Point(waypoint.y, -waypoint.x);
				}
			}
			if (c == 'L') {
				for (int i = 0; i < n / 90; i++) {
					waypoint = new Point(-waypoint.y, waypoint.x);
				}
			}
			if (c == 'F') {
				position.x += waypoint.x * n;
				position.y += waypoint.y * n;
			}
			switch (c) {
				case 'N':
					waypoint.y += n;
					break;
				case 'E':
					waypoint.x += n;
					break;
				case 'S':
					waypoint.y -= n;
					break;
				case 'W':
					waypoint.x -= n;
					break;
			}
		}
		System.out.println(Math.abs(position.x) + Math.abs(position.y));
		
		s.close();
	}
}
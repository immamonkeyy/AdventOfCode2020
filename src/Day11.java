import java.util.*;

public class Day11 {

	public static String input = "input/day11.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		part2();
		
		AOC.endTime(startTime);
	}
	
	public static void part1() {
		Scanner s = AOC.getScanner(input);
		List<String> input = new ArrayList<>();
		while (s.hasNextLine()) {
			input.add(s.nextLine());
		}
		
		char[][] grid1 = new char[input.size()][];
		int idx = 0;
		for (String str : input) {
			grid1[idx++] = str.toCharArray();
		}
		
		char[][] grid2;
		
		boolean change = true;
		while (change) {			
			change = false;
			grid2 = new char[grid1.length][];
			for (int i = 0; i < grid1.length; i++) {
				grid2[i] = new char[grid1[i].length];
				for (int j = 0; j < grid1[i].length; j++) {
					grid2[i][j] = grid1[i][j];
					if (grid1[i][j] != '.') {
						int occ = countOcc(grid1, i, j);
						if (grid1[i][j] == 'L' && occ == 0) {
							grid2[i][j] = '#';
							change = true;
						} else if (grid1[i][j] == '#' && occ >= 4) {
							grid2[i][j] = 'L';
							change = true;
						}
					} 
				}
			}

			grid1 = grid2;
		}
		
		int count = 0;
		for (char[] arr : grid1) {
			for (char c : arr) {
				if (c == '#') count++;
			}
		}
		
		System.out.println(count);
		
		s.close();
	}
	
	public static void print(char[][] grid) {
		for (char[] arr : grid) {
			for (char c : arr) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int countOcc(char[][] grid, int i, int j) {
		int count = 0;
		int[] dirs = new int[] {-1, 0, 1};
		for (int di : dirs) {
			for (int dj : dirs) {
				if (di != 0 || dj != 0) {
					int newI = i + di;
					int newJ = j + dj;
					if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[i].length) {
						if (grid[newI][newJ] == '#') count++;
					}
				} 
			}
		}
		return count;
	}
	
	public static void part2() {
		Scanner s = AOC.getScanner(input);
		List<String> input = new ArrayList<>();
		while (s.hasNextLine()) {
			input.add(s.nextLine());
		}
		
		char[][] grid1 = new char[input.size()][];
		int idx = 0;
		for (String str : input) {
			grid1[idx++] = str.toCharArray();
		}
		
		char[][] grid2;
		
		boolean change = true;
		while (change) {			
			change = false;
			grid2 = new char[grid1.length][];
			for (int i = 0; i < grid1.length; i++) {
				grid2[i] = new char[grid1[i].length];
				for (int j = 0; j < grid1[i].length; j++) {
					grid2[i][j] = grid1[i][j];
					if (grid1[i][j] != '.') {
						int occ = countOcc2(grid1, i, j);
						if (grid1[i][j] == 'L' && occ == 0) {
							grid2[i][j] = '#';
							change = true;
						} else if (grid1[i][j] == '#' && occ >= 5) {
							grid2[i][j] = 'L';
							change = true;
						}
					} 
				}
			}

			grid1 = grid2;
		}
		
		int count = 0;
		for (char[] arr : grid1) {
			for (char c : arr) {
				if (c == '#') count++;
			}
		}
		
		System.out.println(count);
		
		s.close();
	}
	
	public static int countOcc2(char[][] grid, int i, int j) {
		int count = 0;
		int[] dirs = new int[] {-1, 0, 1};
		for (int di : dirs) {
			for (int dj : dirs) {
				if (di != 0 || dj != 0) {
					if (occInDir(grid, i, j, di, dj)) count++;
				} 
			}
		}
		return count;
	}
	
	public static boolean occInDir(char[][] grid, int i, int j, int di, int dj) {
		int newI = i + di;
		int newJ = j + dj;
		
		while (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[i].length) {
			if (grid[newI][newJ] == '#') return true;
			if (grid[newI][newJ] == 'L') return false;
			newI += di;
			newJ += dj;
		}
		
		return false;
	}
}
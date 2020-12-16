import java.util.Scanner;

public class Day02 {
	
	public static String input = "day02input.txt";
	
	public static void main(String[] args) {
		partB();
	}
	
	// 1-3 a: abcde
	// 1-3 b: cdefg
	// 2-9 c: ccccccccc
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		int valid = 0;
		while (s.hasNextLine()) {
			String[] bounds = s.next().split("-");
			int min = Integer.parseInt(bounds[0]);
			int max = Integer.parseInt(bounds[1]);
			char letter = s.next().charAt(0);
			String password = s.nextLine();
			int count = 0;
			for (char c : password.toCharArray()) {
				if (c == letter) count++;
				if (count > max) break;
			}
			if (count >= min && count <= max) valid++;
		}
		System.out.println(valid);
		s.close();
	}
	
	public static void partB() {
		System.out.println("Start");
		Scanner s = AOC.getScanner(input);
		int valid = 0;
		while (s.hasNextLine()) {
			String[] bounds = s.next().split("-");
			int pos1 = Integer.parseInt(bounds[0]);
			int pos2 = Integer.parseInt(bounds[1]);
			char letter = s.next().charAt(0);
			char[] password = s.next().toCharArray();
			int count = 0;
			System.out.print(password[pos1 - 1] + ", " + password[pos2 - 1] + ", ");
			if (password[pos1 - 1] == letter) count++;
			if (password[pos2 - 1] == letter) count++;
			if (count == 1) {
				System.out.println("Valid");
				valid++;
			} else System.out.println("Not valid");
		}
		System.out.println(valid);
		s.close();
	}

}

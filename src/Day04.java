import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Day04 {

	public static String input = "day04input.txt";

	public static void main(String[] args) {
		partB();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		int valid = 0;
		Map<String, String> map = new HashMap<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.isEmpty()) {
				if (map.containsKey("cid") && map.size() == 8 || (!map.containsKey("cid") && map.size() == 7)) {
					valid++;
				}
				map = new HashMap<>();
			} else {
				Scanner lineScan = new Scanner(line);
				while (lineScan.hasNext()) {
					String[] pieces = lineScan.next().split(":");
					map.put(pieces[0], pieces[1]);
				}
				lineScan.close();
			}
		}
		if (map.containsKey("cid") && map.size() == 8 || (!map.containsKey("cid") && map.size() == 7)) {
			valid++;
		}
		System.out.println(valid);
		s.close();
	}
	
	public static void partB() {
		Scanner s = AOC.getScanner(input);
		int valid = 0;
		Map<String, String> map = new HashMap<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.isEmpty()) {
				if (validate(map)) valid++;
				map = new HashMap<>();
			} else {
				Scanner lineScan = new Scanner(line);
				while (lineScan.hasNext()) {
					String[] pieces = lineScan.next().split(":");
					map.put(pieces[0], pieces[1]);
				}
				lineScan.close();
			}
		}
		if (validate(map)) valid++;
		System.out.println(valid);
		s.close();
	}
	
	public static boolean validate(Map<String, String> map) {
		try {
			if (!check(map, "byr", s -> {
				int n = Integer.parseInt(s);
				return n >= 1920 && n <= 2002;
			})) { return false; }
			
			if (!check(map, "iyr", s -> {
				int n = Integer.parseInt(s);
				return n >= 2010 && n <= 2020;
			})) { return false; }
			
			if (!check(map, "eyr", s -> {
				int n = Integer.parseInt(s);
				return n >= 2020 && n <= 2030;
			})) { return false; }
			
			if (!check(map, "hgt", s -> {
				String unit = s.substring(s.length() - 2, s.length());
				int n = Integer.parseInt(s.substring(0, s.length() - 2));
				if (unit.equals("cm")) {
					return n >= 150 && n <= 193;
				} else if (unit.equals("in")) {
					return n >= 59 && n <= 76;
				} else return false;
			})) { return false; }
			
			if (!check(map, "hcl", s -> {
				return s.matches("#[0-9a-f]{6}");
			})) { return false; }
			
			if (!check(map, "ecl", s -> {
				List<String> opts = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
				return opts.contains(s);
			})) { return false; }
			
			if (!check(map, "pid", s -> {
				Integer.parseInt(s);
				return s.length() == 9;
			})) { return false; }
		} catch (Exception e) { return false; }
		
		return true;
	}
	
	public static boolean check(Map<String, String> map, String key, Predicate<String> condition) {
		if (!map.containsKey(key)) return false;
		return condition.test(map.get(key));
	}
	
}

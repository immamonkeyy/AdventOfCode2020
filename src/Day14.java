import java.util.*;

public class Day14 {

	public static String input = "day14input.txt";

	public static void main(String[] args) {
		partB();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		char[] mask = new char[0];
		Map<Integer, Long> mem = new HashMap<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.startsWith("mask = ")) {
				mask = line.substring(7).toCharArray();
			} else {
				String[] pieces = line.split(" = ");
				int loc = Integer.parseInt(pieces[0].substring(4, pieces[0].length() - 1));
				String bitString = Integer.toBinaryString(Integer.parseInt(pieces[1]));
				String newString = mask(mask, bitString);
				mem.put(loc, Long.parseLong(newString, 2));
			}
		}
		
		long sum = 0;
		for (int key : mem.keySet()) {
			sum += mem.get(key);
		}
		System.out.println(sum);
		
		s.close();
	}
	
	public static String padstart(int n, char c, String s) {
		while (s.length() < 36) {
			s = c + s;
		}
		return s;
	}
	
	public static String mask(char[] m, String bitString) {
		String bitStringPadded = padstart(36, '0', bitString);
		char[] newString = new char[m.length];
		for (int i = 0; i < m.length; i++) {
			if (m[i] == 'X') {
				newString[i] = bitStringPadded.charAt(i);
			} else newString[i] = m[i];
		}
		return new String(newString);
	}
	
	public static void partB() {
		Scanner s = AOC.getScanner(input);
		char[] mask = new char[0];
		Map<Long, Integer> mem = new HashMap<>();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.startsWith("mask = ")) {
				mask = line.substring(7).toCharArray();
			} else {
				String[] pieces = line.split(" = ");
				int loc = Integer.parseInt(pieces[0].substring(4, pieces[0].length() - 1));
				int value = Integer.parseInt(pieces[1]);
				String newLoc = maskB(mask, Integer.toBinaryString(loc));
				List<String> locations = getLocs(newLoc);
				for (String str : locations) {
					mem.put(Long.parseLong(str, 2), value);
				}
			}
		}
		
		long sum = 0;
		for (long key : mem.keySet()) {
			sum += mem.get(key);
		}
		System.out.println(sum);
		
		s.close();
	}
	
	public static String maskB(char[] m, String bitString) {
		String bitStringPadded = padstart(36, '0', bitString);
		char[] newString = new char[m.length];
		for (int i = 0; i < m.length; i++) {
			if (m[i] == '0') {
				newString[i] = bitStringPadded.charAt(i);
			} else newString[i] = m[i];
		}
		return new String(newString);
	}
	
	public static List<String> getLocs(String maskedAddress) {
		List<String> list = new ArrayList<>();
		if (!maskedAddress.contains("X")) {
			list.add(maskedAddress);
		} else {
			int idx = maskedAddress.indexOf('X');
			String front = maskedAddress.substring(0, idx);
			List<String> tails = getLocs(maskedAddress.substring(idx + 1));
			for (int i = 0; i < 2; i++) {
				for (String t : tails) {
					list.add(front + i + t);
				}
			}
		}
		return list;
	}
}
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Day08 {

	public static String input = "day08input.txt";
	

	public static void main(String[] args) {
		partB();
	}
	
	public static void partA() {
		List<Instruction> instructions = new ArrayList<>();
		Scanner s = AOC.getScanner(input);
		while (s.hasNextLine()) {
			instructions.add(new Instruction(s.nextLine()));
		}
		
		int acc = 0;
		int index = 0;
		while (true) {
			Instruction i = instructions.get(index);
			if (i.visited) {
				System.out.println("Repeat, acc: " + acc);
				break;
			}
			i.visited = true;
			switch (i.text) {
				case "nop":
					index++;
					break;
				case "acc":
					index++;
					acc += i.num;
					break;
				case "jmp":
					index += i.num;
					break;
			}
		}
		s.close();
	}
	
	public static void partB() {
		List<Instruction> instructions = new ArrayList<>();
		Scanner s = AOC.getScanner(input);
		while (s.hasNextLine()) {
			instructions.add(new Instruction(s.nextLine()));
		}
		
		for (Instruction i : instructions) {
			if (i.text.equals("nop")) {
				i.text = "jmp";
				if (!isRepeat(instructions)) break;
				i.text = "nop";
			} else if (i.text.equals("jmp")) {
				i.text = "nop";
				if (!isRepeat(instructions)) break;
				i.text = "jmp";
			}
		}
		
		int acc = 0;
		int index = 0;
		while (true) {
			if (index == instructions.size()) {
				System.out.println(acc);
				break;
			}
			Instruction i = instructions.get(index);
			switch (i.text) {
				case "nop":
					index++;
					break;
				case "acc":
					index++;
					acc += i.num;
					break;
				case "jmp":
					index += i.num;
					break;
			}
		}
		s.close();
	}
	
	public static boolean isRepeat(List<Instruction> instructions) {
		int index = 0;
		
		for (Instruction i : instructions) {
			i.visited = false;
		}
		
		while (true) {
			if (index == instructions.size()) return false;
			Instruction i = instructions.get(index);
			if (i.visited) return true;
			i.visited = true;
			switch (i.text) {
				case "nop":
					index++;
					break;
				case "acc":
					index++;
					break;
				case "jmp":
					index += i.num;
					break;
			}
		}
	}
	
	public static class Instruction {
		String text;
		int num;
		boolean visited;
		
		public Instruction(String s) {
			text = s.substring(0, 3);
			String sign = s.substring(4, 5);
			num = Integer.parseInt(s.substring(5));
			if (sign.equals("-")) num *= -1;
			visited = false;
		}
	}
}
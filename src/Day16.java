import java.util.*;

public class Day16 {

	public static String input = "day16input.txt";

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		run();
		final long endTime = System.currentTimeMillis();
		
		double millis = endTime - startTime;
		double seconds = millis / 1000;
		double minutes = seconds / 60;
		
		System.out.println("\nTotal execution time:");
		System.out.println(millis + " millis");
		System.out.println(seconds + " seconds");
		System.out.println(minutes + " minutes");
	}
	
	public static void run() {
		partB();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		String line = s.nextLine();
		Set<Integer> valid = new HashSet<>();
		while (!line.isEmpty()) {
			String[] nums = line.split(": ")[1].split(" or ");
			for (String str : nums) {
				String[] bounds = str.split("-");
				for (int i = Integer.parseInt(bounds[0]); i <= Integer.parseInt(bounds[1]); i++) {
					valid.add(i);
				}
			}	
			line = s.nextLine();
		}
		
		while (!line.startsWith("nearby tickets:")) line = s.nextLine();
		int sum = 0;
		while (s.hasNextLine()) {
			String[] vals = s.nextLine().split(",");
			for (String str : vals) {
				int val = Integer.parseInt(str);
				if (!valid.contains(val)) {
					sum += val;
				}
			}
		}
		
		System.out.println(sum);
		s.close();
	}
	
	public static void partB() {
		Scanner s = AOC.getScanner(input);
		String line = s.nextLine();
		Map<String, Set<Integer>> rules = new HashMap<>();
		Set<Integer> valid = new HashSet<>();
		
		while (!line.isEmpty()) {
			String[] pieces = line.split(": ");
			String field = pieces[0];
			rules.put(field, new HashSet<>());
			
			String[] nums = pieces[1].split(" or ");
			for (String str : nums) {
				String[] bounds = str.split("-");
				for (int i = Integer.parseInt(bounds[0]); i <= Integer.parseInt(bounds[1]); i++) {
					rules.get(field).add(i);
					valid.add(i);
				}
			}	
			line = s.nextLine();
		}
		
		int numFields = rules.size();
		
		s.nextLine(); //your ticket
		String[] yourTicket = s.nextLine().split(",");
		s.nextLine(); //empty
		s.nextLine(); //nearby tickets
		
		List<Set<Integer>> tickets = new ArrayList<>();
		
		for (int i = 0; i < numFields; i++) {
			tickets.add(new HashSet<Integer>());
			tickets.get(i).add(Integer.parseInt(yourTicket[i]));
		}
		
		while (s.hasNextLine()) {
			String[] vals = s.nextLine().split(",");
			if (isValid(valid, vals)) {
				for (int i = 0; i < numFields; i++) {
					tickets.get(i).add(Integer.parseInt(vals[i]));
				}
			}
		}
		
		String[] fields = new String[numFields];
		assignFields(0, fields, tickets, rules);
		
//		System.out.println("Fields:");
//		for (String f : fields) {
//			System.out.println(f);
//		}
		
		long product = 1;
		int numDepartureFields = 0;
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i] + ": " + yourTicket[i]);
			if (fields[i].startsWith("departure")) {
				product *= Long.parseLong(yourTicket[i]);
				numDepartureFields++;
			}
		}
		
		System.out.println();
		System.out.println(product);
		System.out.println(numDepartureFields);
				
		s.close();
	}
	
	public static boolean assignFields(int start, String[] fields, List<Set<Integer>> tickets, Map<String, Set<Integer>> rules) {
		if (start == fields.length) return true;
		
		List<String> fieldNames = new ArrayList<>();
		for (String r : rules.keySet()) fieldNames.add(r);
		
		for (String rule : fieldNames) {
			if (rules.get(rule).containsAll(tickets.get(start))) {
				fields[start] = rule;
				Set<Integer> values = rules.remove(rule);
				if (assignFields(start + 1, fields, tickets, rules)) {
					return true;
				}
				rules.put(rule, values);
			}
		}
		return false;
	}
	
	public static boolean isValid(Set<Integer> valid, String[] vals) {
		for (String s : vals) {
			if (!valid.contains(Integer.parseInt(s))) return false;
		}
		return true;
	}
}

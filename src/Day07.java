import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Day07 {

	public static String input = "day07input.txt";
	
	//map of color -> colors it can be in
	//set of allowed colors to be in

	public static void main(String[] args) {
		partB();
	}
	
	//bright teal bag contain 4 shiny crimson bag, 3 faded red bag, 2 posh lavender bag.
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		Map<String, Set<String>> rules = new HashMap<>();
		
		while (s.hasNextLine()) {
			String[] linePieces = s.nextLine().split(" contain ");
			String outerBag = linePieces[0];
			String[] innerBags = linePieces[1].substring(0, linePieces[1].length() - 1).split(", ");
			for (String i : innerBags) {
				String bag = i.substring(2);
				if (!rules.containsKey(bag)) {
					rules.put(bag, new HashSet<>());
				}
				rules.get(bag).add(outerBag);
			}
		}
//		
//		for (String color : rules.keySet()) {
//			System.out.print(color + " --> ");
//			for (String a : rules.get(color)) {
//				System.out.print(a + ", ");
//			}
//			System.out.println();
//		}

		s.close();
		
		Set<String> outers = new HashSet<>();
		getColorsFor("shiny gold bag", outers, rules);
		System.out.println(outers.size());
		
	}
	
	// Kate v1
//	public static Set<String> bagCounter(Map<String, Set<String>> bagRules, String currentBag, Set<String> outerbags) {
//		if (bagRules.get(currentBag) != null) {
//			Set<String> newOuterBags = bagRules.get(currentBag);
//			for (String newOuterBag : newOuterBags) {
//				Set<String> updatedOuterBags = outerbags.addAll(newOuterBags);
//				if (!outerbags.contains(newOuterBag)) {
//					return bagCounter(bagRules, newOuterBag, updatedOuterBags);
//				}
//			}
//		}
//		return outerbags;
//	}
	
	// Kate v2
//	Set<String> bagCounter(Map<String, Set<String>> bagRules, String currentBag, Set<String> outerbags) {
//		    if (bagRules.get(currentBag).isEmpty()) return outerbags.add(currentBag);
//
//		    Set<String> newBags = bagRules.get(currentBag).get();
//
//		    if (!newBags.isEmpty()) {
//		        Set<String> updatedOuterbags = outerbags.addAll(newBags);
//		        newBags.filter(b -> !outerbags.contains(b))
//		                .map(vb ->bagCounter(bagRules, vb, updatedOuterbags));
//		    }
//
//		    return outerbags;
//		}
	
	public static void getColorsFor(String color, Set<String> outers, Map<String, Set<String>> rules) {
		if (rules.get(color) != null && !outers.containsAll(rules.get(color))) {
			outers.addAll(rules.get(color));
			for (String option : rules.get(color)) {
				getColorsFor(option, outers, rules);
			}
		}
	}
	
	// outer -> inner
	public static void partB() {
		Scanner s = AOC.getScanner(input);
		Map<String, Set<String>> rules = new HashMap<>();
		
		while (s.hasNextLine()) {
			String[] linePieces = s.nextLine().split(" contain ");
			String outerBag = linePieces[0];
			String[] innerBags = linePieces[1].substring(0, linePieces[1].length() - 1).split(", ");
			rules.put(outerBag, new HashSet<>());
			if (!innerBags[0].equals("no other bag")) {
				for (String bag : innerBags) {
					rules.get(outerBag).add(bag);
				}
			}
		}
//		
//		for (String color : rules.keySet()) {
//			System.out.print(color + " --> ");
//			for (String a : rules.get(color)) {
//				System.out.print(a + ", ");
//			}
//			System.out.println();
//		}

		s.close();
		
		int innerCount = getInnerCount("shiny gold bag", rules);
		System.out.println(innerCount);
	}
	
	public static int getInnerCount(String color, Map<String, Set<String>> rules) {
		if (rules.get(color).isEmpty()) {
			return 0;
		}
		int count = 0;
		for (String c : rules.get(color)) {
			int num = Integer.parseInt(c.substring(0, 1));
			String innerCol = c.substring(2);
			int countInsideColor = getInnerCount(innerCol, rules);
			System.out.println(innerCol + " has " + countInsideColor + " bags inside it");
			count += num * (countInsideColor + 1);
		}
		return count;
	}
}
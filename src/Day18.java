import java.util.*;

public class Day18 {

	public static String input = "day18input.txt";

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
		partA();
	}
	
	public static void partA() {
		Scanner s = AOC.getScanner(input);
		
		s.close();
	}
	
	public static void partB() {
		
	}
}
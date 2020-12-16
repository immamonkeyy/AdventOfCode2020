import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AOC {
	
	public static Scanner getScanner(String input) {
		try {
			return new Scanner(new File(input));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find input file.");
			return null;
		}
	}

	public static void endTime(long startTime) {
		final long endTime = System.currentTimeMillis();
		
		double millis = endTime - startTime;
		double seconds = millis / 1000;
		double minutes = seconds / 60;
		
		System.out.println("\nTotal execution time:");
		System.out.println(millis + " millis");
		System.out.println(seconds + " seconds");
		System.out.println(minutes + " minutes");
	}
}

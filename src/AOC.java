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

}

package se.wederbrand.facebook;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BeautifulStrings {
	public static void main(String... args) throws IOException {
		String filename = "beauty_final.txt";
		if (args.length > 0) {
			filename = args[0];
		}
		String result = doAll(filename);
		System.out.println(result);
		FileWriter fileWriter = new FileWriter("smiley_result.txt");
		fileWriter.write(result);
		fileWriter.close();
	}

	public static String doAll(String arg) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(arg));
		int count = scanner.nextInt();
		int i = 1;

		// go to next line
		scanner.nextLine();
		StringBuilder stringBuilder = new StringBuilder();
		while (i <= count) {
			int beauty = getMax(scanner.nextLine());
			stringBuilder.append("Case #").append(i++).append(": ").append(beauty).append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

	public static int getMax(String str) {
		// keep only letters
		// put all in another char array by character value, discard anything outside a and z
		char[] chars = new char['Z' + 1];
		for (Character aChar : str.toUpperCase().toCharArray()) {
			if (aChar < 'A' || aChar > 'Z') {
				continue;
			}

			chars[aChar]++;
		}

		Arrays.sort(chars);

		// read from the end
		int sum = 0;
		int currentValue = 26;
		for (int i = 'Z'; i >= 'A'; i--) {
			sum += currentValue-- * chars[i];
		}

		return sum;
	}
}

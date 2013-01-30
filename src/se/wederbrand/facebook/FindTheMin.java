package se.wederbrand.facebook;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class FindTheMin {
	public static void main(String... args) throws IOException {
		String filename = "min_final.txt";
		if (args.length > 0) {
			filename = args[0];
		}
		String result = doAll(filename);
		FileWriter fileWriter = new FileWriter("min_result.txt");
		fileWriter.write(result);
		fileWriter.close();
		System.out.println(result);
	}

	public static String doAll(String arg) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(arg));

		// discard one line
		scanner.nextLine();

		StringBuilder stringBuilder = new StringBuilder();
		int i = 1;
		while (scanner.hasNextInt()) {
			long n = scanner.nextInt();
			int k = scanner.nextInt();
			long a = scanner.nextLong();
			long b = scanner.nextLong();
			long c = scanner.nextLong();
			long r = scanner.nextLong();
			long value = getValue(n, k, a, b, c, r);
			stringBuilder.append("Case #").append(i++).append(": ").append(value).append(System.lineSeparator());
		}
		return stringBuilder.toString();
	}

	public static long getValue(long n, int k, long a, long b, long c, long r) {
		// create a sorted set that holds the k+1 lowest possible values
		SortedSet<Long> lowest = new TreeSet<>();
		for (long i = 0; i < k + 1; i++) {
			lowest.add(i);
		}

		// m[0] = a
		// m[i] = (b * m[i - 1] + c) % r
		ArrayList<Long> m = new ArrayList<>(k + 1);
		m.add(a);

		// create the known values
		for (int i = 1; i < k; i++) {
			long newValue = (b * m.get(i - 1) + c) % r;
			m.add(newValue);
			lowest.remove(newValue);
		}

		// add on extra on k+1
		Long first = lowest.first();
		lowest.remove(first);
		m.add(first);

		// rotate until we don't need to rotate any more
		// keep track of the first index relative the original array
		int i = 0;
		while (!lowest.isEmpty()) {
			rotate(m, lowest);
			i++;
		}

		// by now we should have a list that's stable and an index offset
		int wantedIndex = (int) ((n - 1 - i) % (k + 1));
		return m.get(wantedIndex);
	}

	public static void rotate(ArrayList<Long> runningLows, SortedSet<Long> lowest) {
		// remove the first element (it's not part of the condition anyway)
		Long removed = runningLows.remove(0);

		// put it back in the lowest list, if it's low enough and not there already
		if (removed < runningLows.size() && !lowest.contains(removed) && !runningLows.contains(removed)) {
			lowest.add(removed);
		}

		// get the next low value ..
		Long nextLow = lowest.first();
		lowest.remove(nextLow);

		// .. and put it in the list
		runningLows.add(nextLow);
	}
}

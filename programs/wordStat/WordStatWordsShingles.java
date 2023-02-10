import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;

public class WordStatWordsShingles {
	public static void putMap(String word, Map<String, Integer> words) {
		if (words.containsKey(word)) {
				words.put(word, words.get(word) + 1);
		} else {
			words.put(word, 1);
		}
	}
	public static void main(String[] args) {
		try {
			MyScanner scan = new MyScanner(args[0], "UTF8", new Check());
			Map<String, Integer> words = new TreeMap<>();
			String word;
			try {
				while (scan.hasNextToken()) {
					word = scan.nextToken().toLowerCase();
					if (word.length() < 3 && word.length() > 0) {
						putMap(word, words);
					} else if (word.length() > 0) {
						for (int i = 0; i < word.length() - 2; i++) {
							putMap(word.substring(i, i + 3), words);
						}
					}
				}
			} finally {
				scan.close();
			}
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(args[1]),
						StandardCharsets.UTF_8
				));
				try {
					for (String key : words.keySet()) {
						writer.write(key + " " + words.get(key));
						writer.newLine();
					}
				} finally {
					writer.close();
				}
			} catch (FileNotFoundException e) {
				System.out.println("File does not exist: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Output file writer error: " + e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Input file reader error: " + e.getMessage());
		}
	}

}
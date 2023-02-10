package wordStat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class WsppCountLastL {

	public static void addInMap(Map<String, Integer> lastWordInLine, Map<String, IntList> words) {
		for (String key : lastWordInLine.keySet()) {
			IntList numbers = words.getOrDefault(key, new IntList());
			numbers.add(lastWordInLine.get(key));
			words.put(key, numbers);
		}
	}

	public static void addInSortedMap(Map<Integer, ArrayList<String>> sortedWords, Map<String, IntList> words, Map<String, Integer> countWords) {
		for (String key : words.keySet()) {
			int count = countWords.get(key);
			ArrayList<String> numbers = sortedWords.getOrDefault(count, new ArrayList<String>());
			numbers.add(key);
			sortedWords.put(count, numbers);
		}
	}

	public static void main(String[] args) {
		try {
			Map<String, Integer> countWords = new LinkedHashMap<>();
			Map<Integer, ArrayList<String>> sortedWords = new TreeMap<>();
			Map<String, IntList> words = new LinkedHashMap<>();
			MyScanner scan = new MyScanner(args[0], "UTF8", new Check());
			try {
				String word;
				int idx = 0;
				int line = 0;
				Map<String, Integer> lastWordInLine = new LinkedHashMap<>();
				while (scan.hasNextToken()) {
					if (line != scan.lineNum()) {
						addInMap(lastWordInLine, words);
						lastWordInLine = new LinkedHashMap<>();
						line = scan.lineNum();
						idx = 0;
					}
					word = scan.nextToken().toLowerCase();
					idx++;
					int count = countWords.getOrDefault(word, 0);
					countWords.put(word, count + 1);
					lastWordInLine.put(word, idx);
				}
				addInMap(lastWordInLine, words);
				addInSortedMap(sortedWords, words, countWords);

			} finally {
				scan.close();
			}

			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(args[1]),
						StandardCharsets.UTF_8
				));
				try {
					for (int key : sortedWords.keySet()) {
						for (String wr : sortedWords.get(key)) {
							IntList numbers = words.get(wr);
							writer.write(wr + " " + key);
							for (int i = 0; i < numbers.lastIdx(); i++) {
								writer.write(" " + numbers.get(i));
							}
							writer.newLine();
						}
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
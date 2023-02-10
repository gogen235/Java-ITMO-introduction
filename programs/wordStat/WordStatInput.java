package wordStat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {

	public static void main(String[] args) {
		try {
			MyScanner scan = new MyScanner(args[0], "UTF8", new Check());
			Map<String, Integer> words = new LinkedHashMap<>();
			String word;
			try {
				while (scan.hasNextToken()) {
					word = scan.nextToken().toLowerCase();
					if (words.containsKey(word)) {
						words.put(word, words.get(word) + 1);
					} else {
						words.put(word, 1);
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
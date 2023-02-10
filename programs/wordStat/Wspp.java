package wordStat;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
	public static void main(String[] args) {
		try {
			Map<String, IntList> words = new LinkedHashMap<>();
			MyScanner scan = new MyScanner(args[0], "UTF8", new Check());
			try {
				String word;
				int idx = 0;
				while (scan.hasNextToken()) {
					word = scan.nextToken().toLowerCase();
					idx++;
					IntList numbers = words.getOrDefault(word, new IntList());
					numbers.add(idx);
					words.put(word, numbers);
				}
			} finally {
				scan.close();
			}
			
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(args[1]),
					"UTF8"
				));
				try {
					for (String key : words.keySet()) {
						IntList numbers = words.get(key);
    					writer.write(key + " " + numbers.lastIdx());
    					for (int i = 0; i < numbers.lastIdx(); i++) {
    						writer.write(" " + numbers.get(i));
    					}
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
package wordStat;

import java.io.*;

public class MyScanner {
	private final Reader reader;
	private static final int BUFFER_SIZE = 1024;
	private static final String SEP = System.lineSeparator();
	private final char[] buffer = new char[BUFFER_SIZE];
	private int read;
	private int idx = 0;
	private boolean hasNextToken = true;
	private int lineNum = 0;
	private StringBuilder nextToken = new StringBuilder();
	private int idxSep = 0;
	private final CheckToken check;

	private static class DefaultCheck implements CheckToken {
		public boolean checkToken(char chr) {
			return Character.isWhitespace(chr);
		}
	}

	private boolean readBuffer() throws IOException {
		if (idx == read) {
			read = reader.read(buffer);
			idx = 0;
		}
		return true;
	}

	private void skipSpace() throws IOException {
		nextToken = new StringBuilder();
		while (readBuffer() && idx < read && check.checkToken(buffer[idx])) {
			checkSep();
			idx++;
		}
		if (read == -1) {
			hasNextToken = false;
		}
	}

	private void readToken() throws IOException {
		while (readBuffer() && idx < read && !check.checkToken(buffer[idx])) {
			nextToken.append(buffer[idx]);
			idx++;
		}
	}

	public String nextToken() throws IOException {
		skipSpace();
		readToken();
		return nextToken.toString();
	}

	public boolean hasNextToken() throws IOException {
		skipSpace();
		return hasNextToken;
	}

	public int lineNum() {
		return lineNum;
	}

	public void close() throws IOException {
		reader.close();
	}

	private void checkSep() {
		if (buffer[idx] == SEP.charAt(idxSep)) {
			idxSep++;
			if (idxSep == SEP.length()) {
				lineNum++;
				idxSep = 0;
			}
		} else {
			idxSep = 0;
		}
	}

	MyScanner(InputStream in, String charset) throws IOException {
		check = new DefaultCheck();
		reader = new BufferedReader(new InputStreamReader(in, charset));
		read = reader.read(buffer);
	}

	MyScanner(String file, String charset) throws IOException {
		check = new DefaultCheck();
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		read = reader.read(buffer);
	}

	MyScanner(InputStream in, String charset, CheckToken checkToken) throws IOException {
		check = checkToken;
		reader = new BufferedReader(new InputStreamReader(in, charset));
		read = reader.read(buffer);
	}

	MyScanner(String file, String charset, CheckToken checkToken) throws IOException {
		check = checkToken;
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		read = reader.read(buffer);
	}

}
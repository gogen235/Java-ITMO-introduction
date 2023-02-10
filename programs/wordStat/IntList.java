package wordStat;

import java.util.Arrays;

public class IntList {
	private int[] list = new int[1];
	private int lastIdx = 0;
	private int size = 1;
	
	private void restruct() {
		size *= 2;
		list = Arrays.copyOf(list, size);
	}

	public void add(int val) {
		if (size == lastIdx) {
			restruct();
		}
		list[lastIdx] = val;
		lastIdx++;
	}

	public void assign(int idx, int val) {
		if (idx < lastIdx) {
			list[idx] = val;
		}
	}

	public int get(int idx) {
		return list[idx];
	}

	public int lastIdx() {
		return lastIdx;
	}
}
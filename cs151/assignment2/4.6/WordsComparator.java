package maximum;

import java.util.Comparator;

public class WordsComparator implements Comparator<String> {
	public int compare(String first, String second) {
		return  (second.length()) - (first.length());
	}
}

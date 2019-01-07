package maximum;

import java.util.ArrayList;

public class MaximumTester {
	
	public static void main(String[] args)
    {
		ArrayList<String> words = new ArrayList<>();
        words.add(new String("Areeb"));
        words.add(new String("Sugoihoih"));
        words.add(new String("Raymond"));
        words.add(new String("Perilous"));
        words.add(new String("Discombobulated"));
        words.add(new String("Stupendous"));
        words.add(new String("why"));
        words.add(new String("cry"));
        
        Maximum method = new Maximum();
        WordsComparator comp = new WordsComparator();
        String max = method.maximum(words, comp);
        System.out.print(max);
    }

}

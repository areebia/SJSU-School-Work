package filter;

public class FilterTester {
	public static void main(String[] args)
    {
		String[] words = {"amy", "cry", "why", "hi", "Areeb", "Kuristina", "Angus", "dolt" };
		
		Filterer threeLetters = new Filterer();
		Filter coffee = new Filterer();
		
		String[] filtered = threeLetters.filter(words, coffee);
		 for(String s: filtered)
		 {
			 System.out.println(s);
		 }
		
    }
    
}

package filter;

public class Filterer implements Filter{
	
	public boolean accept(String x)
	{
		if(x.length() > 3)
		return false;
		else 
		return true;
	}
	
	public static String[] filter(String[] a, Filter f)
	{
		String[] filtered = new String[a.length];
		int count = 0;
		for(String s: a)
		{
			if(f.accept(s))
			{
			   filtered[count] = s;
			}
			count++;
		}
		return filtered;
		
	}
	
	
}

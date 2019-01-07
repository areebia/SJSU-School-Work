package maximum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Maximum {
	
	public static String maximum(ArrayList<String> a, Comparator<String> c)
	{
		Collections.sort(a, c);
		return a.get(0);
	}
}

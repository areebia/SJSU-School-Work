package rectangle;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RectangleTester {

	public static void main(String[] args)
	{
		Rectangle2D.Double[] rect = {
		         new Rectangle2D.Double(123, 321, 300, 300),
		new Rectangle2D.Double(25, 50, 20, 20),
		new Rectangle2D.Double(300, 300, 123, 321),
		new Rectangle2D.Double(3, 3, 3, 3),
		new Rectangle2D.Double(123, 97, 60, 30),
		new Rectangle2D.Double(123, 321, 300, 300),
		new Rectangle2D.Double(69, 96, 100, 100),
		new Rectangle2D.Double(123, 321, 200, 100)};
		
		
		RectangleComparator comp = new RectangleComparator();
		Arrays.sort(rect, comp);
		for(Rectangle2D.Double r: rect)
		{
			System.out.println(r.getX()+" "+r.getY()+" "+r.getWidth()+" "+r.getHeight());
		}
	}
}
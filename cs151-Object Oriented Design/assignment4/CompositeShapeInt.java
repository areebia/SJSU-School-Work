package shapemoves;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;


public interface CompositeShapeInt
{
   
   void add(Shape aShape);

   Rectangle getBounds();

   void draw (Graphics2D g);
	   
}

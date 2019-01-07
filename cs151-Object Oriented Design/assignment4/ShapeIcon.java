package shapemoves;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.Icon;
/**
 * where the magical shape icon is made
 * @author Areeb Yaqub
 *
 */
public class ShapeIcon implements Icon
{    
	private CompositeShape shape;
     private int width;
     private int height;
 
   public ShapeIcon(CompositeShape aShape, int aWidth, int aHeight)
   {
      shape = aShape;
      width = aWidth;
      height = aHeight;
   }

   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      double shapeWidth = shape.getBounds().getWidth();
      double shapeHeight = shape.getBounds().getHeight();

      double scaleX = Math.max(1, shapeWidth / width);
      double scaleY = Math.max(1, shapeHeight / height);
      double scale = 1 / Math.max(scaleX, scaleY);

      Graphics2D g2 = (Graphics2D)g;

      AffineTransform oldTransform = g2.getTransform();
      g2.translate(x, y);
      g2.scale(scale, scale);
      g2.setColor(Color.black);
      shape.draw(g2);
      g2.setTransform(oldTransform);
   }


}



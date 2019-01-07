package frame;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * An icon that has the shape of the planet Mars.
 */
public class CircleIcon implements Icon{
	
	
	private Color currentColor;
	/**
	 * Constructs a Mars icon of a given size.
	 * 
	 * @param aSize
	 *            the size of the icon
	 */
	public CircleIcon(int aSize) {
		size = aSize;
	}

	public int getIconWidth() {
		return size;
	}

	public int getIconHeight() {
		return size;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);
		g2.setColor(currentColor);
		g2.fill(circle);
		
	}
	
	public void setCurrentColor(Color c)
	{
		currentColor = c;
	}

	private int size;
}
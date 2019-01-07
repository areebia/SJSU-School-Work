package rectangle;

import java.awt.geom.Rectangle2D;
import java.util.Comparator;

public class RectangleComparator implements Comparator<Rectangle2D.Double> {

	public int compare(Rectangle2D.Double first, Rectangle2D.Double second) {

		if (first.getX() == second.getX()) {
			if (first.getY() == second.getY()) {
				if (first.getWidth() == second.getWidth()) {
					if (first.getHeight() == second.getHeight()) {
						return 0;
					} else {
						return (int) (first.getHeight() - second.getHeight());
					}
				} else {
					return (int) (first.getWidth() - second.getWidth());
				}
			} else {
				return (int) (first.getY() - second.getY());
			}
		} else {
			return (int) (first.getX() - second.getX());
		}
	}
}

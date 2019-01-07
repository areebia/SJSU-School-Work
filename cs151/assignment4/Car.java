package shapemoves;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Car extends CompositeShape
{
	public Car(int xCoordinate, int yCoordinate, int width)
	{
		super(xCoordinate, yCoordinate);
	
		Rectangle2D.Double body = new Rectangle2D.Double(getX(), getY() + width / 6, width - 1, width / 6);
		
		Ellipse2D.Double frontTire = new Ellipse2D.Double(getX() + width / 6, getY() + width / 3, width / 6, width / 6);
		
		Ellipse2D.Double rearTire = new Ellipse2D.Double(getX() + width * 2 / 3, getY() + width / 3, width / 6,	width / 6);
		
		Point2D.Double r1 = new Point2D.Double(getX() + width / 6, getY() + width / 6);
		
		Point2D.Double r2 = new Point2D.Double(getX() + width / 3, getY());
		
		Point2D.Double r3 = new Point2D.Double(getX() + width * 2 / 3, getY());
		
		Point2D.Double r4 = new Point2D.Double(getX() + width * 5 / 6, getY() + width / 6);
		
		Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
		
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
		
		add(body);
		add(frontTire);
		add(rearTire);
		add(frontWindshield);
		add(roofTop);
		add(rearWindshield);
	}
}

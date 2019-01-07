package shapemoves;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Skewer extends CompositeShape
{
	private Ellipse2D.Double topCircle, bottomCircle, middleCircle;
	private Rectangle2D.Double middleRectangle, lowerMiddleRectangle;
	private Line2D.Double topLine, bottomLine;

	public Skewer(int xCoordinate, int yCoordinate, int width)
	{
		super(xCoordinate, yCoordinate);
		
		middleCircle = new Ellipse2D.Double(getX(), getY(), width, width);
		topCircle = new Ellipse2D.Double(getX(), getY()-width*2, width, width);
		bottomCircle = new Ellipse2D.Double(getX(), getY() + width *2, width, width);
		
		lowerMiddleRectangle = new Rectangle2D.Double(getX(), getY()+ width, width, width);
		middleRectangle = new Rectangle2D.Double(getX(), getY() - width, width, width);
		
		topLine = new Line2D.Double(getX() + width/2, getY() - width * 2, getX() + width/2, getY() - width * 4);
		bottomLine = new Line2D.Double(getX()+ width/2, getY() + width *3, getX() + width/2, getY() + width *4);
		
		
		add(topCircle);
		add(middleCircle);
		add(bottomCircle);
		add(lowerMiddleRectangle);
		add(middleRectangle);
		add(topLine);
		add(bottomLine);
	
	}
}

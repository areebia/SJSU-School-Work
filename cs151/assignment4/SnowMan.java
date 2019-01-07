package shapemoves;

import java.awt.geom.Ellipse2D;

public class SnowMan extends CompositeShape
{
	private Ellipse2D.Double topCircle, bottomCircle;

	public SnowMan(int xCoordinate, int yCoordinate, int width)
	{
		super(xCoordinate, yCoordinate);
		
		topCircle = new Ellipse2D.Double(getX(), getY(), width, width);
		bottomCircle = new Ellipse2D.Double(getX(), getY() + width, width, width);
		
		add(topCircle);
		add(bottomCircle);
	}
}

package shapemoves;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class PositionedShape
{
	private Point2D position;
	private CompositeShape shape;

	public PositionedShape(Point2D position, CompositeShape shape)
	{
		this.position = position;
		this.shape = shape;
	}

	public void draw(Graphics2D g2)
	{
		AffineTransform moveShape = g2.getTransform();
		g2.translate(position.getX(), position.getY());
		shape.draw(g2);
		g2.setTransform(moveShape);
	}
}

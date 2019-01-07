package shapemoves;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;


public class CompositeShape implements CompositeShapeInt
{
	private int xCoordinate;
	private int yCoordinate;
	private ArrayList<Shape> shapes;

	public CompositeShape(int xCoordinate, int yCoordinate)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		shapes = new ArrayList<Shape>();
	}

	public int getX()
	{
		return xCoordinate;
	}

	public int getY()
	{
		return yCoordinate;
	}

	public void add(Shape newShape)
	{
		shapes.add(newShape);
	}
	
	
	public Rectangle getBounds()
	{	
		if (shapes.size() == 0)
		{
			return new Rectangle();
		}
		
		Shape localScale = shapes.get(0);
		Rectangle localRec = localScale.getBounds();
		for (int i = 1; i < shapes.size(); i++)
		{
			localScale = (Shape)shapes.get(i);
			localRec = localRec.union(localScale.getBounds());
		}
		return localRec;
	}

	public void draw(Graphics2D g2)
	{
		for (int i = 0; i < shapes.size(); i++)
		{
			g2.draw(shapes.get(i));
		}
	}

}
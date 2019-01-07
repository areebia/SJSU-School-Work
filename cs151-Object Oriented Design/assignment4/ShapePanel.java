package shapemoves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * a panel to hold and keep shapes tyvm.
 * @author Areeb Yaqub
 *
 */
		
public class ShapePanel extends JPanel
{
	private CompositeShape currentShape;
	private ArrayList<PositionedShape> shapes;

	public ShapePanel()
	{
		shapes = new ArrayList<PositionedShape>();
		
		addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent event)
					{
						Point2D mousePoint = event.getPoint();
						shapes.add(new PositionedShape(mousePoint,currentShape));
				        repaint();
					}
				});
	}

	public void setShape(CompositeShape newShape)
	{
		currentShape = newShape;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for (int i = 0; i < shapes.size(); i++)
		{
			PositionedShape currentShape = (PositionedShape) shapes.get(i);
			currentShape.draw(g2);
		}
	}

}
package shapemoves;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ShapeFrame extends JFrame
{
	public static final int BUTTON_WIDTH = 30;
	public static final int BUTTON_HEIGHT = 30;
	
		
	private ShapePanel shapeHolder;	
	private Box buttonPanel;			
			


	public ShapeFrame()
	{
		buttonPanel = Box.createHorizontalBox();
		add(buttonPanel, BorderLayout.NORTH);

		shapeHolder = new ShapePanel();
		add(shapeHolder, BorderLayout.CENTER);
	}

	public void addShape(CompositeShape newShape)
	{
		JButton icon = new JButton(new ShapeIcon(newShape, BUTTON_WIDTH, BUTTON_HEIGHT));
		
		icon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("new Icon");
				shapeHolder.setShape(newShape);
			}
		});

		buttonPanel.add(icon);
	}

}

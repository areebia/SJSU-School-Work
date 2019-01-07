
package shapemoves;

import javax.swing.JFrame;

public class ShapeDisplayer
{
	public static void main(String[] args)
	{
		ShapeFrame frame = new ShapeFrame();
		frame.addShape(new Car(0, 10, 50));
		frame.addShape(new SnowMan(0, 0, 20));
		frame.addShape(new Skewer(0, 20, 15));

		frame.setSize(400, 600);
		frame.setTitle("Shape Displayer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}

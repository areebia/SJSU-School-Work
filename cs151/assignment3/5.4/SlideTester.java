package slide;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SlideTester {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JSlider slide = new JSlider();
		CarIcon car = new CarIcon(100);
		JLabel label = new JLabel(car);
		frame.setLayout(new FlowLayout());

		slide.setMajorTickSpacing(10);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);

		
			frame.add(label);
			frame.add(slide);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			slide.addChangeListener((ChangeEvent e) -> {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int level = (int) source.getValue();
					car.setWidth(level);
					label.repaint();
				}

		});
	}
}

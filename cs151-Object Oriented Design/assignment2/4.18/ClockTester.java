package clock;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
   This program shows a clock that is updated once per second.
*/
public class ClockTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FIELD_WIDTH = 20;
      final JTextField textField = new JTextField(FIELD_WIDTH);

      frame.setLayout(new FlowLayout());
      frame.add(textField);
      
 
      ClockIcon clock = new ClockIcon(100);
      JLabel label = new JLabel(clock);
      
      frame.add(label);
      
    

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}

package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrameTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      final int FIELD_WIDTH = 20;
      JTextField textField = new JTextField(FIELD_WIDTH);
      textField.setText("Fill the circle with a color!");
      
      CircleIcon circle = new CircleIcon(20);
      JLabel label = new JLabel(circle);
      
      JButton red = new JButton("Red");
      red.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent event)
            {
              // Button action goes here
              circle.setCurrentColor(Color.RED);
              label.repaint();
              
              
            }
          } );
     
      
      JButton blue = new JButton("Blue");
      JButton green = new JButton("Green");

      
      blue.addActionListener(new
          ActionListener()
          {
          public void actionPerformed(ActionEvent event)
          {
          // Button action goes here
            circle.setCurrentColor(Color.BLUE);
              label.repaint();
          }
          });
      
      green.addActionListener(new
          ActionListener()
          {
          public void actionPerformed(ActionEvent event)
          {
          // Button action goes here
            circle.setCurrentColor(Color.GREEN);
              label.repaint();
          }
          });
      
      frame.setLayout(new FlowLayout());

     
      frame.add(red);
      frame.add(blue);
      frame.add(green);
      frame.add(textField);
      frame.add(label);
      

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }

}
  

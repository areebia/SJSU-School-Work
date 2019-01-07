package mvc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MvcTester extends JPanel
{
	
	
	public MvcTester() 
	{
		textShowsUp.setEditable(false);
		textShowsUp.setPreferredSize(new Dimension(200,300));
		addButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent newEvent) 
					{
						String text = textInput.getText();
						textShowsUp.append(text + "\n");
						textInput.selectAll();
					}
				});

		setLayout(new BorderLayout());
		add(textInput, BorderLayout.SOUTH);
		add(textShowsUp, BorderLayout.CENTER);
		add(addButton, BorderLayout.NORTH);
	}

	public static void showMeTheWords()
	{
		JFrame frame = new JFrame("Mvc Tester");
		frame.setVisible(true);
		
		frame.add(new MvcTester());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		showMeTheWords();
	}
	
	Button addButton = new Button("add");
	JTextArea textShowsUp = new JTextArea();
	JTextField textInput = new JTextField();
}
		
		



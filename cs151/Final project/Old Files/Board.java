package master;

/**
 * Code modified from Zetcode.com Author Jan Bodner
 * http://zetcode.com/tutorials/javagamestutorial/snake/?fbclid=IwAR3h84Q5aMnLCI4__OAeKchoXRohSirxb01QogPVMukiOxRg-POtN34A2rQ
 * 
 * Javacode authors: Nishank Kuppa, Ruchika Kotha, Areeb Yaqub 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Main panel that contains the Snake game
 * @author Nishank Kuppa, Areeb Yaqub, Ruchika Kotha
 *
 */
public class Board extends JPanel implements ActionListener, GameMethods
{

	private final int B_WIDTH = 300; // width of board panel
	private final int B_HEIGHT = 300; // height of board panel
	private final int DOT_SIZE = 10; // size of the snake
	private final int ALL_DOTS = 900; // all possible dots on the board
	private final int RAND_POS = 29; // random starting position
	private final int DELAY = 140; // time delay between movement
	private int winCondition = 10;

	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];

	private int dots;
	private int apple_x;
	private int apple_y;

	// movement determiners
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;

	private Timer timer;
	private Image ball;
	private Image apple;
	private Image head;

	public Board()
	{
		initializeBoard(); // start
	}

	/**
	 * The start of the game, helps to initialize all the variables on the board
	 *
	 */
	public void initializeBoard()
	{

		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		loadImages();
		initGame();
	}

	/**
	 * gets the images that are provided in the location. has a head, body and apple
	 * image
	 */
	public void loadImages()
	{

		ImageIcon snakeBody = new ImageIcon("src/dot.png");
		ball = snakeBody.getImage();

		ImageIcon foodItem = new ImageIcon("src/apple.png");
		apple = foodItem.getImage();

		ImageIcon snakeHead = new ImageIcon("src/head.png");
		head = snakeHead.getImage();
	}

	/**
	 * main initializer method to set up the board sets up the snake, the randomized
	 * apple, and a timer.
	 * 
	 */
	public void initGame()
	{

		dots = 3;

		for (int z = 0; z < dots; z++)
		{
			x[z] = 50 - z * 10;
			y[z] = 50;
		}

		appleLocation();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	/**
	 * calls method to place images and update board
	 * 
	 * 
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		doDrawing(g);
	}

	/**
	 * updates the board with new apple locations and decides whether game has been
	 * won or is over.
	 * 
	 */
	public void doDrawing(Graphics g)
	{

		if (inGame)
		{

			g.drawImage(apple, apple_x, apple_y, this);

			for (int z = 0; z < dots; z++)
			{
				if (z == 0)
				{
					g.drawImage(head, x[z], y[z], this);
				} else
				{
					g.drawImage(ball, x[z], y[z], this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else if (dots == winCondition)
		{

			winGame(g);
		} else
		{
			gameOver(g);
		}
	}

	/**
	 * Creates game over scene when game has been lost.
	 * 
	 */
	public void gameOver(Graphics g)
	{

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

	}

	/**
	 * creates game won scene when game has been won.
	 * 
	 */
	public void winGame(Graphics g)
	{

		String msg = "You won! Now get up!";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
		
	}

	/**
	 * Checks when an apple has been eaten and to increase snake length
	 * appropriately.
	 * 
	 */
	private void checkApple()
	{

		if ((x[0] == apple_x) && (y[0] == apple_y))
		{

			dots++;
			appleLocation();
		}

		if (dots == winCondition)
		{
			inGame = false;
		}
	}

	/**
	 * Allows for the snake movement to be detected and recreated accordingly.
	 * 
	 */
	public void move()
	{

		for (int z = dots; z > 0; z--)
		{
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection)
		{
			x[0] -= DOT_SIZE;
		}

		if (rightDirection)
		{
			x[0] += DOT_SIZE;
		}

		if (upDirection)
		{
			y[0] -= DOT_SIZE;
		}

		if (downDirection)
		{
			y[0] += DOT_SIZE;
		}
	}

	/**
	 * checks if snake has hit a wall or itself.
	 * 
	 */
	public void checkCollision()
	{

		for (int z = dots; z > 0; z--)
		{

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z]))
			{
				inGame = false;
			}
		}

		if (y[0] >= B_HEIGHT)
		{
			inGame = false;
		}

		if (y[0] < 0)
		{
			inGame = false;
		}

		if (x[0] >= B_WIDTH)
		{
			inGame = false;
		}

		if (x[0] < 0)
		{
			inGame = false;
		}

		if (!inGame)
		{
			timer.stop();
		}
	}

	/**
	 * The method that randomizes the apple location
	 * 
	 */
	private void appleLocation()
	{

		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE));
	}

	/**
	 * repaints the board after each checking cycle.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (inGame)
		{

			checkApple();
			checkCollision();
			move();
		}

		repaint();
	}

	/**
	 * an inner class adapter to allow for keys on a keyboard to be utilized for the
	 * game movement.
	 * 
	 */
	private class TAdapter extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e)
		{

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!rightDirection))
			{
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection))
			{
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_UP) && (!downDirection))
			{
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!upDirection))
			{
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
		}
	}
}

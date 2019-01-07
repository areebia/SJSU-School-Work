package master;

import java.awt.Graphics;

/**
 * An interface that defines common methods for games
 * This interface can be used to implement other games for this advanced alarm clock
 * @author Nishank Kuppa, Ruchika Kotha, Areeb Yaqub
 *
 */
public interface GameMethods
{
	 void initializeBoard();
	 void loadImages();
	 void initGame();
	 void paintComponent(Graphics g);
	 void doDrawing(Graphics g);
	 void gameOver(Graphics g);
	 void winGame(Graphics g);
	 void move();
	 void checkCollision();
	
}

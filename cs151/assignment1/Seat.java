package reserve;
// 2d array to create the seats of an airplane. not completely connected to the 
// database, just a visual representation of the passengers who take certain spots. 
public class Seat {
	
	private String firstClassSeats[][];
	private String economyClassSeats[][];
 
	public Seat()
	{
		firstClassSeats = new String[3][4]; 
		firstClassSeats[0][0] = "A";
		firstClassSeats[0][1] = "B";
		firstClassSeats[0][2] = "C";
		firstClassSeats[0][3] = "D";
		
		economyClassSeats = new String [21][6];
		
		economyClassSeats[0][0] = "A";
		economyClassSeats[0][1] = "B";
		economyClassSeats[0][2] = "C";
		economyClassSeats[0][3] = "D";
		economyClassSeats[0][4] = "E";
		economyClassSeats[0][5] = "F";
		
	}
	
	
	public void setSeats()
	{
		for(int row = 1; row < firstClassSeats.length; row++)
		{
			for (int column = 0; column < firstClassSeats[0].length; column++)
			{
				firstClassSeats[row][column] = String.valueOf(row);
				
			}
		}
		
		for(int row = 1; row < economyClassSeats.length; row++)
		{
			for (int column = 0; column < economyClassSeats[0].length; column++)
			{
				economyClassSeats[row][column] = String.valueOf(row + 9);
			}
		}
	}
	
	public void printSeats()
	{	
		for(int row = 0; row < firstClassSeats.length; row++)
		{
			for (int column = 0; column < firstClassSeats[0].length; column++)
			{
				System.out.print(firstClassSeats[row][column] + " ");
			}
			 System.out.println();
		}
		
		for(int row = 0; row < economyClassSeats.length; row++)
		{
			for (int column = 0; column < economyClassSeats[0].length; column++)
			{
				if(row == 0 )
					System.out.print(economyClassSeats[row][column] +"  ");
				else
					System.out.print(economyClassSeats[row][column] +" ");
			}
			 System.out.println();
		}
		
	}
	
			
	
	
}

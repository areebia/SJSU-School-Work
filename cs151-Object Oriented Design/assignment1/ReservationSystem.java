package reserve;

import java.util.Scanner;

public class ReservationSystem {

	public static void main(String[] args)
	{
		
		/*Scanner groupType = new Scanner(System.in);
		System.out.print("Enter # of party: ");
		int partyNumber = groupType.nextInt();
		if(partyNumber == 1)
		{
			
		}
		else
		{
			
		}*/
		Seat one = new Seat();
		one.setSeats();
		one.printSeats();
	}
}

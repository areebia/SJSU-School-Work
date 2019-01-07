package reserve;

public class Passenger {

	private String name; 
	private boolean seated;
	private String preference;
	private String seatAssign;
	
	public Passenger()
	{
		this.name = name;
		seated = false;
		this.preference = preference;
		this.seatAssign = seatAssign;
	}
	
	
	public boolean seated()
	{
		seated = true;
		return seated;
	}
	
	public boolean unseated()
	{
		seated = false;
		return seated;
	}
	
	public void setPref(String letter)
	{
		preference = letter;
		
	}
	
	public void setSeatAssign(String seat)
	{
		seatAssign = seat;
	}
}

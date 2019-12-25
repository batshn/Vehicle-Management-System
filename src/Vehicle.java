import java.util.*;
import java.io.Serializable;
import java.util.Date;

class Vehicle implements Serializable  {
	
	private static final long serialVersionUID = 1765522370875753494L;
	
	private String vehicleID;
	private String hirerID;
	private String description;
	private String status;
	private double dailyRate;
	private int odoMeter;
	private DateTime hiredDateTime; 
	
	
	public Vehicle(String vehicleID, String description, double dailyRate, int odoMeter)
	{
		this.vehicleID = vehicleID;
		this.description = description;
		this.dailyRate = dailyRate;
		this.odoMeter = odoMeter;
		this.status = "A";
	}
	
	// when car is hired
	public void hire(String hirerID) throws StatusException, OdometerException
	{
		if (status.compareTo("A")==0)
		{
		//	DateTime d=new DateTime();
			this.hirerID = hirerID;
			hiredDateTime = new DateTime();
			status = "H";
		}
		else 
			throw new StatusException("WARNING: Car with " + vehicleID + " could not be hired !", status);
	}
	
	
	// when car is sent for service
	public void service() throws StatusException
	{
		if ( status.compareTo("A") == 0 ) 
		{
			status = "S";
		}
		else 
			throw new StatusException("WARNING: Car with " + vehicleID + " cannot be sent for service !", status);
		
	}
	
	// when car is returned from service
	public void serviceComplete(int odo) throws StatusException
	{
		if ( status.compareTo("S") == 0)
		{
			status = "A"; 
			odoMeter = odo;
		}
		else 
			throw new StatusException("WARNING: Car with " + vehicleID + " cannot be returned from service !", status);
	}
	

	// when car hire is completed
	public double hireComplete(int odo) throws StatusException , OdometerException
	{
		if (status.compareTo("H") == 0 )
		{
			if (odoMeter < odo )
			{
				DateTime hcDate = new DateTime();
				status = "A";
				odoMeter = odo;
				
				return DateTime.diffDays(hcDate, hiredDateTime) * dailyRate;
			}
			else 
				throw new OdometerException("WARNING: Car with " + vehicleID + " could not be returned from hire because odometer is less than starting one !", odoMeter);
		}
		else 
			throw new StatusException("WARNING: Car with " + vehicleID + " could not be returned from hire !", status);
	}
	
	public void print()
	{
		System.out.println("\n********************** Vehicle Details **********************");
		System.out.println(DateTime.getCurrentTime());
		System.out.println("vehicle ID=" + vehicleID + "   Description=" + description + "   Status=" + status + "   Daily Rate=" + dailyRate + "   Odometer reading=" + odoMeter);
		//System.out.println(toString());
		if(status == "H") System.out.println("Hirer=" + hirerID + " Date/time of hire=" + hiredDateTime.toString());
	}
	
	
	/*public String toString()
	{
		String summary;
		
		summary = "vehicle ID=" + vehicleID + "   Description=" + description + "   Status=" + status + "   Daily Rate=" + dailyRate + "   Odometer reading=" + odoMeter;
		
		return summary;
		//long l = getTime();
		//Date gct = new Date(l);
		//return gct.toString();
	}*/
	
	public String getVehicleID() 
	{
		return vehicleID;
	}
	
	
	public String getDescription() 
	{
		return description;
	}
	
	
	public String getStatus() 
	{
		return status;
	}
	

	public double getDailyRate() 
	{
		return dailyRate;
	}
	
	
	public int getOdometer() 
	{
		return odoMeter;
	}
	
	public String getHirer() 
	{
		return hirerID;
	}
	
	
	public String getHiredDateTime() 
	{
		return hiredDateTime.toString();
	}
}

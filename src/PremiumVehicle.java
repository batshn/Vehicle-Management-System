import java.util.*;
import java.util.Date;


class PremiumVehicle extends Vehicle {
	
	private static final long serialVersionUID = -1166436618418374937L;
	
	private int dailyMileage;
	private int serviceLenght;
	private int lastService;
	
	public PremiumVehicle(String vehicleID, String description, double dailyRate, int odoMeter, int dailyMileage, int serviceLenght, int lastService)
	{
		super(vehicleID, description, dailyRate, odoMeter);
		
		this.dailyMileage = dailyMileage;
		this.serviceLenght = serviceLenght;
		this.lastService = lastService;
	}
	
	
	// when car is hired
	public void hire(String hirerID) throws StatusException, OdometerException
	{
		if (super.getOdometer() < (lastService + serviceLenght) )
			super.hire(hirerID);
		else 
			throw new OdometerException("\nCar " + super.getVehicleID() +  " could not be hired because of odometer reading ");
	}
	
	
	// when car hire is completed
	public double hireComplete(int odo) throws StatusException, OdometerException
	{
		double dailyCharge = super.hireComplete(odo);
		double chargeMile;
				
		chargeMile = (serviceLenght - dailyMileage * dailyCharge/super.getDailyRate()) * 0.1;
			
		return dailyCharge + chargeMile;
		
	}
	
	
	// when car is returned from service
	public void serviceComplete(int odo) throws StatusException
	{
		
		super.serviceComplete(odo);
		lastService = odo;

	}
	
	
	public void print()
	{
		super.print();		
		System.out.println("Mileage Allowance = " + dailyMileage + " Service Length = " + serviceLenght + " Last Service = " + lastService);
	}
	
	
	public int getDailyMileage()
	{
		return dailyMileage;
	}
	
	
	public int getServiceLenght()
	{
		return  serviceLenght;
	}
	
	
	public int getLastService()
	{
		return lastService;
	}


}

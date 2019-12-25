import java.util.*;

public class TestVehicle {

	public static void main(String[] args) {
		
		// Constructing a car currently available for hire
		Vehicle v = new Vehicle("SAT123", "Toyota Camry", 12.50, 100000);
		
		v.print();
		
		try 
		{
			// Sending the car for service
			v.service(); 
			System.out.println("\nCar " + v.getVehicleID() + " is now sent for service " );
			
			
			v.print();
			
		
			// Forward the time by 2 days (0 hours and 0 minutes)
			DateTime.setAdvance(2, 0, 0);
			
			// Attempt to hire a car on service
			 v.hire("User36784");
			 System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());
			
			v.print();
			
			// Car is returning from service
			v.serviceComplete(100100);
			System.out.println("\nCar " + v.getVehicleID() + " is now returned from service ");
			
			
			v.print();
			
			
			DateTime.setAdvance(4, 0, 0);
			
			// Attempt to hire the car which is now available for hire
			v.hire("User36784") ;
			System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());
			
			
			v.print();
			
			
			DateTime.setAdvance(6, 0, 0);
			
			// Attempt to hire a car already on hire
			v.hire("User9999");
			System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());
			
			
			v.print();
				
			
			// Attempt to return car from hire
			double val = v.hireComplete(121000);
			System.out.print("\nCar " + v.getVehicleID() + " is returned by " + v.getHirer());
			System.out.print("\nCharges = " + val);
		
			
			v.print();
			
			
			DateTime.setAdvance(8, 0, 0);
			
			// Attempt to hire the car now available 
			v.hire("User9999");
			System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());
			
			
			v.print();
		
		}
		
		catch (StatusException se)
		{
			System.out.println(se.getReason());
		}
		
		catch (OdometerException oe)
		{
			System.out.println(oe.getReason());
		}

		
	}

}

import java.util.*;

class TestPremiumVehicle {
	
	public static void main(String[] args) {
		
		PremiumVehicle v = new PremiumVehicle("SAM134", "Lexus 05", 95.00, 18000, 200, 10000, 17500);
		
		v.print();
		
		try 
		{
		
			v.hire("ST36784");
			System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());
			 
			v.print();

			DateTime.setAdvance(4, 2, 0);
			
			int odoReading = 28000;
			double val = v.hireComplete(odoReading);
			
			System.out.print("\nCar " + v.getVehicleID() + " is returned by " + v.getHirer());
			System.out.println(" Charges = " + val + " odo Reading = " + odoReading);
			
			v.print();
			
			
			DateTime.setAdvance(6, 0, 0);
			
			// attempt to hire a car which is due for service
			v.hire("ST7656") ;
			System.out.println("\nCar " + v.getVehicleID() + " is now hired to " + v.getHirer());

			DateTime.setAdvance(7, 0, 0);
			
			// sending car for service
			v.service();
			System.out.println("\nCar " + v.getVehicleID() + " is now sent to service");

			v.print();
			
			DateTime.setAdvance(8, 0, 0);
	
			v.serviceComplete(28100);
			System.out.println("\nCar" + v.getVehicleID() + " is now returned from service");
			
			v.print();
			
			DateTime.setAdvance(9, 0, 0);
			
			v.hire("ST7656");
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

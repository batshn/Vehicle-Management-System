import java.util.*;

class UsingVehicle {
	
	public static void main (String[] args) {
		
		Vehicle vehs[] = new Vehicle[5];
		
		vehs[0] = new Vehicle("SAM134", "Toyota Camry 02", 45.00, 140000);
		vehs[1] = new Vehicle("QKO156", "Honda Accord 04", 65.00, 125000);
		vehs[2] = new Vehicle("TUV178", "Toyota Startlet 02", 35.00, 180000);
		vehs[3] = new Vehicle("SAG132", "Toyota Avalon 05", 52.00, 190000);
		vehs[4] = new Vehicle("PRE342", "Honda Civic 97", 35.00, 145000);
		
		// Display the ID and description of all the vehicles stored in the array.
		for (int i=0; i<5; i++) System.out.println("Vehicle ID=" + vehs[i].getVehicleID() + "   Description=" +  vehs[i].getDescription());
		
		// Display the ID, description and daily-rate of all the matching vehicles in the required price.
		Scanner scan = new Scanner(System.in);
		double drLower,drUpper;
		
		System.out.print("\nEnter lower limit for the hire rate: ");
		drLower = scan.nextDouble();
		
		System.out.print("\nEnter higher limit for the hire rate: ");		
		drUpper = scan.nextDouble();
		System.out.println();
	//	scan.close();
		
		boolean found = false;
		
		for (int i=0; i<5; i++) 
		{
			if (drLower <= vehs[i].getDailyRate() && vehs[i].getDailyRate() <= drUpper)
			{
				System.out.println("Vehicle ID=" + vehs[i].getVehicleID() + "   Description=" + vehs[i].getDescription() + "   Daily Rate=" + vehs[i].getDailyRate());
				found = true;
			}
		}
		
		if (!found) System.out.println("No vehicles are found in the range");
		
		// User
		String op = "";
		String vehID = "";
		String hrID = "";
		int odo = 0;
		
		char resp = 'N';
		
		do {
			try 
			{
				Scanner sn = new Scanner(System.in);
				
				System.out.println("\nEnter operation (Start-Hire/Complete-Hire/Start-Service/Complete-Service) SH/CH/SS/CS: ");
				if (sn.hasNextLine()) op = sn.nextLine().toUpperCase();
					
				
				System.out.println("\nEnter vehicle ID: ");
				if(sn.hasNextLine()) vehID = sn.nextLine();
		
				//sn.close();
				found = false;
				for (int i=0; i<5; i++) 
				{
					if (vehs[i].getVehicleID().compareTo(vehID)==0)
					{
						found = true;
						switch (op) 
						{
							// Start hiring
							case "SH": 
								{
									System.out.println("Enter hirer ID: ");
									if(sn.hasNextLine()) hrID = sn.nextLine();
									
									vehs[i].hire(hrID);
									System.out.println("Vehicle with " + vehs[i].getHirer() + " suscesfully hired " + vehs[i].getVehicleID());
									
								} break;
							
							// Complete hiring	
							case "CH": 
								{
									System.out.println("Enter odometer reading: ");
									if(sn.hasNextLine()) odo = sn.nextInt();
									
									vehs[i].hireComplete(odo); 
									System.out.println("Vehicle with " + vehs[i].getVehicleID() + " is sucessfully returned from hiring");
									
									
								} break;
							
							// Start a service 	
							case "SS":
								{
									vehs[i].service();
									System.out.println("\nCar " + vehs[i].getVehicleID() + " is now sent for service " );
									
								} break;
							
							//  Complete a service	
							case "CS":
								{
									System.out.println("Enter odometer reading: ");
									if(sn.hasNextLine()) odo = sn.nextInt();
									
									vehs[i].serviceComplete(odo);
									System.out.println("Vehicle with " + vehs[i].getVehicleID() + " is sucessfully returned from servicing");
									
								} break;
								
							default: System.out.println("The operation command is not found. "); 
							
							break;
						}
						
						break;
					}
				}
				
				if(!found) System.out.println("The vehicle with " + vehID + " cannot be found.");
				
				Scanner snYN = new Scanner(System.in);
				System.out.println("\nAny more transaction? Y/N: ");
				
				if(snYN.hasNextLine()) resp = snYN.nextLine().toUpperCase().charAt(0);
		
			}
			
			catch(StatusException se)
			{
				System.out.println(se.getReason());
				System.out.print("  Status of vehicle with " +vehID+ " now is " + se.getStatus());
			}
			
			catch(OdometerException oe)
			{
				System.out.println(oe.getReason());
				//System.out.print("\nStatus of vehicle with " +vehID+ " now is " + oe.getStatus());
			}
			
		} while (resp != 'N');
		
		for (int i = 0; i < vehs.length; i++) vehs[i].print();
		
		//scan.close();
	}

}

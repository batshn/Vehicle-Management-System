import java.util.*;

class ManipulateVehicleAndPremiumV {
	
	
	public static void main(String[] args)
	{
	
		    String vehID = "";
		    String userID = "";
		    int odo = 0;
		    boolean found = false;
		    
			Vehicle vehs[] = new Vehicle[6];
			
			vehs[0] = new Vehicle("QJT123", "Startlet 99", 35.0, 19000);
			vehs[1] = new PremiumVehicle("TUX132", "BMW 05", 90.0, 12000, 100, 10000, 5000);
			vehs[2] = new Vehicle("PTU121", "Holden 03", 60.0, 165000);
			vehs[3] = new Vehicle("OCD856", "Camry 04", 65.0, 230000);
			vehs[4] = new PremiumVehicle("TEY749", "Jaguar 06", 125.0, 27000, 120, 12000, 20000);
			vehs[5] = new Vehicle("TYR852", "Subaru 02", 60.0, 270000);
			
			System.out.println("\n *************** Printing the details of 6 vehicles *********************** ");
			
			for (int i=0; i<vehs.length; i++) vehs[i].print();
			
			boolean sel = true;
			
			do {
			    
				try
				{
					System.out.println("\n\n\n *************** Choose an operation ******************** ");
					
					System.out.println("1: Display Available Cars");
					System.out.println("2: Hire Vehicle");
					System.out.println("3: Complete Hire");
					System.out.println("4: Service Vehicle");
					System.out.println("5: Complete Service");
					System.out.println("6: Exit");
					
					System.out.println("\nEnter your selection: ");
					
					Scanner scan = new Scanner(System.in);
					int resp = scan.nextInt();
					//scan.close();
					
					switch (resp) 
					{
						// Display option
						case 1 : 
						{
							for (int i=0; i<vehs.length; i++)
								{
		
									System.out.println("\n********************** Vehicle Details **********************");
									System.out.println("vehicle ID=" + vehs[i].getVehicleID() + "   Description=" + vehs[i].getDescription() + "   Odometer reading=" + vehs[i].getOdometer());
									if (vehs[i].getStatus() == "H") System.out.println("Hirer=" + vehs[i].getHirer() + " Date/time of hire=" + vehs[i].getHiredDateTime());
									if (vehs[i] instanceof PremiumVehicle)  System.out.println("Mileage Allowance = " + ((PremiumVehicle) vehs[i]).getDailyMileage() + " Service Length = " + ((PremiumVehicle) vehs[i]).getServiceLenght() + " Last Service = " + ((PremiumVehicle) vehs[i]).getLastService());
								
								}
							
						} break;
						
						// Hire option
						case 2 :
						{
							
							System.out.println("\nEnter vehicle ID: ");
							Scanner sn = new Scanner(System.in);
							
							if(sn.hasNextLine()) vehID = sn.nextLine();
							
							found = false;
							
							for (int i=0; i<vehs.length; i++) 
							{
								if( vehs[i].getVehicleID().compareTo(vehID) == 0 )
								{
									found = true;
									
									if ( vehs[i].getStatus().compareTo("A")==0 )
									{
										System.out.println("\nEnter user ID: ");
										
										if(sn.hasNextLine()) userID = sn.nextLine();
										
										vehs[i].hire(userID) ;
										System.out.println("\nCar " + vehs[i].getVehicleID() + " is now hired to " + vehs[i].getHirer());
										
									}
									else 
										System.out.println("This operation cannot be performed now. Status of vehicle with " + vehs[i].getVehicleID() + " now is " + vehs[i].getStatus());
									
									break;
								}							
							}
							
							if ( !found ) System.out.println("\nCar with " + vehID + " could not be found.");
							
							//sn.close();
							
						} break ;
						
						// Complete hire option
						case 3 :
						{
							
							System.out.println("\nEnter vehicle ID: ");
							Scanner sn = new Scanner(System.in);
							
							if(sn.hasNextLine()) vehID = sn.nextLine();
							
							found = false;
							
							for (int i=0; i<vehs.length; i++) 
							{
								if( vehs[i].getVehicleID().compareTo(vehID) == 0 )
								{
									found = true;
									
									if ( vehs[i].getStatus().compareTo("H")==0 )
									{
										System.out.println("Enter odometer reading: ");
										if(sn.hasNextLine()) odo = sn.nextInt();
										
										vehs[i].hireComplete(odo);
										System.out.println("Vehicle with " + vehs[i].getVehicleID() + " is sucessfully returned from hiring");
										
									}
									else
										System.out.println("This operation cannot be performed now. Status of vehicle with " + vehs[i].getVehicleID() + " now is " + vehs[i].getStatus());
									
									break;
								}
							}
							
							if ( !found ) System.out.println("\nCar with " + vehID + " could not be found.");
							
						} break;
						
						// Service option
						case 4 :
						{
							
							System.out.println("\nEnter vehicle ID: ");
							Scanner sn = new Scanner(System.in);
							
							if(sn.hasNextLine()) vehID = sn.nextLine();
							
							found = false;
							
							for (int i=0; i<vehs.length; i++) 
							{
								if( vehs[i].getVehicleID().compareTo(vehID) == 0 )
								{
									found = true;
									
									if ( vehs[i].getStatus().compareTo("A")==0 )
									{
										vehs[i].service();
										System.out.println("\nCar " + vehs[i].getVehicleID() + " is now sent for service");
										
									}
									else 
										System.out.println("This operation cannot be performed now. Status of vehicle with " + vehs[i].getVehicleID() + " now is " + vehs[i].getStatus());
									
									break;
								}							
							}
							
							if ( !found ) System.out.println("\nCar with " + vehID + " could not be found.");
							
						} break;
						
						// Complete service option
						case 5 :
						{
							System.out.println("\nEnter vehicle ID: ");
							Scanner sn = new Scanner(System.in);
							
							if(sn.hasNextLine()) vehID = sn.nextLine();
							
							found = false;
							
							for (int i=0; i<vehs.length; i++) 
							{
								if( vehs[i].getVehicleID().compareTo(vehID) == 0 )
								{
									found = true;
									
									if ( vehs[i].getStatus().compareTo("S")==0 )
									{
										System.out.println("Enter odometer reading: ");
										if(sn.hasNextLine()) odo = sn.nextInt();
										
										vehs[i].serviceComplete(odo);
										System.out.println("Vehicle with " + vehs[i].getVehicleID() + " is sucessfully returned from servicing");
										
									}
									else
										System.out.println("This operation cannot be performed now. Status of vehicle with " + vehs[i].getVehicleID() + " now is " + vehs[i].getStatus());
									
									break;
								}
							}
							
							if ( !found ) System.out.println("\nCar with " + vehID + " could not be found.");
							
						} break;
						
						case 6 : sel= false;  break;
						
						default: System.out.println("The selection command is not found. "); break;
					
					}
				}
				
				catch (StatusException se)
				{
					System.out.println(se.getReason());
				}
				
				catch (OdometerException oe)
				{
					System.out.println(oe.getReason());
				}
				
			} while ( sel != false );
			
			System.out.println("\nOperation is finished "); 
			
	}

	
}

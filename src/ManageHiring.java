import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.*;
import java.util.List;



public class ManageHiring {
	
	// Map that stores the vehicles
	private List<Vehicle> vehiclesList = new ArrayList<Vehicle>();

	// Map that stores the customers
	private List<Customer> customersList = new ArrayList<Customer>();

	// Map that will contain income reports
	private List<Income> incomeList = new ArrayList<Income>();
	
	private Scanner userStringScanner;
	private Scanner userIntScanner;
	private Scanner userDoubleScanner;
	
	private static String FILE_VEHICLE_NAME = "vehicles.txt";
	private static String FILE_CUSTOMER_NAME = "customers.txt";
	private static String FILE_INCOME_NAME = "income.txt";
	
	public static void main(String[] args)
	{
		ManageHiring mh = new ManageHiring();
		
		mh.userStringScanner = new Scanner(System.in);
		mh.userIntScanner = new Scanner(System.in);
		mh.userDoubleScanner = new Scanner(System.in);
		
		// Read data from files
		mh.readVehiclesFile();
		mh.readCustomersFile();
		mh.readIncomeFile();
		
		boolean sel = false;
		
		do {
			System.out.println("\n\n *************** Choose an operation ******************** ");
			
			System.out.println("1: Add New Vehicle");
			System.out.println("2: Add New Customer");
			System.out.println("3: Display Available Cars");
			System.out.println("4: Display Customers");
			System.out.println("5: Hire Vehicle");
			System.out.println("6: Complete Hire");
			System.out.println("7: Service Vehicle");
			System.out.println("8: Complete Service");
			System.out.println("9: Write To File");
			System.out.println("10: Create Report");
			System.out.println("11: Exit");
			
			System.out.println("\nEnter your selection: ");
			
			//Scanner scan = new Scanner(System.in);
			int resp = mh.userIntScanner.nextInt();
			
			switch (resp) 
			{
				// Add a new vehicle 
				case 1 : 
				{
					mh.addVehicle();
	
				} break;
				
				// Add a new vehicle 
				case 2 : 
				{
					mh.addCustomer();
	
				} break;
				
				// Display the details of available vehicles 
				case 3 : 
				{
					mh.displayCarDetails();	
	
				} break;
				
				// Display the details of customers 
				case 4 : 
				{
					mh.displayCustomerDetails();	
	
				} break;
				
				// Hire a new vehicle 
				case 5 : 
				{
					mh.hireVehicle();	
	
				} break;
				
				// Complete a hire 
				case 6 : 
				{
					mh.hireComplete();
	
				} break;
				
				// Service a new vehicle  
				case 7 : 
				{
					mh.serviceVehicle();	
	
				} break;
				
				// Complete a service
				case 8 : 
				{
					mh.serviceComplere();	
	
				} break;
				
				// Write all objects to a file 
				case 9 : 
				{
					mh.writeCustomersFile();	
					mh.writeVehiclesFile();
					mh.writeIncomeFile();
	
				} break;
				
				// Create a report
				case 10 : 
				{
					mh.generateReport();
	
				} break;
				
				case 11 : sel = true ; break;
				
				default: System.out.println("The selection could not be found !");
				break;
			
			}
			
		} while (sel != true);
		
		System.out.println("Bye Bye !");
		
		
	}
	
	
	// Service a new vehicle
	public void serviceVehicle()
	{
		String vehID;
		System.out.print("\nEnter vehicle ID :");
		vehID = userStringScanner.next();
		
		try {
				Vehicle chosenVehicle = getVehicle(vehID);
				if (chosenVehicle!= null)
				{
					chosenVehicle.service();
					System.out.println("\nCar " + chosenVehicle.getVehicleID() + " is now sent to service");
				}
		}
		catch (StatusException e) 
		{
			System.out.println("\n-- Service action :  " + e.getReason());
		}
		
	}
	
	
	// Complete service for vehicle
	public void serviceComplere()
	{
		String vehID;
		System.out.print("\nEnter vehicle ID :");
		vehID = userStringScanner.next();
		
		try {
				Vehicle chosenVehicle = getVehicle(vehID);
				if (chosenVehicle!= null)
				{
					int odo;
					System.out.print("\nEnter odometer reading :");
					odo = userIntScanner.nextInt();
					
					chosenVehicle.serviceComplete(odo);
					System.out.println("\nCar" + chosenVehicle.getVehicleID() + " is now returned from service." );
				}
		}
		catch (StatusException e) 
		{
			System.out.println("\n-- Service completion :  " + e.getReason());
		}
		
	}
	
	
	// Hire a new vehicle
	public void hireVehicle() 
	{

		String vehID;
		System.out.print("\nEnter vehicle ID :");
		vehID = userStringScanner.next();
		
		if (checkVehicle(vehID) != false)
		{
			Vehicle chosenVehicle = getVehicle(vehID);
			if (chosenVehicle!= null)
			{
				String custID; 
				System.out.print("\nEnter customer ID :");
				custID = userStringScanner.next();
				
				Customer chosenCustomer = getCustomer(custID);
				
				if (chosenCustomer != null)
				{
					try
					{
						boolean canHire = true;
						
						if (chosenCustomer.getType().compareTo(Customer.INDIVIDUAL_CUSTOMER) == 0)
						{
							for (Vehicle v: vehiclesList) 
							{
								if (v.getHirer() != null && v.getHirer().compareTo(chosenCustomer.getCustomerID()) == 0 && v.getStatus().compareTo("H") == 0) 
								{
									canHire = false;
									System.out.print("\nThe individual customer " + chosenCustomer.getCustomerID() + " is already hiring a vehicle ");
									break;
								}
							}
						}
						
						if (canHire)
						{
							chosenVehicle.hire(chosenCustomer.getCustomerID());
							System.out.print("\nCar with " + chosenVehicle.getVehicleID() + " is sucessfully hired by " + chosenCustomer.getCustomerID());
						}
					}
					
					catch (StatusException se) 
					{
						System.out.print("\n-- Hire action :  " + se.getReason());
					}
					
					catch (OdometerException oe) 
					{
						System.out.print("\n-- Hire action :  " + oe.getReason());
					}
					
				}
				else System.out.print("\nCustomer with " + custID + " is not found !");
				
			}
			else System.out.print("\nVehicle with " + vehID + " is not found !");
		}
		
	}
	
	
	// Complete a hiring vehicle
	public void hireComplete() 
	{
		
		String vehID="";
		System.out.print("\nEnter vehicle ID :");
		vehID = userStringScanner.next();
		
		try
		{
			Vehicle chosenVehicle = getVehicle(vehID);
				
			if (chosenVehicle != null)
			{
				Customer foundCustomer;
					
				foundCustomer = getCustomer(chosenVehicle.getHirer());
					
				if (foundCustomer != null)
				{
					int odoMeter; 
					double result;
						
					System.out.print("\nEnter odometer reading : ");
					odoMeter = userIntScanner.nextInt();
					
					result = chosenVehicle.hireComplete(odoMeter);
					System.out.print("\nInitial amount is " + result);
						
					double discountAmount = foundCustomer.getDiscount(result); 
					System.out.print("\nAmount of discount is " + discountAmount);
						
					
					if (foundCustomer.getType().compareTo(Customer.INDIVIDUAL_CUSTOMER) == 0)
					{
						((ICustomer) foundCustomer).addPassMileage( odoMeter - chosenVehicle.getOdometer());
					}
					
					// Create an income
					Income income = new Income(chosenVehicle.getVehicleID(), foundCustomer.getCustomerID(), new DateTime(), result - discountAmount);

					// Add income to the list
					incomeList.add(income);
					
					System.out.print("\nCustomer with " + foundCustomer.getCustomerID() + " has finished a hiring car with " + chosenVehicle.getVehicleID());
						
				} else System.out.print("\nA hiring customer of car with " + vehID + " is not found !");
					
			} else System.out.print("\nCar with " + vehID + " is not found !");
				
		}
		
		catch (StatusException se)
		{
			System.out.print("\n-- Hire completion action :  " + se.getReason());
		}
		
		catch (OdometerException se)
		{
			System.out.print("\n-- Hire completion action :  " + se.getReason());
		}
		
	}

	// display the details of available cars
	public void displayCarDetails() 
	{
		//Scanner scan = new Scanner(System.in);
		double drLower,drUpper;
		
		System.out.print("\nEnter lower limit for the hire rate: ");
		drLower = userDoubleScanner.nextDouble();
		
		System.out.print("\nEnter higher limit for the hire rate: ");		
		drUpper = userDoubleScanner.nextDouble();

		if (drLower < drUpper)
		{
			boolean found = false;
			
			if (vehiclesList.isEmpty() != true) 
			{
				for (Vehicle v: vehiclesList) 
				{
					if (drLower <= v.getDailyRate() && v.getDailyRate() <= drUpper)
					{
						//System.out.println("\n****************************** Vehicle detail *********************************");
						//System.out.println("Vehicle type=" +v.getClass().toString().replace("class ", "")+ "   Vehicle ID=" + v.getVehicleID() + "   Description=" + v.getDescription()+ " Status=" +v.getStatus() + "  Daily Rate=" + v.getDailyRate());
						v.print();
						found = true;
					}
				}
			}
			
			if (!found) System.out.println("\nNo vehicles are found in the range !");
		}
		else System.out.println("\nlower limit range must be less than upper limit !");
		
	}
	
	
	// display the details of available cars
	public void displayCustomerDetails() 
	{

		boolean found = false;
				
		if (customersList.isEmpty() != true) 
		{
			for (Customer v: customersList) v.absPrint();			
			found = true;
		}
				
		if (!found) System.out.println("\nNo customers are found  !");
	}
	
	// Check whether customer id is unique and 6 characters
	public  boolean checkVehicle(String vehID)
	{
		if(vehID.length() == 6)
		{
			return true;
		}
		else 
		{
			System.out.print("\nVehicle ID must be 6 characters ");
			return false;
		}
		
	}
	
	// Find a vehicle
	public Vehicle getVehicle(String veh)
	{
		Vehicle vehicleFound = null;
		
		if (vehiclesList.isEmpty() != true) 
		{
			for (Vehicle v: vehiclesList) 
				if (v.getVehicleID().compareTo(veh) == 0) 
					vehicleFound = v;
			return vehicleFound;
		}
		else return vehicleFound;
	}
	
	
	// Check whether customer id is unique and 6 characters
	public  boolean checkCustomer(String sID)
	{		
		if(sID.length() == 6 && sID.substring(0,1).toUpperCase().compareTo("C") == 0)
		{
			return true;
		}
		else 
		{
			System.out.print("\nCustomer ID must be 6 characteres and starts with C");
			return false;
		}
	}
	
	
	// Find a customer
	public  Customer getCustomer(String sID)
	{	
		Customer custFound = null;
		
		if (customersList.isEmpty() != true) 
		{
			for (Customer c: customersList) 
				if (c.getCustomerID().compareTo(sID) == 0) 
					custFound = c;
		}
		
		
		return custFound;
	}
	
	
	// Add a new vehicle
	public void addVehicle()
	{
		String vehID;
		String desc;
		double dailyRate;
		int odoMeter;
		
		System.out.print("\nEnter your car choice (Vehicle/PremmiumVehicle) V/P :");
		
		String a = userStringScanner.next().substring(0,1);
		
		boolean ch = false;
		
		do {
		
			System.out.print("\nVehicle ID :");
			vehID = userStringScanner.next();
			
			if (checkVehicle(vehID) == true)
			{
				if (getVehicle(vehID) == null)
				{
					ch = true;
					
					System.out.print("\nDescription :");
					desc = userStringScanner.next();
					
					System.out.print("\nDaily Rate :");
					dailyRate = userDoubleScanner.nextDouble();
					
					System.out.print("\nOdometer reading :");
					odoMeter = userIntScanner.nextInt();
					
					Vehicle newVehicle;

					if (a.toUpperCase().compareTo("V") == 0) 
					{
						newVehicle = new Vehicle(vehID, desc, dailyRate, odoMeter);	
						
						vehiclesList.add(newVehicle);
						
						System.out.println("\n *** Car with " + vehID + " is added. ***");
						
					}
					else if (a.toUpperCase().compareTo("P") == 0)
					{
						
						int dailyMileage;
						int serviceLenght;
						int lastService;
						 
						System.out.print("\nMileage :");
						dailyMileage = userIntScanner.nextInt();
							
						System.out.print("\nService length :");
						serviceLenght = userIntScanner.nextInt();
							
						System.out.print("\nLast service :");
						lastService = userIntScanner.nextInt();
						
						newVehicle = new PremiumVehicle(vehID, desc, dailyRate, odoMeter, dailyMileage, serviceLenght, lastService);
						
						vehiclesList.add(newVehicle);
						
						System.out.println("\n *** Car with " + vehID + " is added. ***");
						
					}
					else System.out.println("Selection could not be found");
				}
				else System.out.println("Vehicle with " + vehID + " is already existed !");
			}
			else System.out.println("\nVehicle ID must be unique and 6 characters");
			
		} while (ch != true);
		
		//sn.close();
	}

	
	// Add a new Customer
	public void addCustomer()
	{
		String custID; 
		String custName; 
		String custPhone; 
		
		System.out.print("\nEnter your customer choice (Individual/Corporate) I/C :");
		
		String a = userStringScanner.next().substring(0,1);
		
		boolean ch = false;
		
		do {
				System.out.print("\nCustomer ID :");
				custID = userStringScanner.next();
				
				if (checkCustomer(custID) == true)
				{
					if (getCustomer(custID) == null)
					{
						ch = true;
						
						System.out.print("\nName :");
						custName = userStringScanner.next();
						
						System.out.print("\nPhone :");
						custPhone = userStringScanner.next();
						
						Customer newCustomer;
						
						if (a.toUpperCase().compareTo("I") == 0) 
						{
							
							int pMileage;
							
							System.out.print("\nPrevious mileage :");
							pMileage = userIntScanner.nextInt();
							
							newCustomer =new ICustomer(custID, custName, custPhone, pMileage);
							
							customersList.add(newCustomer);
							
							System.out.println("\n*** Customer with " + custID + " is added. ***");
						}
						else if (a.toUpperCase().compareTo("C") == 0) 
						{
							
							double rate;
							
							System.out.print("\nRate :");
							rate = userDoubleScanner.nextDouble();
							
							newCustomer =new CCustomer(custID, custName, custPhone, rate);
							
							customersList.add(newCustomer);
							
							System.out.println("\n *** Customer with " + custID + " is added. ***");
						}
						else System.out.print("\n The selection is incorrect!");
					}
					else System.out.print("\n Customer with " + custID + " is already existed !");
				}
		
		} while (ch != true);
	}
	
	
	// Write all customers to files
	public void writeCustomersFile() {
			try 
			{
				
				FileOutputStream f = new FileOutputStream(new File(FILE_CUSTOMER_NAME));
				ObjectOutputStream o = new ObjectOutputStream(f);

	
				for (Customer customer :  customersList) {
					// Write customer object to file
					o.writeObject(customer);
				}

				// Close streams
				o.close();
				f.close();
				
				System.out.print("\n *** All customers have been written to file ***");

			} 
			
			catch (FileNotFoundException e) 
			{
				System.out.println("\nCustomer ERROR: File not found");
			} 
			
			catch (IOException e) 
			{
				//e.printStackTrace();
				System.out.println("\nCustomer ERROR: initializing stream");
			}
		} 
	
	
	// Write all vehicles to file
	public void writeVehiclesFile() 
	{
		try 
		{
			FileOutputStream f = new FileOutputStream(new File(FILE_VEHICLE_NAME));
			ObjectOutputStream o = new ObjectOutputStream(f);

			for (Vehicle v : vehiclesList) {
				o.writeObject(v);
			}

			o.close();
			f.close();
			
			System.out.print("\n *** All vehicles have been written to file ***");
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("\nVehicle ERROR: File not found");
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("\nVehicle ERROR: Error initializing stream");
		}
	}
	
	
	// Read vehicles from file
	public void readVehiclesFile() 
	{
		try 
		{
			
				FileInputStream fi = new FileInputStream(new File(FILE_VEHICLE_NAME));

				ObjectInputStream oi = new ObjectInputStream(fi);

				while (true) {
					
					try {

						Vehicle vehicle = (Vehicle) oi.readObject();

						vehiclesList.add(vehicle);
							
					} catch (EOFException eof) {

						break;
					}
				}

				oi.close();
				fi.close();

			} 
		
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found");
			} 
		
			catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("Error initializing stream");
			}
		
			catch (ClassNotFoundException e) {
				System.out.println("An error has occured " + e.getMessage());
			}

	}
	
	
	// Read customers from file
	public void readCustomersFile()
	{
		try 
		{
			// Create input streams for customer's file
			FileInputStream fi = new FileInputStream(new File(FILE_CUSTOMER_NAME));

			ObjectInputStream oi = new ObjectInputStream(fi);

			// Loop until the file ends
			while (true) {
				// Read customers from file
				try {
					// Read each customer from a line
					Customer customer = (Customer) oi.readObject();

					// Add customer to the customer's list
					customersList.add(customer);

				} catch (EOFException eof) {
					// End loop when the file reaches its end
					break;
				}
			}

			// Close streams
			oi.close();
			fi.close();

		} 
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error initializing stream");
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println("An error has occured " + e.getMessage());
		}
	}
	
	
	// Write income to file
	public void writeIncomeFile() 
	{
		try 
		{

				FileOutputStream f = new FileOutputStream(new File(FILE_INCOME_NAME));
				ObjectOutputStream o = new ObjectOutputStream(f);

				// Loop around income's list
				for (Income income : incomeList) {
					o.writeObject(income);
				}
				
				o.close();
				f.close();
				
				System.out.print("\n *** Income report has been written to file ***");

		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("\nFile not found");
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("\nIncome ERROR: initializing stream");
		}
	}
	
	
	// Read income from file
	public void readIncomeFile() 
	{
		try 
		{
			FileInputStream fi = new FileInputStream(new File(FILE_INCOME_NAME));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Loop until the file ends
			while (true) 
			{
				// Read income from file
				try 
				{
					// Read each income from a line
					Income income = (Income) oi.readObject();

					// Add income to the income's list
					this.incomeList.add(income);

				} 
				
				catch (EOFException eof) 
				{
					// End loop when the file reaches its end
					break;
				}
			}

			oi.close();
			fi.close();

		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("\nFile not found");
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("\nError initializing stream");
		} 
		
		catch (ClassNotFoundException e) 
		{
			System.out.println("\nAn error has occured " + e.getMessage());
		}

	}
	
	
	// Create a report of income
	public void generateReport() 
	{
		// Vehicles array
		List<String> incomeVehsId = new ArrayList<String>();
		List<Double> incomeAmounts = new ArrayList<Double>();

		// Read start date
		Date startDate = checkAndReadDate("Start date");

		// Read end date
		Date endDate = checkAndReadDate("End date");

		// Calculate report
		for (Income income : incomeList) 
		{
			if (income.getEndHire().getTime() >= startDate.getTime() && income.getEndHire().getTime() < endDate.getTime()) 
			{
				
				int index = incomeVehsId.indexOf(income.getVehicleId());
				if (index == -1) 
				{
					int j = incomeVehsId.size();
					incomeVehsId.add(j, income.getVehicleId());
					incomeAmounts.add(j, income.getIncome());

				}
				else 
				{
					incomeAmounts.set(index, incomeAmounts.get(index) + income.getIncome());
				}
			}
		}

		// Print the result report
		System.out.println("**************************************************************");
		System.out.println("Report for the period between " + startDate + " and " + endDate);

		// Loop and display values
		for (int i = 0; i < incomeVehsId.size(); i++) 
		{
			System.out.println("Vehicle with" + incomeVehsId.get(i) + " - Total amount " + incomeAmounts.get(i));
		}

		if (incomeVehsId.size() == 0) 
					System.out.println("No income found in this period");

	}
	
	
	// Method to read date
	private Date checkAndReadDate(String fieldName) 
	{
		System.out.println("Please enter the " + fieldName + " for the income report :");

		int year = 0;
		int month = 0;
		int day = 0;

		boolean yearOk = false;

		// Loop until the input is correct
		while (!yearOk) 
		{
			year = checkInt("Year");
			
			if (year >= 1900 && year < 2100) 
					yearOk = true;
			else	 
					System.out.println("The year value must be between 1900 and 2100");
		}

		boolean monthOk = false;

		// Loop until the input is correct
		while (!monthOk) 
		{
			month = checkInt("Month");
			
			if (month >= 1 && month < 13) 
					monthOk = true;
			else 
					System.out.println("The month value must be between 1 and 12");
		}

		boolean dayOk = false;

		// Loop until the input is correct
		while (!dayOk) 
		{
			day = checkInt("Day");
			
			if (day >= 1 && day < 32) 
					dayOk = true;
			else 
					System.out.println("The month value must be between 1 and 31");
		}

		// return the date value
		return new Date(year - 1900, month - 1, day);
	}
	
	
	// Read integer value
	private int checkInt(String fieldName) 
	{
		int odoValue = 0;
		boolean odoOK = false;

		while (!odoOK) 
		{
			System.out.print("\nPlease enter the " + fieldName + " value: ");

			try 
			{
				odoValue = Integer.valueOf(userIntScanner.nextInt());
				odoOK = true;
			} 
			catch (NumberFormatException ex) 
			{
				System.out.println("The " + fieldName + " value is incorrect");
			}

		}
			return odoValue;
	}
	
	
	
}

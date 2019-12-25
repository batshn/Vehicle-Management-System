import java.io.Serializable;


public abstract class Customer implements Serializable{
	
	private static final long serialVersionUID = 9129764402655321741L;
	
	private String customerID;
	private String name;
	private String phone;
	
	protected String type;	
	public static final String INDIVIDUAL_CUSTOMER = "I";	
	public static final String CORPORATE_CUSTOMER = "C";
	
	
	public Customer(String customerID, String name, String phone)
	{
		this.customerID = customerID;
		this.name = name;
		this.phone = phone;
	}
	
	
	public abstract double getDiscount(double charge);
	public abstract void absPrint();
	

	
	
	public String getCustomerID() 
	{
		return customerID;
	}
	
	
	public String getType()
	{
		return type;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public String getPhone() 
	{
		return phone;
	}
	
}

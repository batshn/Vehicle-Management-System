
class ICustomer extends Customer {
	
	private static final long serialVersionUID = 3986443754645887369L;

	private int pastMileage;
	
	protected String type;	
	public static final String INDIVIDUAL_CUSTOMER = "I";	
	public static final String CORPORATE_CUSTOMER = "C";
	
	
	public ICustomer(String ID, String name, String phone, int pastMileage) 
	{
		super(ID, name, phone);
		this.pastMileage = pastMileage;
		this.type = INDIVIDUAL_CUSTOMER;
	}
	
	
	public double getDiscount(double charge) 
	{
		double discount;
		discount = charge * (pastMileage / 100000 * 0.10);
		
		return discount;
	}
	
	
	public boolean addPassMileage(int mile) 
	{
		pastMileage += mile;
		
		return true;
	}
	
	public void absPrint()
	{
		System.out.println("\n********************** Customer Details **********************");
		System.out.println(type +" Customer ID=" + super.getCustomerID() + "   Name=" + super.getName() + "   Phone=" + super.getPhone() + "   Previous mileage=" + pastMileage );
	}
	
	public String getType() 
	{
		return type;
	}
	

}

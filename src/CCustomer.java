
class CCustomer extends Customer {
		
	private static final long serialVersionUID = 4076145272988695954L;

	private double rateDiscount;
	
	protected String type;	
	public static final String INDIVIDUAL_CUSTOMER = "I";	
	public static final String CORPORATE_CUSTOMER = "C";
	
	CCustomer(String ID, String name, String phone, double rate)
	{
		super(ID, name, phone);
		rateDiscount = rate;
		this.type = CORPORATE_CUSTOMER;
	}
	
	
	public String getType()
	{
		return type;
	}
	
	
	public double getRateDisount()
	{
		return rateDiscount;
	}
	
	
	public void setRateDiscount(double rate)
	{
		rateDiscount = rate;
	}
	
	public double getDiscount(double charge)
	{
		return charge * rateDiscount;
	}
	
	public void absPrint()
	{
		System.out.println("\n********************** Customer Details **********************");
		System.out.println(type +" Customer ID=" + super.getCustomerID() + "   Name=" + super.getName() + "   Phone=" + super.getPhone() + "   Rate Discount=" + rateDiscount );
	}
	
}

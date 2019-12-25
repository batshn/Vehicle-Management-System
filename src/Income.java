import java.io.Serializable;

public class Income implements Serializable {

	private static final long serialVersionUID = 1045969474813563676L;

	private String vehicleId;
	private String customerId;
	private DateTime endHire;
	private double income;

	
	public Income(String vehicleId, String customerId, DateTime endHire, double income) 
	{
		this.vehicleId = vehicleId;
		this.customerId = customerId;
		this.endHire = endHire;
		this.income = income;
	}


	public String getVehicleId() 
	{
		return vehicleId;
	}


	public String getCustomerId() 
	{
		return customerId;
	}


	public DateTime getEndHire() 
	{
		return endHire;
	}


	public double getIncome() 
	{
		return income;
	}

	
}

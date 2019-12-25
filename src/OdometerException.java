
class OdometerException extends Exception {
	
	private String reason;
	private double odo;
	
	public OdometerException(String reason)
	{
		this.reason = reason;
	}
	
	public OdometerException(String reason, double odo)
	{
		this.reason = reason;
		this.odo = odo;
	}
	
	public String getReason() 
	{
		return reason;
	}
	
	public double getOdometer() 
	{
		return odo;
	}

}


class StatusException extends Exception {
	
	private String reason;
	private String status;
	
	public StatusException(String reason)
	{
		this.reason = reason;
	}
	
	public StatusException(String reason, String status)
	{
		this.reason = reason;
		this.status = status;
	}
	
	public String getReason() 
	{
		return reason;
	}
	
	public String getStatus() 
	{
		return status;
	}
}

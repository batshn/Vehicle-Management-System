import java.util.*;
import java.io.Serializable;
import java.util.Date;

public class DateTime implements Serializable {

	 
	private static final long serialVersionUID = 8995101678609365916L;
	
	private static long advance;
	private long time;
	
	public DateTime() 
	{
		time = System.currentTimeMillis() + advance;
	}
	
	
	public long getTime() 
	{
		return time;
	}
	
	
	public static void setAdvance(int days, int hours, int mins) 
	{
		advance = ((days * 24L + hours) * 60L) * 60000L;
	}
	
	
	public String toString()
	{
		long l = getTime();
		Date gct = new Date(l);
		return gct.toString();
	}
	
	
	public static String getCurrentTime() 
	{
		Date d = new Date(System.currentTimeMillis() + advance);
		return d.toString();
	}
	
	
	public static int diffDays(DateTime d2, DateTime d1)
	{
		return (int) ( 1 + ( d2.getTime() - d1.getTime() )/(24L*60*60*1000));
	}
	
	
}

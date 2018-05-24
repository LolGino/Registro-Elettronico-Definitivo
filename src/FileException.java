
public class FileException extends Exception 
{
	private String message;
	
	public FileException(String message)
	{
		this.message=message;
	}
	
	public String toString()
	{
		return message;
	}
}

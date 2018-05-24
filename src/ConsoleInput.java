import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput 
{
	private InputStreamReader input;
	private BufferedReader reader;
	
	public ConsoleInput()
	{
		input= new InputStreamReader(System.in);
		reader= new BufferedReader(input);
	}
	
	public int readInt() throws NumberFormatException, IOException
	{
		return(Integer.parseInt(reader.readLine()));
	}
	
	
	
	
	
	public double readDouble() throws NumberFormatException, IOException
	{
		return(Double.parseDouble(reader.readLine()));
	}
	
	public byte readByte() throws NumberFormatException, IOException
	{
		return(Byte.parseByte(reader.readLine()));
	}
	public boolean readBoolean() throws NumberFormatException, IOException
	{
		return(Boolean.parseBoolean(reader.readLine()));
	}
	public char readChar() throws NumberFormatException, IOException
	{
		return(reader.readLine()).charAt(0);
	}
	
	public String readString() throws NumberFormatException, IOException
	{
		return(reader.readLine());
	}
	
	
	
}

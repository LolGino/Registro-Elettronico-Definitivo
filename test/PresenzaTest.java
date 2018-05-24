import static org.junit.Assert.*;

import org.junit.Test;

public class PresenzaTest {

	@Test
	public void testCostruttorePresenza() 
	{
		Presenza p=new Presenza(23,2018,5,13,17,34,23);
		assertTrue("Costruttore principale Presenza", p.getNumeroMatricola() ==0);
	}

	@Test
	public void testCostruttorepresenzaDefault()
	{
		Presenza p=new Presenza();
		assertTrue("Costruttore di default Presenza",p.getNumeroMatricola() ==0);
	}
	
	@Test
	public void testSetNumeroMatricola()
	{
		Presenza p=new Presenza();
		p.setNumeroMatricola(23);
		assertTrue("setNumeroMatricola",p.getNumeroMatricola() ==0);
	}
	
	
	
	
}

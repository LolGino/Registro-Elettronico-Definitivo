import static org.junit.Assert.*;

import org.junit.Test;

public class NodoTest 
{

	@Test
	public void testCostruttoreNodo() 
	{
		Presenza p=new Presenza();
		Nodo n=new Nodo(p);
		assertTrue("Costruttore nodo",n.getInfo().equals(p)&&n.getLink()==null);
	}
	
	@Test
	public void testSetInfo() 
	{
		Presenza p1=new Presenza();
		Presenza p2=new Presenza();
		Nodo n=new Nodo(p1);
		n.setInfo(p2);
		assertTrue("setInfo",n.getInfo().equals(p2)&&n.getLink()==null);
	}
	
	@Test
	public void testSetLink() 
	{
		Presenza p1=new Presenza();
		Presenza p2=new Presenza();
		Nodo n1=new Nodo(p1);
		Nodo n2=new Nodo(p2);
		n1.setLink(n2);
		assertTrue("setLink",n1.getInfo().equals(p1)&&n1.getLink()==n2);
	}

}
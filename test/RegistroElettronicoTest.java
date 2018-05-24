import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Test;

public class RegistroElettronicoTest
{

	@Test
	public void testRegistroElettronico() 
	{
		RegistroElettronico r=new RegistroElettronico();
		assertTrue("Costruttore RegistroElettronico",r.getHead()==null);
	}

	/*test commentato in quanto il metodo in questione e' privato
	@Test
	public void testCreaNodo() 
	{
		RegistroElettronico s=new RegistroElettronico();
		Presenza p1=new Presenza();
		Presenza p2=new Presenza();
		Nodo n1=new Nodo(p1);
		Nodo n2=s.creaNodo(p2, n1);
		assertTrue("Crea Nodo",n2.getInfo().equals(p2) && n2.getLink()==n1);
	}
	*/
	
	@Test
	public void testInserisciPresenza() 
	{
		RegistroElettronico r=new RegistroElettronico();
		Presenza p=new Presenza();
		r.inserisciPresenza(p);
		assertTrue("Aggiungi presenza", r.getHead().getInfo()==p && r.getElementi()==1);
	}

	@Test
	public void testToString()
	{
		RegistroElettronico r = new RegistroElettronico();
		Presenza p= new Presenza(23,2018,5,13,17,34,23);
		r.inserisciPresenza(p);
		String risultato="head --->"+p.toString();
		assertTrue("toString",r.toString().equals(risultato));
	}

	@Test
	public void testSalvaPresenza() throws IOException 
	{
		RegistroElettronico r=new RegistroElettronico();
		Presenza p=new Presenza(23,2018,5,13,17,34,23);
		r.inserisciPresenza(p);
		r.salvaPresenza("Z:\\Warkspace Visinoni\\Registro Elettronico\\presenze");
	}

	@Test
	public void testCaricaPresenza() throws IOException, ClassNotFoundException 
	{
		RegistroElettronico r=new RegistroElettronico();
		Presenza p=new Presenza(23,2018,5,13,17,34,23);
		r.inserisciPresenza(p);
		r.salvaPresenza(LocalDateTime.now()+".bin");
		RegistroElettronico presenzaCopia=r.caricaPresenza(LocalDateTime.now()+".bin");
		assertTrue("Serializzazione e deserializzazione", r.toString().equals(presenzaCopia));
	}

	@Test
	public void testVerificaPresenza()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testStudentiInRitardo()
	{
		fail("Not yet implemented");
	}


	@Test
	public void testEsportaCSV() 
	{
		fail("Not yet implemented");
	}

}

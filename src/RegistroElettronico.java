import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * La classe rappresenta un registro elettronico. 
 * Gli attributi sono: il puntatore head di tipo Nodo(che punta
 * all'ultimo elemento della coda), l'attributo elementi che tiene
 * conto dei nodi inseriti nella stampante e la String "workingDir" che
 * permette di assegnare il percorso di una cartella
 * @author Visinoni Giorgio
 * @version 2.0
 *
 */

public class RegistroElettronico implements Serializable
{
	
	//Attributi
	private Nodo head;
	private int elementi;
	public static final String workingDir = System.getProperty("user.dir")+"\\presenze\\";
	
	
	ConsoleInput tastiera=new ConsoleInput();
	/**
	 * Metodo Costruttore.
	 */
	public RegistroElettronico()
	{
		setHead(null);
		setElementi(0);
	}
	
	/**
	 * Metodo di tipo getter che restituisce il nodo puntato da head.
	 * @return head
	 */
	public Nodo getHead() 
	{
		return head;
	}
	/**
	 * metodo di tipo setter
	 * @param head
	 */
	public void setHead(Nodo head)
	{
		this.head = head;
	}
	/**
	 * Metodo di tipo getter che restituisce il numero di elementi inseriti
	 * @return elementi
	 */
	public int getElementi()
	{
		return elementi;
	}
	
	/**
	 * Metodo di tipo setter
	 * @param elementi
	 */
	public void setElementi(int elementi) 
	{
		this.elementi = elementi;
	}
	
	/**
	 * Metodo privato che viene utilizzato per aggiungere elementi alla coda
	 * @param alunno è la parte informativa dl nodo
	 * @param link contiene il reference del prossimo nodo
	 * @return nodo restituisce il nodo creato
	 */
	private Nodo creaNodo(Presenza alunno, Nodo link)
	{
		Nodo nodo= new Nodo(alunno);
		nodo.setLink(link);
		return nodo;
	}
	
	/**
	 * Consente di aggiungere un alunno in testa alla coda
	 * @param alunno alunno d inserire
	 */
	public void inserisciPresenza(Presenza alunno)
	{
		Nodo p=creaNodo(alunno, head);
		head=p;
		elementi++;
		
	}
	
	/**
	 * Metodo tostring che restituisce una stringa formata dagli alunni con 
	 * i relativi dati.
	 */
	public String toString()
	{
		String risultato="Head"; 
		if (elementi==0)
			return risultato+="-->";
		Nodo p=head;
		while(p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
	
	
	/**
	 * Consente di salvare i vari alunni con i relativi dati su un file binario.
	 * @param nomeFile nome del file sul quale saranno salvati gli alunni
	 * @throws IOException eccezione che viene sollevata quando si verificano errori durante la scrittura su file 
	 */
	public void salvaPresenza(String nomeFile) throws IOException
	{
		
		FileOutputStream file= new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
		
	}
	
	/**
	 * Consente di caricare i dati salvati sul file binario,
	 * per ricaricarli in coda.
	 * @param nomeFile nome del file sul quale effettuare la lettura
	 * @return r1 restituisce una serie di dati
	 * @throws IOException viene sollevata quando si verificano errori durante la lettura da file  
	 * @throws ClassNotFoundException viene sollevata quando si verifica un'errore di casting
	 */
	public RegistroElettronico caricaPresenza(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file= new FileInputStream(nomeFile);
		ObjectInputStream reader=new ObjectInputStream(file);
		
		RegistroElettronico r1;
		r1=(RegistroElettronico)(reader.readObject()); //casting
		file.close();
		return r1;
	}
	
	/**
	 * Metodo che consente di recuperare dei file precedentemente
	 * salvati sul file binario
	 * @return elencoFile sono i file recuperati
	 */
	public static String[] elencaGiorni()
	{
		File filesPresenti=new File(workingDir); //classe File, crea una rappresentazione astratta dei file in una directory
						
		int numeroFilesPresenti=filesPresenti.list().length;
		String[] elencoFile=new String[numeroFilesPresenti];
		elencoFile=filesPresenti.list();
		for (int i = 0; i < numeroFilesPresenti; i++)
			elencoFile[i]=elencoFile[i].substring(0, elencoFile[i].length()-4); //tolgo l'estensione.bin ai nomi dei file
		return elencoFile;		
	}
	
	/**
	 * Metodo che consente di verificare se un alunno era presente in una determinata data
	 * effettuando prima la deserializzazione del file 
	 * @param alunno alunno che sarà assente
	 * @throws IOException viene sollevata quando si verificano errori durante la lettura da file
	 * @throws ClassNotFoundException viene sollevata quando si verifica un'errore di casting
	 */
	public void verificaPresenza(Presenza alunno) throws IOException, ClassNotFoundException
	{
		int data;
		Presenza p=new Presenza();
		System.out.println("Inserisci il giorno del quale vuoi verificare la presenza dell'alunno (esempio: 2018-05-12)");
		data=tastiera.readInt();
		System.out.println("Inserisci il numero di matricola dello sudente");
		p.setNumeroMatricola(tastiera.readInt());
		FileInputStream file= new FileInputStream(data+".bin");
		ObjectInputStream reader=new ObjectInputStream(file);
		RegistroElettronico r1;
		r1=(RegistroElettronico)reader.readObject();
		
			
	}
	
	/**
	 * Metodo che consente di veificare quali studenti erano in ritardo in una dterminata data
	 * effettuando la deserializzazione
	 * @param oraDataIngresso data e ora in cui lo studente ha timbrato
	 * @return r1 restituisce una serie di dati, in questo caso gli alunni in ritardo
	 * @throws IOException viene sollevata quando si verificano errori durante la lettura da file  
	 * @throws ClassNotFoundException viene sollevata quando si verifica un'errore di casting
	 */
	public RegistroElettronico studentiInRitardo(LocalDateTime oraDataIngresso) throws IOException, ClassNotFoundException
	{
		InputStreamReader input=new InputStreamReader(System.in);
		BufferedReader tastiera= new BufferedReader(input);
		System.out.println("Inserisci giorno per verificare i ritardi");
		String s="";
		try 
		{
			s=tastiera.readLine();
		} 
		catch (IOException e) 
		{
			System.out.println("Errore nella lettura ");
		}
		FileInputStream file= new FileInputStream(s+".bin");
		ObjectInputStream reader=new ObjectInputStream(file);
		RegistroElettronico r1;
		r1=(RegistroElettronico)reader.readObject();
		return r1;
		
	}
	
	/**
	 * Metodo privato utilizzato per ottenere il nodo in una determiata posizione
	 * @param posizione posizione da cui si ricava il nodo
	 * @return p che rappresenta il collegamento ottenuto nella posizione richiesta
	 */
	private Nodo getLinkPosizione(int posizione)
	{
		
		Nodo p;
		int n;
		p=head;
		n=1;
			
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink();	//p va a puntare al nodo successivo
			n++;
		}
		
		return p;
	}
	
	/**
	 * Metodo che permette di recuperare dei dati da una determinata posizione
	 * @param posizione posizione da cui si ricava il nodo
	 * @return p 
	 */
	public Presenza getPresenza (int posizione) 
	{
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	
	/**
	 * Metodo che consente di strampare su un file di testo in formato CSV
	 * le presenze di un determinato giorno. Prima verrà richiesta la data 
	 * del file da deserializzare
	 * @param nomeFile nome del file dal quale recuperare i dati
	 * @throws IOException  viene sollevata quando si verificano errori durante la scrittura su file
	 * @throws FileException viene sollevata quando vi sono errori relativi al file
	 */
	public void esportaCSV(String nomeFile) throws IOException,FileException
	{
		TextFile file=new TextFile (nomeFile,'W');
		String alunnoCSV;
		Presenza alunno;
		
		for (int i = 1; i < getElementi(); i++) 
		{
			alunno=getPresenza(i);
			alunnoCSV=alunno.getNumeroMatricola()+";"+alunno.getOraDataIngresso()+";";
			file.toFile(alunnoCSV);
		}
		file.closeFile();
	}
	
	


	
	
	
	
}

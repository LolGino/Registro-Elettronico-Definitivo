import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JSpinner.NumberEditor;

public class MainClass 
{
	static ConsoleInput tastiera=new ConsoleInput();
	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException 
	{		
		String[] elencoFunzioni=new String[6];
		elencoFunzioni[0]="0--->Esci";
		elencoFunzioni[1]="1--->Aggiungi Presenza";
		elencoFunzioni[2]="2--->Salva File";
		elencoFunzioni[3]="3--->Stampa le presenze in CSV";
		elencoFunzioni[4]="4--->Verifica presenza di un alunno";
		elencoFunzioni[5]="5--->Studenti in ritardo";
		
		
		
		
		
		RegistroElettronico r1=new RegistroElettronico();
		ConsoleInput tastiera=new ConsoleInput();
		
		/* 
		  try 
		{
			r1=r1.caricaPresenza(LocalDate.now().toString()+".bin");
			System.out.println("File caricati");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Impossibile caricare oggetti di tipo ProcessoStampa");
		}
		catch (IOException e) 
		{
			System.out.println("Impossibile leggere da file");
		}*/
		
		Menu m1=new Menu(elencoFunzioni);
		int scelta=0 ;
		
		do {
			scelta=m1.scelta();
			switch (scelta) 
			{
			case 0:
				try 
				{
					r1.salvaPresenza(LocalDateTime.now()+".bin");
					System.out.println("salvataggio avvenuto con successo");
				} 
				catch (IOException e) 
				{
					System.out.println("Errore. Operazione annullata.");
				}
			break;
			
			case 1:
			{
				Presenza p=new Presenza();
				try 
				{
					System.out.println("Inserisci il numero di matricola dello studente");
					p.setNumeroMatricola(tastiera.readInt());
					System.out.println(LocalDateTime.now()); 
					r1.salvaPresenza( LocalDate.now().toString()+".bin");
				} 
				catch (IOException e) 
				{
					System.out.println("Errore. Operazione annullata");
				}
				
			}
			break;
			
			case 2: 			
			{
				try 
			
				{
					
					r1.salvaPresenza(LocalDate.now().toString()+".bin");
					System.out.println("File salvato");
				} 
				catch (IOException e) 
				{
					System.out.println("Impossibile scrivere sul file");				
					e.printStackTrace();
				}
			}
			break;
			
			case 3:
			{
				Presenza p=new Presenza();
				p.getOraDataIngresso();
				String data;
				int anno,mese,giorno,ora,minuti,secondi;
				System.out.println("Inserisci anno: ");
				anno=tastiera.readInt();
				System.out.println("Inserisci mese: ");
				mese=tastiera.readInt();
				System.out.println("Inserisci giorno: ");
				giorno=tastiera.readInt();

				data=(anno+"-"+mese+"-"+giorno);
				//esportazione
				
				try 
				{
					
					r1.esportaCSV(data+".txt");
				} 
				catch (IOException e) 
				{
					System.out.println("Impossibile scivere sul file");
				} 
				catch (FileException e) 
				{
					System.out.println(e.toString());
				}
				System.out.println("Stampa effettuata");
			}
			System.out.println("Stampa eseguita");
			break;
			
			case 4:
			{
				Presenza alunno = null;		
				try 
				{
					r1.verificaPresenza(alunno);
				} 
				catch (ClassNotFoundException e) 
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
				catch (NumberFormatException e)
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
				catch (FileNotFoundException e) 
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
			}
			break;
			
			case 5:
			{
				LocalDateTime oraDataIngresso = null;
				try
				{
					r1.studentiInRitardo(oraDataIngresso);
				}
				catch (ClassNotFoundException e) 
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
				catch (NumberFormatException e)
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
				catch (FileNotFoundException e) 
				{
					System.out.println("Errore");
					e.printStackTrace();
				}
				
			}
			break;
			
			
			default:
				System.out.println("Scelta non consentita");
				break;
			}
			
			System.out.println("Premi un tasto per continuare");
			try 
			{
				tastiera.readString();
			} 
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			
		} 
		while (scelta!=0);
		
		

		
		
		
	}

}

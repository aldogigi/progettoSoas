package operatori;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
* La classe fa da proxy con il server offrendo al client tutti i metodi per l'invio di dati
* 
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
*/
public class ProxyServer implements ServerInterface {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private InetAddress addr;

	/**
	 * Il costruttore definisce la Socket e gli oggetti I/O
	 * 
	 * @exception IOExeption se non si riesce a comunicare con il server
	 */
	public ProxyServer() throws IOException {
		addr = InetAddress.getByName(null);
		
	}


	/**
	 * Vengono inviati al server tutti i dati relativi alla registrazione di un nuovo centro vaccinale
	 * 
	 * @param param è la stringa che contiene tutte le informazioni del centro vaccinali delimitati da :
	 * 
	 * @exception IOExeption se non si riesce a comunicare con il server
	 * 
	 * @return intero maggiore di 0 se il server ha inserito il centro vaccinale, intero negativo altrimenti
	 */
	
	public String checkUser(String param) throws IOException {
		
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("checkUser:" + param);

		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return risposta;
	}
	
	public int inserisciCV(String param) throws IOException {
		
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("inserisciCV:" + param);

		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(risposta);
	}
	
	public int inserisciUser(String param) throws IOException {
		
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("inserisciUser:" + param);
		
		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(risposta);
	}

	/**
	 * Vengono inviati al server tutti i dati relativi alla registrazione di una nuova vaccinazione
	 * somministrata ad un cittadino
	 * 
	 * @param param è la stringa che contiene tutte le informazioni del cittadino delimitati da :
	 * 
	 * @exception IOExeption se non si riesce a comunicare con il server
	 * 
	 * @return intero maggiore di 0 se il server ha inserito la vaccinazione, intero negativo altrimenti
	 */
	public int inserisciVN(String param) throws IOException {
		
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("inserisciVN:" + param);

		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(risposta);
	}

	/**
	 * Viene inviata una richiesta al server per richiedere tutti i centri vaccinali presenti nel database
	 * 
	 * @exception IOExeption se non si riesce a comunicare con il server
	 * 
	 * @return lista di tutti i centri vaccinali se presenti, lista vuota altrimenti
	 */
	public String popolaCV() throws IOException {
		
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("popolaCV");

		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return risposta;
	}


	public String deployAllRuleXACML() throws IOException {

		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("deployAllRuleXACML");

		String risposta = in.readLine();

		try {
			in.close();
			out.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return risposta;
		
	}

}

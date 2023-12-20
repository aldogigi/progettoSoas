package cittadini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
/**
 * E' il proxy che viene usato dal client per comunicare con il server
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */
public class ProxyServer implements ServerInterface{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private InetAddress addr;
	
	/**
	 * Il costruttore instaura un collegamento con il server e inizializza
	 * gli oggetti di I/O
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * @exception SocketEception se il server è disconesso
	 */
	public ProxyServer() throws IOException {
		addr = InetAddress.getByName(null);
		try {
		socket = new Socket(addr, ServerInterface.PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		}
		catch(SocketException e) {
			System.out.println("Server Disconnesso");
		}
		
	}

	/**
	 * Il metodo invia al server la stringa contenente tutti i dati
	 * salvati dal form della registrazione del cittadino per registrarlo
	 * sul database
	 * 
	 * @param CF codice fiscale del cittadino
	 * @param email email del cittadino
	 * @param password password scelta dal cittadino
	 * 
	 * @exception Exception se non si riesce a comunicare con il server
	 * 
	 * @return 	L'utente è già registrato,
	 * 		    L'utente non si è ancora vaccinato o
	 * 		    C'è qualcosa che non va
	 * 
	 */
	public String registraCittadino(String CF, String email, String password) throws Exception {
		
		String param = CF + ":" + email + ":" + password;
		out.println("inserisciCittadini:" + param);
		
		String risposta = in.readLine();
	
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return risposta.toString();
	}


	/**
	 * Il metodo invia al server la stringa contenente tutti i dati
	 * del form login per poterlo autenticare
	 * 
	 * @param Email_CF il cittadino può autenticarsi o tramite il proprio codice
	 *                 fiscale o l'email inseriti nella fase di registrazione
	 * @param Pass     password inserita durante la fase di registrazione
	 * 
	 * @exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return 	non ho trovato nè CF nè Email sul Db, Registrati!,
	 * 			password errata,
	 * 			autenticazione o
	 * 			Il codice fiscale ha tutte lettere maiuscole
	 *  
	 */
	public String CheckCittadini(String Email_CF, String Pass) throws Exception {
		String param = Email_CF + ":" + Pass;
		out.println("CheckCittadini:" + param);
		
		String risposta = in.readLine();
	
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return risposta.toString();
	}


	/**
	 * Il metodo invia al server una stringa che determina una richiesta di cercare tutti i centri vaccinali
	 * che contengano un determinato nome
	 * 
	 * @param name rappresenta il nome del centro vaccinale
	 * 
	 * @exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return	niente se non è presente nessun centro vaccinale contenente quel nome,
	 * 			tutti i centri vaccinali altrimenti
	 */
	public String research_forName(String name) throws Exception {
		String param = name;
		out.println("research_forName:" + param);
		
		String risposta = in.readLine();
	
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return risposta;
	}
	

	/**
	 * Il metodo invia al server una stringa che determina una richiesta di cercare tutti i centri vaccinali
	 * che contengano un determinato nome e che siano di una determinata tipologia
	 * 
	 * @param Municipality comune del centro vaccinale
	 * @param Type         tipologia del centro vaccinale: hub,
	 *                     ospedaliero,aziendale
	 * 
	 * @exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return 	niente se non è presente nessun centro vaccinale che rispetti i due vincoli imposti
	 *  		tutti i centri vaccinali
	 */
	public String research_forType_Municipality(String Municipality, String Type) throws Exception {
		String param = Municipality + ":" + Type;
		out.println("research_forType_Municipality:" + param);
		
		String risposta = in.readLine();
	
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return risposta;
		
	}

	/**
	 * Il metodo set invia una stringa al server contenente l'email o il codice fiscale
	 * del cittadino per poter ricercare tutti i centri vaccinali in cui è stato vaccinato
	 * 
	 * @param Email_CF contenente email o codice fiscale del cittadino
	 * 
	 * @exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return niente se non è ancora stato vaccinato, 
	 *         lista dei centri vaccinali altrimenti
	 */
	public String setCF(String Email_CF) throws Exception {
		String param = Email_CF;
		out.println("setCF:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}

	/**
	 * Il metodo invia una stringa contenente il nome di un cittadino 
	 * che determina la richiesta di ottenere informazioni
	 * (vaccinazioni e avversità) per il cittadino in questione
	 * 
	 * @param id      centro vaccinale
	 * @param CF email o codice fiscale di un cittadino
	 * 
	 * @exception Exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return 	lista di tutte ke vaccinazione eseguite e le eventuali avversità,
	 * 			niente se non ci sono vaccinazioni e le eventuali avversità
	 */
	public String visualizzaInfoCentroVaccinale(String id, String CF) throws Exception {
		String param = id + ":" + CF;
		out.println("selectAvversita:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}

	/**
	 * Il metodo invia una stringa contenente l'id di un centro vaccinale 
	 * che determina la richiesta di ottenere tutte le informazioni
	 * (vaccinazioni e avversità) di un centro vaccinale in forma anonima
	 * 
	 * @param id      centro vaccinale
	 * 
	 * @exception Exception Exception se non si riesce a comunicare con il Server
	 * @return 	lista di tutte vaccinazione eseguite e le eventuali avversità
	 * 			niente se non ci sono vaccinazioni e le eventuali avversità
	 */
	public String visualizzaInfoCentroVaccinale2(String id) throws Exception {
		String param = id;
		out.println("selectAvversita2:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}
	
	/**
	 * Il metodo invia al server una stringa che determina l'inserimento di una eventuale avversità
	 * per il cittadino dopo essersi loggato
	 * 
	 * @param tipo    indica la tipologia di avversità
	 * @param severy  indica la severità dell'evento (1-5)
	 * @param note    commento opzionale riguardante l'avversità
	 * @param id1     id della vaccinazione
	 * @param cFEmail codice fiscale o email del cittadino
	 * @param
	 * 
	 * @return 	inserimento avvenuto se non ci sono stati problemi, 
	 * 			trovato evento avverso uguale se il cittadino prova ad inserire un evento avverso già presente
	 */
	public String inserisciEventiAvversi(String tipo, int severy, String note, String id1, String cFEmail) throws Exception {
		
		String param = tipo + ":" + severy + ":" + note + ":" + id1 + ":" + cFEmail;
		out.println("insertAvversita:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
		
		
	}

	/**
	 * Il metodo invia una stringa contenente id del centro vaccinale e codice fiscale del cittadino 
	 * al server che determina la ricerca di 
	 * tutte le vaccinazioni somministrate ad un cittadino
	 * 
	 * @param id1      indica il centro vaccinale
	 * @param CFemail email o codice fiscale
	 * 
	 * @return 	niente se il cittadino non è stato ancora vaccinato,
	 * 			lista di tutte le vaccinazioni altrimenti
	 */
	public String popola_id_V(String id1, String CFemail) throws Exception {
		String param = id1 + ":" + CFemail;
		out.println("popola_id_V:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
		
	}

	/**
	 * Il metodo updateAvversita invia una stringa che determina la richiesta di modificare un'avversità
	 * già presente
	 * 
	 * @param id_vaccinazione id della vaccinazione
	 * @param id              id dell'evento avverso
	 * @param evento          evento avverso
	 * @param severita        grado di severtià
	 * @param note            eventuali commenti (opzionale)
	 * @param string 
	 * 
	 * @return 	modifca avvenuta se non ci sono stati problemi,
	 * 			modifica fallita altrimenti
	 */
	public String updateAvversita(String cf, String id_vaccinazione, String id, String evento, String severita, String note) throws Exception {
		
		String param = cf + ":" + id_vaccinazione + ":" + id + ":" + evento + ":" + severita + ":" + note;
		out.println("updateAvversita:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
		
	}

	/**
	 * Il metodo deleteAvversita invia una richiesta al server di rimuovere una avversità selezionata da un utente
	 * 
	 * @param id id rappresentante l'avversità 
	 * @param id_evento_avverso 
	 * 
	 * @exception Exception Exception se non si riesce a comunicare con il Server
	 * 
	 * @return 	cancellazione avvenuta se non ci sono stati problemi,
	 *  		cancellazione fallita se ci sono stati dei problemi
	 */
	public String deleteAvversita(String id1, String id_evento_avverso) throws Exception {
		
		String param = id1 + ":" + id_evento_avverso;
		out.println("deleteAvversita:" + param);
	
		String risposta = in.readLine();
		
		try {
			in.close();
			out.close();
			socket.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}
	
	public String openOauth(String login, String operatori) throws IOException {

		socket = new Socket(addr, ServerInterface.PORTOAUTH);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		out.println("openOauth:" + login + ":" + operatori);
		
		String risposta = in.readLine();
		
		System.out.println(risposta);
		
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

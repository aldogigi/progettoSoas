package operatori;
import java.io.IOException;

/**
* E' l'interfaccia che viene implementata da ProxyServer.
* Contiene i prototipi dei metodi e la porta su cui il server è in funzione
*
* @author Gianluca Fontana 21452A
* @author Alex Rabuffetti "Matricola"
*/
public interface ServerInterface {
	public static final int PORT = 4444;

	public int inserisciCV(String param) throws IOException;

	public int inserisciVN(String param) throws IOException;

	public String popolaCV() throws IOException;
	
	public int inserisciUser(String param) throws IOException;
	
	public String deployAllRuleXACML() throws IOException;
}

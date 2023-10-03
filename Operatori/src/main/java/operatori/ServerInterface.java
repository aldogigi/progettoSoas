package operatori;
import java.io.IOException;

/**
* E' l'interfaccia che viene implementata da ProxyServer.
* Contiene i prototipi dei metodi e la porta su cui il server è in funzione
*
* @author Andrea Ferro 740958 VA
* @author Gianluca Fontana 742393 VA
* @author Manuel Nguyen 741939 VA 
* @author Digvijaysinh D. Raj 741976 VA
*/
public interface ServerInterface {
	public static final int PORT = 4444;

	public int inserisciCV(String param) throws IOException;

	public int inserisciVN(String param) throws IOException;

	public String popolaCV() throws IOException;
}

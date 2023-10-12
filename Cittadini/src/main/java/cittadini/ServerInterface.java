package cittadini;

/**
 * E' l'interfaccia che viene implementata dal ProxyServer
 * <p>Contiene prototipi del metodi che comunicano con il server e la porta su cui
 * <p>il server è in ascolto
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */
public interface ServerInterface {
	public static final int PORT = 4444;

	public String registraCittadino(String CF, String email, String password) throws Exception;

	public String CheckCittadini(String Email_CF, String Pass) throws Exception;

	public String research_forName(String name) throws Exception;

	public String research_forType_Municipality(String Municipality, String Type) throws Exception;

	public String setCF(String Email_CF) throws Exception;

	public String visualizzaInfoCentroVaccinale(String id, String CF) throws Exception;

	public String visualizzaInfoCentroVaccinale2(String id) throws Exception;

	public String inserisciEventiAvversi(String tipo, int severy, String note, String id1, String cFEmail)
			throws Exception;

	public String popola_id_V(String id1, String CFemail) throws Exception;

	public String updateAvversita(String id_vaccinazione, String id, String evento, String severita, String note)
			throws Exception;
	
	public String deleteAvversita(String id_avversita) throws Exception;
}

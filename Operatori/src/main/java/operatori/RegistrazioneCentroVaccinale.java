package operatori;
import java.io.IOException;

/**
* La classe esegue tutti i controlli sulle informazioni ricevute per la registrazione del nuovo
* <p>centro vaccinale e procede a inviarli al server tramite il proxy
*
* @author Andrea Ferro 740958 VA
* @author Gianluca Fontana 742393 VA
* @author Manuel Nguyen 741939 VA 
* @author Digvijaysinh D. Raj 741976 VA
*/
public class RegistrazioneCentroVaccinale {
	private String nomecv, hub, indirizzo, nome, comune, provincia;
	private int civico, cap;
	private String errore;

	/**
	 * Il costruttore inizializza tutti i parametri ricevuti in input
	 * 
	 * @param nomecv nome del centro vaccinale
	 * @param hub tipologia del centro vaccinale
	 * @param indirizzo indirizzo del centro vaccinale (senza comune, provincia e cap)
	 * @param nome nome del centro vaccinale
	 * @param civico civico
	 * @param comune comune in cui si trova il centro vaccinale
	 * @param cap
	 * 
	 */
	public RegistrazioneCentroVaccinale(String nomecv, String hub, String indirizzo, String nome, int civico,
			String comune, String provincia, int cap) {
		this.nomecv = nomecv;
		this.hub = hub;
		this.indirizzo = indirizzo;
		this.nome = nome;
		this.civico = civico;
		this.comune = comune;
		this.provincia = provincia;
		this.cap = cap;

		errore = "";
	}

	/**
	 * Viene creata la stringa contenente tutti i dati del centro vaccinale,
	 * viene creata un'istanza del ProxyServer e viene inviata la stringa richiamando
	 * il metodo inserisciCV()
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * 
	 * @return intero maggiore di 0 se il server ha inserito il centro vaccinale, intero negativo altrimenti
	 * 
	 */
	public int registraCentroVaccinale() throws IOException {
		String parametri = nomecv + ":" + hub + ":" + indirizzo + ":" + nome + ":" + civico + ":" + comune + ":"
				+ provincia + ":" + cap;

		ProxyServer ps = new ProxyServer();
		int risultato = ps.inserisciCV(parametri);

		return risultato;
	}

	/**
	 * Vengono effettuati tutti i controlli sugli input salvati dal form
	 * 
	 * @return OK se non ci sono errori, errore specifico in base all'errore altrimenti
	 */
	public String controllo() {

		if (nomecv.length() == 0) {
			errore += "Inserire nome del entro vaccinale\n";

		}

		if (nomecv.length() > 0) {
			if (!nomecv.matches("^\\p{L}+(?: \\p{L}+)*$")) {
				errore += "Il nome centro vaccinale deve contenere solo lettere\n";
			}
		}

		if (nome.length() == 0) {
			errore += "Inserire nome per l'indirizzo\n";
		}

		if (nome.length() > 0) {
			if (nome.matches(".*\\d.*")) {
				errore += "Il nome dell'indirizzo non può contenere numeri\n";
			}
		}

		if (comune.length() == 0) {
			errore += "Inserire il comune\n";
		}

		if (comune.length() > 0) {
			if (!comune.matches("^\\p{L}+(?: \\p{L}+)*$")) {
				errore += "Il comune deve contenere solo lettere\n";
			}
		}

		if (provincia.length() == 0) {
			errore += "Inserire la provincia\n";
		}

		if (provincia.length() > 0) {
			if (!provincia.matches("[a-zA-Z]+")) {
				errore += "La provincia deve contenere solo lettere\n";
			}
		}

		if (provincia.length() > 2 || provincia.length() == 1) {
			errore += "La provincia deve contenere solo la sigla [EX: VA]\n";
		}

		if (civico == 0) {
			errore += "Inserire il civico\n";
		}

		if (cap != 0) {
			if (String.valueOf(cap).length() != 5) {
				errore += "Il CAP deve essere di 5 cifre";
			}
		}

		if (cap == 0) {
			errore += "Inserire il cap\n";
		}

		if (errore.length() > 0) {
			return errore;
		} else
			return "OK";

	}
}

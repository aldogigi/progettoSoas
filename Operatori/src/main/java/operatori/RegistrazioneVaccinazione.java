package operatori;
import java.io.IOException;

/**
*La classe esegue tutti i controlli sulle informazioni ricevute per la registrazione della nuova
* <p>vaccinazione e procede a inviarli al server tramite il proxy
*
* @author Gianluca Fontana 21452A
* @author Alex Rabuffetti "Matricola"
*/
public class RegistrazioneVaccinazione {
	private String centroVaccinale, nome, cognome, codiceFiscale, data, vaccinoSom;
	private String errore;

	/**
	 * Il costruttore inizializza tutti i parametri ricevuti in input
	 * 
	 * @param centroVaccinale è il centro vaccinale in cui il cittadino è stato vaccinato
	 * @param nome nome del cittadino
	 * @param cognome cognome del cittadino
	 * @param codiceFiscale codice fiscale del cittadino
	 * @param data data in cui viene somministrata la vaccinazione
	 * @param vaccinoSom nome del vaccino che viene somministrato
	 * 
	 */
	public RegistrazioneVaccinazione(String centroVaccinale, String nome, String cognome, String codiceFiscale,
			String data, String vaccinoSom) {
		this.nome = nome;
		this.cognome = cognome;
		this.centroVaccinale = centroVaccinale;
		this.codiceFiscale = codiceFiscale;
		this.data = data;
		this.vaccinoSom = vaccinoSom;

		errore = "";
	}

	/**
	 * Viene creata la stringa contenente tutti i dati dell vaccinazione,
	 * viene creata un'istanza del ProxyServer e viene inviata la stringa richiamando
	 * il metodo inserisciVN()
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * 
	 * @return intero maggiore di 0 se il server ha inserito la vaccinazione, intero negativo altrimenti
	 * 
	 */
	public int registraVaccinato() throws IOException {
		String parametri = centroVaccinale + ":" + nome + ":" + cognome + ":" + codiceFiscale.toLowerCase() + ":" + data
				+ ":" + vaccinoSom;

		ProxyServer ps = new ProxyServer();
		int risultato = ps.inserisciVN(parametri);

		return risultato;
	}

	/**
	 * Vengono effettuati tutti i controlli sugli input salvati dal form
	 * 
	 * @return OK se non ci sono errori, errore specifico in base all'errore altrimenti
	 */
	public String controllo() {
		if (nome.length() == 0) {
			errore += "Inserire il nome del vaccinato\n";
		}

		if (nome.length() > 0) {
			if (!nome.matches("^\\p{L}+(?: \\p{L}+)*$")) {
				errore += "Il nome del vaccinato deve contenere solo lettere\n";
			}
		}

		if (cognome.length() == 0) {
			errore += "Inserire il cognome del vaccinato\n";
		}

		if (cognome.length() > 0) {
			if (!cognome.matches("^\\p{L}+(?: \\p{L}+)*$")) {
				errore += "Il cognome del vaccinato deve contenere solo lettere\n";
			}
		}

		if (codiceFiscale.length() == 0) {
			errore += "Inserire il CF del vaccinato\n";
		}

		if (codiceFiscale.length() != 16) {
			errore += "Il CF deve essere di 16 caratteri";
		} else if (!(codiceFiscale.equals(codiceFiscale.toUpperCase()))) {
			errore += "Il CF non ha tutti i caratteri maiuscoli";
		} else {

		}

		if (errore.length() > 0) {
			return errore;
		} else
			return "OK";
	}
}
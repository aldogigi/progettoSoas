package cittadini;

/**
 * La classe permette la gestione tra la scelta di: ricerca per nome 
 * <p>o ricerca per comune e tipologia nella finestra iniziale di Homepage_cittadini2
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */
public class ComboItem {
	private String key;
	private String value;

	/**
	 * Costruttore per la gestione della ComboItem 
	 * 
	 * @param key chiave del ComboItem
	 * @param value valore del ComboItem scelto
	 */
	public ComboItem(String key, String value) {
		this.key = key;
		this.value = value;
	}


	/**
	 * Ridefinizione del metodo toString()
	 * @return riferimento alla chiave
	 */
	public String toString() {
		return key;
	}

	/**
	 * Permette di ritornare la chiave del ComboItem scelto
	 * @return riferimento alla chiave
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Permette di ritornare il valore del ComboItem scelto
	 * @return riferimento al valore
	 */
	public String getValue() {
		return value;
	}
}
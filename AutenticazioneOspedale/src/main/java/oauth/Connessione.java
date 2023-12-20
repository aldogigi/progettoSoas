package oauth;

import java.sql.*;

/**
 * E' La classe che si occupa della connessione al database in cui
 * <p>sono presenti tutti i dati relativi all'applicativo
 * 
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */

public class Connessione {
	private String username, pwd, host, port, db; /*Credenziali del database*/
	private String url; /*Stringa finale composta da tutte le credenziale*/
	private Connection conn;

	/**
	 * Costruttore che salva tutti i dati relativi per accedere al database
	 * 
	 * @param username username con cui accedere al database
	 * @param pwd password dell'username
	 * @param host indirizzo della macchina su cui è presente il database
	 * @param port porta su cui gira il databse
	 * @param db nome database
	 * 
	 */
	public Connessione(String username, String pwd, String host, String port, String db) {
		this.username = username;
		this.pwd = pwd;
		this.host = host;
		this.port = port;
		this.db = db;

		url = "";
		conn = null;
	}

	/**
	 * Il metodo connetti esegue la connessione al database centrale
	 * 
	 * @exception SQLException se ci sono problemi nella connessione con la query
	 * @exception ClassNotFoundException se non viene trovata la classe
	 * 
	 * @return oggetto Connection che rappresenta la connessione appena istanziata
	 */
	public Connection connetti() {
		url = "jdbc:postgresql://" + host + ":" + port + "/" + db + "";

		try {
			Class.forName("org.postgresql.Driver"); /*Inizializzo il Driver giusto per PostgreSQL*/
			conn = DriverManager.getConnection(url, username, pwd); /*Connessione al database*/
			System.out.println("Connesso al database");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.print("Collegamento al database non effettuato. Controllare i parametri inseriti");
			System.exit(0);
		}

		return conn;
	}

	/**
	 * Il metodo getConn permette di ritornare l'istanza della connessione al database
	 * 
	 * @return istanza connessione al database
	 */
	public Connection getConn() {
		return conn;
	}
	
	/**
	 * Il metodo interrompe la connessione al database rilasciando tutte le risorse
	 * 
	 * @exception SQLException se non si riesce a chiudere la connessione
	 * 
	 */
	public void chiudiConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

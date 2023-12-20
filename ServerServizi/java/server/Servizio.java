package server;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.*;

import org.postgresql.util.PSQLException;

import xml_dati_Cittadini_XMLCheckerPolicy.XMLChecker;
import xml_dati_Cittadini_XMLediting.*;

/**
 * E' la classe che fornisce i metodi principali che si occupano di interrogare il database eseguendo la query di interesse.
 * 
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */
public class Servizio{
	private Connection conn;
	private String Email_CF;
	private File fileShowInsert = new File("ServerServizi\\src\\main\\resources\\PolicyCittadiniShowInsert.xml");
	private File fileModifyDelete = new File("ServerServizi\\src\\main\\resources\\PolicyCittadiniModifyDelete.xml");
	private PolicyCittadini policyCittadini = new PolicyCittadini();
	private PolicyCittadini policyCittadini2 = new PolicyCittadini();
	private JAXBContext jc = JAXBContext.newInstance(PolicyCittadini.class);
	private Marshaller marshaller;
	private XMLChecker checker;
	/**
	 * Il costruttore inizializza la connessione con il database
	 * 
	 * @param conn rappresenta la connessione stabilita con il database
	 * @throws Exception 
	 */
	public Servizio(Connection conn) throws Exception {
		this.conn = conn;

	}

	/**
	 * Il metodo carica sul database un nuovo centro vaccinale ancora
	 * non presente
	 * 
	 * @param nomecv    nome del centro vaccinale
	 * @param tipologia tipologia del centro vaccinale (hub,ospedaliero,
	 *                  aziendale)
	 * @param indirizzo qualificatore dell'indirizzo (via,viale,piazza)
	 * @param nome      nome indirizzo
	 * @param civico    civico
	 * @param comune    comune in cui si trova il centro vaccinale
	 * @param provincia provincia in cui ci trova il centro vaccinale
	 * @param cap       cap
	 * 
	 * @exception SQLException se ci sono problemi con la query SQL
	 * 
	 * @return un intero positivo se l'inserimento è andato a buon fine, negativo
	 *         altrimenti
	 */
	public int inserisciCV(String nomecv, String tipologia, String indirizzo, String nome, int civico, String comune,
			String provincia, int cap) {

		String indirizzoCompleto = indirizzo + " " + nome + " " + civico;

		System.out.println("INDIRIZZO: " + indirizzoCompleto);
		System.out.println("Comune: " + comune);
		System.out.println("provincia: " + provincia);
		System.out.println("cap: " + cap);

		int result = 0;

		try {

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

			ResultSet ris = stmt.executeQuery(
					"SELECT centro_vaccinale_id FROM centri_vaccinali " + "WHERE nome = '" + nomecv + "';");

			if (ris.first()) {
				System.out.println("Centro vaccinale già presente");
				result = -1;
				return result;
			}

			// Procedo con l'inserimento del centro vaccinale
			result = stmt.executeUpdate(
					"INSERT INTO centri_vaccinali(centro_vaccinale_id, nome, indirizzo, comune, provincia, cap, tipologia) VALUES(nextval('centri_vaccinali_seq'),"
							+ "'" + nomecv + "'," + "'" + indirizzoCompleto + "'," + "'" + comune + "'," + "'"
							+ provincia + "'," + "'" + cap + "'," + "'" + tipologia + "'" + ");");

			if (result > 0) { // Se il centro vaccinale è stato inserito
				System.out.println("Centro vaccinale inserito");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Il metodo carica sul database una vaccinazione somministrata ad
	 * un cittadino
	 * 
	 * @param centroVaccinale centro vaccinale in cui è stato somministrato il
	 *                        vaccino
	 * @param nome            nome del cittadino
	 * @param cognome         cognome del cittadino
	 * @param codiceFiscale   codice fiscale del cittadino
	 * @param data            data in cui è stato somministrato il vaccino
	 * @param vaccinSom       indica il vaccino somministrato
	 * 
	 * @exception SQLException se ci sono problemi con la query SQL
	 * @return un intero positivo se la vaccinazione viene inserita senza errori,
	 *         altrimenti ritorna un intero negativo e viene eseguito il rollback
	 */
	public int inserisciVN(String centroVaccinale, String nome, String cognome, String codiceFiscale, String data,
			String vaccinSom) throws Exception{

		int result = 0;
		int x = 0;

		try {
			conn.setAutoCommit(false); /* Le query eseguite non saranno più transazioni */
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			Statement stmt = conn.createStatement();

			/* Query per vedere se il cittadino è già stato vaccinato */
			ResultSet rs = stmt
					.executeQuery("SELECT cf FROM cittadini_vaccinati " + "WHERE cf = '" + codiceFiscale + "';");

			if (rs.next() == false) { /*
										 * Nel caso non fosse stato ancora vaccinato lo inserisco nella tabella
										 * cittadini_registrati
										 */
				System.out.println("Cittadino non ancora inserito");

				result = stmt.executeUpdate("INSERT INTO cittadini_vaccinati(cf, nome, cognome) VALUES('"
						+ codiceFiscale + "'," + "'" + nome + "'," + "'" + cognome + "'" + ");");
			}

			ResultSet ris = stmt.executeQuery("SELECT centro_vaccinale_id FROM centri_vaccinali " + "WHERE nome = '"
					+ centroVaccinale + "';"); /* Per sapere qual è l'id del centro vaccinale */

			int id = 0;

			if (ris.next()) {
				id = Integer.parseInt(ris.getString("centro_vaccinale_id"));
				System.out.println("Id del centro vaccinale: " + id);
			}

			/* Procedo con l'inserimento della vaccinazione effettuata del mio cittadino */
			x = stmt.executeUpdate(
					"INSERT INTO vaccinazione_effettuata(id_vaccinazione, data_somministrazione, vaccino_somministrato, centro_vaccinale_id, cf) VALUES(nextval('vaccinazione_effettuata_seq'),"
							+ "'" + data + "'," + "'" + vaccinSom.toLowerCase() + "'," + "'" + id + "'," + "'"
							+ codiceFiscale + "'" + ");");

			if (x > 0) { /* Se la query è eseguita con successo */
				System.out.println("Vaccinazione inserita");
				result = x;
				conn.commit(); /* Approvo la query e la rendo permanente */
		        //--------------------------------------------------------
		        
				
			} else {
				result = -1;
				conn.rollback(); /* Nel caso ci siano stato errori eseguo un ripristino */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Il metodo permette di ottenere tutti i nomi dei centri vaccinali
	 * inseriti
	 * 
	 * @exception SQLException se ci sono problemi con la query SQL
	 * @return stringa contente tutti i centri vaccinali
	 */
	public String popolaCV() {
		String listaCV = "";

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT nome FROM centri_vaccinali;"); /* Query per ritornare tutti i centri vaccinale inseriti */

			while (rs.next()) {
				listaCV += rs.getString("nome")
						+ ":"; /* Creo una stringa unica che delmita ogni centro vaccinale da ':' */
			}

			if (listaCV.length() == 0) {
				listaCV = "vuota"; /* Nel caso non ci fossero ancora centri vaccinali inseriti */
			} else {
				listaCV += "END";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.println(listaCV);
		return listaCV;
	}

	/**
	 * Il metodo permette di registrare sul database un cittadino
	 * che desidera, dopo essersi vaccinato almeno una volta, di poter gestire le
	 * proprie avversità,
	 * 
	 * @param CF       rappresenta il codice fiscale del cittadino
	 * @param email    rappresenta l'email con la quale il cittadino desidera
	 *                 registrarsi
	 * @param password password scelta dal cittadino per potersi autenticare
	 * 
	 * @return stringa che informa il cittadino se è stato registrato, non è stato
	 *         registrato o se non è ancora stato vaccinato
	 */
	public String registraCittadino(String CF, String email, String password) throws Exception {

		System.out.print(CF);
		Statement stmt = conn.createStatement();
		ResultSet ris = stmt.executeQuery("SELECT cf FROM vaccinazione_effettuata " + "WHERE cf = '" + CF.toLowerCase() + "' ;");
		stmt = conn.createStatement();
		ResultSet ris2 = stmt.executeQuery("SELECT cf FROM cittadini_registrati " + "WHERE cf = '" + CF.toLowerCase() + "';");

		String result = "";
		if (ris.next()) {
			result = String.valueOf(ris.getString("cf"));
			if (ris2.next()) {
				return "L'utente e' gia' registrato";
			} else {
				System.out.println("C'e' qualcosa che non va");
			}

			stmt.executeUpdate("INSERT INTO cittadini_registrati(email, password, cf) VALUES('" + email + "', '"
					+ password + "','" + CF.toLowerCase() + "');");
			deployAllRuleXACML();
			return "inserimento avvenuto";

		} else if (!(ris.next())) {
			return "L'utente non si e' ancora vaccinato";

		}

		return result;
	}

	/**
	 * Il metodo gestisce l'autenticazione del cittadino
	 * dell'applicativo Cittadini
	 * 
	 * @param Email_CF il cittadino può autenticarsi o tramite il proprio codice
	 *                 fiscale o l'email inseriti nella fase di registrazione
	 * @param Pass     password inserita durante la fase di registrazione
	 * 
	 * @return stringa che comunica il risultato dell'autenticazione: email / CF non
	 *         presenti, password errata o autenticazione che indica
	 *         l'autenticazione avvenuta
	 */
	public String CheckCittadini(String Email_CF, String Pass) throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet ris = null;
		if(Pass.equals("null")) {
			String[] cfsplit = Email_CF.split("_");
			Email_CF = cfsplit[0];
		}
		ris = stmt.executeQuery("SELECT cf FROM cittadini_registrati " + "WHERE cf = '" + Email_CF.toLowerCase() + "' ;");
		if (!(ris.next())) {
			ris = stmt.executeQuery(
					"SELECT cf FROM cittadini_registrati " + "WHERE email = '" + Email_CF.toLowerCase() + "' ;");
			if (!(ris.next())) {
				return "non ho trovato ne' CF ne' Email sul Db, Registrati!";
			} else {
				ResultSet ris2 = stmt.executeQuery("SELECT cf FROM cittadini_registrati " + "WHERE password = '" + Pass
						+ "' AND email = '" + Email_CF.toLowerCase() + "' ;");
				if (!(ris2.next())) {
					return "password errata";
				} else {
					return "autenticazione";
				}
			}
		} else {
			ResultSet ris2;
			if(Pass.equals("null")) {
				return "autenticazione";
			}
			else {
				ris2 = stmt.executeQuery("SELECT cf FROM cittadini_registrati " + "WHERE password = '" + Pass
						+ "' AND cf = '" + Email_CF.toLowerCase() + "' ;");
			}
			if (!(ris2.next())) {
				return "password errata";
			} else {
				return "autenticazione";
			}
		}

	}

	/**
	 * Il metodo ha lo scopo di ricercare tutti i centri vaccinali
	 * per nome
	 * 
	 * @param name rappresenta il nome del centro vaccinale
	 * 
	 * @return niente se non è presente nessun centro vaccinale che abbia quel nome
	 *         o che contegna quel nome altrimenti ritorna il / i centro/i che
	 *         corrispondono, altrimenti tutti i centri vaccinali che contengono quel nome
	 */
	public String research_forName(String name) throws SQLException {

		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet ris = stmt.executeQuery("SELECT * FROM centri_vaccinali WHERE nome LIKE '%" + name.toLowerCase() + "%' ;");
		String[] array = new String[3];
		ResultSetMetaData rsmd = ris.getMetaData();
		int numColonne = rsmd.getColumnCount();
		String nomeColonne = "";
		for (int nr = 0; nr < numColonne; nr++)
			nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
		String datiRighe = "";
		while (ris.next()) {
			String riga = "";
			for (int nr = 0; nr < numColonne; nr++) {
				riga += (ris.getObject(nr + 1).toString() + ":");
			}
			datiRighe += riga + "___________";
		}
		array[0] = nomeColonne;
		array[1] = datiRighe;

		if (!(ris.first())) {
			return "niente";
		}
		return (array[0] + "-" + array[1]);

	}

	/**
	 * Il metodo permette la ricerca di un centro
	 * vaccinale per Comune e Tipologia
	 * 
	 * @param Municipality comune del centro vaccinale
	 * @param Type         tipologia del centro vaccinale: hub,
	 *                     ospedaliero,aziendale
	 * 
	 * @return niente se non è presente nessun centro vaccinale con le richieste
	 *         volute, altrimenti tutti i centri vaccinali che rispettano i vincoli
	 */
	public String research_forType_Municipality(String Municipality, String Type) throws SQLException {

		ResultSet ris = null;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try {
			ris = stmt.executeQuery("SELECT * FROM centri_vaccinali WHERE comune = '" + Municipality.toLowerCase()
					+ "' AND tipologia = '" + Type.toLowerCase() + "' ;");
		} catch (PSQLException e) {
			return "niente";
		}
		String[] array = new String[3];
		ResultSetMetaData rsmd = ris.getMetaData();
		int numColonne = rsmd.getColumnCount();
		String nomeColonne = "";
		for (int nr = 0; nr < numColonne; nr++)
			nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
		String datiRighe = "";
		while (ris.next()) {
			String riga = "";
			for (int nr = 0; nr < numColonne; nr++) {
				riga += (ris.getObject(nr + 1).toString() + ":");
			}
			datiRighe += riga + "___________";
		}
		array[0] = nomeColonne;
		array[1] = datiRighe;

		if (!(ris.first())) {
			return "niente";
		}
		return (array[0] + "-" + array[1]);

	}

	/**
	 * Il metodo va a cercare tutti i centri vaccinali in cui un determinato
	 * cittadino si è vaccinato
	 * 
	 * @return niente se non è ancora stato vaccinato, lista dei centri vaccinali
	 *         altrimenti
	 */
	public String Spam() throws SQLException {

		ResultSet ris = null;
		ResultSet ris2 = null;
		String cf = "";

		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ris2 = stmt.executeQuery("SELECT cf FROM cittadini_registrati WHERE email = '" + Email_CF.toLowerCase() + "' ;");
		if (ris2.next()) {
			cf = ris2.getObject(1).toString();
		} else {
			cf = "";
		}
		ris = stmt.executeQuery(
				"SELECT DISTINCT centri_vaccinali.centro_vaccinale_id, centri_vaccinali.nome, centri_vaccinali.indirizzo, centri_vaccinali.comune,"
						+ " centri_vaccinali.provincia, centri_vaccinali.cap, centri_vaccinali.tipologia "
						+ "FROM centri_vaccinali JOIN vaccinazione_effettuata "
						+ "ON centri_vaccinali.centro_vaccinale_id = vaccinazione_effettuata.centro_vaccinale_id "
						+ "WHERE vaccinazione_effettuata.cf = '" + cf.toLowerCase()
						+ "' OR vaccinazione_effettuata.cf = '" + Email_CF.toLowerCase() + "';");
		String[] array = new String[3];
		ResultSetMetaData rsmd = ris.getMetaData();
		int numColonne = rsmd.getColumnCount();
		String nomeColonne = "";
		for (int nr = 0; nr < numColonne; nr++)
			nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
		String datiRighe = "";
		while (ris.next()) {
			String riga = "";
			for (int nr = 0; nr < numColonne; nr++) {
				riga += (ris.getObject(nr + 1).toString() + ":");
			}
			datiRighe += riga + "___________";
		}
		array[0] = nomeColonne;
		array[1] = datiRighe;

		if (!(ris.first())) {
			return "niente";
		}
		return (array[0] + "-" + array[1]);

	}

	/**
	 * Il metodo va a richiamare il metodo Spam
	 * 
	 * @param string contenente email o codice fiscale del cittadino
	 * 
	 * @exception SQLException se ci sono problemi con la query SQL
	 * 
	 * @return niente se non è ancora stato vaccinato, lista dei centri vaccinali
	 *         altrimenti
	 */
	public String setCF(String string) {
		Email_CF = string;
		String result = "";
		try {
			result = Spam();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Il metodo permette di ottenere informazioni
	 * (vaccinazioni e avversità) per il cittadino
	 * 
	 * @param id      centro vaccinale
	 * @param CFemail email o codice fiscale di un cittadino
	 * 
	 * @return lista di tutte vaccinazione eseguite e le eventuali avverità
	 * @throws Exception 
	 */
	public String visualizzaInfoCentroVaccinale(String id, String CFemail) throws Exception {
		
		ResultSet ris = null;

		ResultSet ris2 = null;

		String cf = "";

		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		if (!(CFemail == null)) {
			ris2 = stmt.executeQuery(
					"SELECT cf FROM cittadini_registrati WHERE email = '" + CFemail.toLowerCase() + "' ;");
			if (ris2.next()) {
				cf = ris2.getObject(1).toString();
			} else {
				cf = "";
			}
		}
		
		ris = stmt.executeQuery("SELECT id_vaccinazione FROM vaccinazione_effettuata NATURAL JOIN cittadini_registrati "
				+ "WHERE cf = '" + CFemail.toLowerCase() + "' OR cf = '" + cf.toLowerCase() + "';");
		
		String resultResponse = "";
		
		String CFforXACML = "";
		if(cf == "") {
			CFforXACML = CFemail;
		}
		else {
			CFforXACML = cf;
		}
		
		while(ris.next()) {
			String id_vaccinazione = ris.getString(1);
			checker = new XMLChecker();
			checker.initBalana();
			String resultCheckXACML = checker.resultCheck(CFforXACML, "show", id_vaccinazione);
						
			if(resultCheckXACML.equals("Deny")) {
				resultResponse = "Deny";
				break;
			}
			else if(resultCheckXACML.equals("Permit")){
				
				resultResponse = "Permit";
				
			}
			else {
				System.err.println("CI DEVE ESSERE UN ERRORE NEL CHECK, SICURAMENTE SARA' NEI PARAMETRI");
			}
			
		}
		
		if(resultResponse.equals("Permit")) {
		
			ris = stmt.executeQuery("SELECT t.id_vaccinazione, t.id_evento_avverso, t.evento, t.severita, t.note "
					+ "FROM eventi_avversi AS t NATURAL JOIN vaccinazione_effettuata "
					+ "WHERE vaccinazione_effettuata.centro_vaccinale_id = " + id
					+ " AND  vaccinazione_effettuata.cf = ALL (SELECT vaccinazione_effettuata.cf "
					+ "FROM vaccinazione_effettuata JOIN centri_vaccinali "
					+ "ON vaccinazione_effettuata.centro_vaccinale_id = centri_vaccinali.centro_vaccinale_id "
					+ "WHERE vaccinazione_effettuata.cf = '" + CFemail.toLowerCase()
					+ "' OR  vaccinazione_effettuata.cf = '" + cf.toLowerCase() + "' "
					+ "AND vaccinazione_effettuata.centro_vaccinale_id = " + id + ");");
			
			
			String[] array = new String[3];
			ResultSetMetaData rsmd = ris.getMetaData();
			int numColonne = rsmd.getColumnCount();
			String nomeColonne = "";
			for (int nr = 0; nr < numColonne; nr++)
				nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
			String datiRighe = "";
			while (ris.next()) {
				String riga = "";
				for (int nr = 0; nr < numColonne; nr++) {
					riga += (ris.getObject(nr + 1).toString() + ":");
				}
				datiRighe += riga + "___________";
			}
			array[0] = nomeColonne;
			array[1] = datiRighe;
	
			if (!(ris.first())) {
				return "niente";
			}
			return (array[0] + "-" + array[1]);
			
		}
		else if(resultResponse.equals("Deny")) {
			
			return "permesso_negato";
			
		}
		else return "niente";

	}

	/**
	 * Il metodo permette di ottenere informazioni
	 * (vaccinazioni e avversità) in modo anonimo
	 * 
	 * @param id centro vaccinale
	 * 
	 * @return lista di tutte vaccinazione eseguite e le eventuali avversità
	 */
	public String visualizzaInfoCentroVaccinale2(String id) throws SQLException {
		ResultSet ris = null;

		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ris = stmt.executeQuery("SELECT t.id_vaccinazione, t.id_evento_avverso, t.evento, t.severita, t.note "
				+ "FROM eventi_avversi AS t NATURAL JOIN vaccinazione_effettuata "
				+ "WHERE vaccinazione_effettuata.centro_vaccinale_id = " + id
				+ " AND vaccinazione_effettuata.id_vaccinazione = ALL (SELECT vaccinazione_effettuata.id_vaccinazione "
				+ "FROM vaccinazione_effettuata JOIN centri_vaccinali "
				+ "ON vaccinazione_effettuata.centro_vaccinale_id = centri_vaccinali.centro_vaccinale_id "
				+ "WHERE t.id_vaccinazione = vaccinazione_effettuata.id_vaccinazione AND vaccinazione_effettuata.centro_vaccinale_id = "
				+ id + " );");

		String[] array = new String[3];
		ResultSetMetaData rsmd = ris.getMetaData();
		int numColonne = rsmd.getColumnCount();
		String nomeColonne = "";
		for (int nr = 0; nr < numColonne; nr++)
			nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
		String datiRighe = "";
		while (ris.next()) {
			String riga = "";
			for (int nr = 0; nr < numColonne; nr++) {
				riga += (ris.getObject(nr + 1).toString() + ":");
			}
			datiRighe += riga + "___________";
		}
		array[0] = nomeColonne;
		array[1] = datiRighe;

		if (!(ris.first())) {
			return "niente";
		}
		return (array[0] + "-" + array[1]);

	}

	/**
	 * Il metodo permette di inserire un' eventuale avversità
	 * per il cittadino dopo essersi loggato
	 * 
	 * @param tipo    indica la tipologia di avversità
	 * @param severy  indica la severità dell'evento (1-5)
	 * @param note    commento opzionale riguardante l'avversità
	 * @param id1     id della vaccinazione
	 * @param cFEmail codice fiscale o email del cittadino
	 * @param
	 * 
	 * @return inserimento avvenuto se non ci sono stati problemi, trovato evento
	 *         avverso uguale se il cittadino prova ad inserire un medesimo evento
	 *         avverso già presente
	 */
	public String inserisciEventiAvversi(String tipo, int severy, String note, String id1, String cFEmail)
			throws Exception {

		ResultSet ris = null;
		
		String cf = "";
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		ris = stmt.executeQuery(
				"SELECT cf FROM cittadini_registrati NATURAL JOIN vaccinazione_effettuata WHERE id_vaccinazione = '" + id1 + "' ;");
		if (ris.next()) {
			cf = ris.getObject(1).toString();
		} else {
			cf = "";
		}
		
		ris = stmt.executeQuery(
				"SELECT eventi_avversi.id_vaccinazione, eventi_avversi.id_evento_avverso, "
					+ "eventi_avversi.evento, eventi_avversi.severita, eventi_avversi.note "
					+ "FROM eventi_avversi NATURAL JOIN vaccinazione_effettuata "
					+ "WHERE eventi_avversi.evento = '" + tipo + "' AND " + "eventi_avversi.id_vaccinazione = "
					+ id1 + " ;");

		if (ris.next()) {
			return "trovato evento avverso uguale!";
		} else {
			
			String resultResponse = "";
			
			String id_vaccinazione = id1;
			checker = new XMLChecker();
			checker.initBalana();
			String resultCheckXACML = checker.resultCheck(cf, "insert", id_vaccinazione);
			
			if(resultCheckXACML.equals("Deny")) {
				resultResponse = "Deny";
			}
			else if(resultCheckXACML.equals("Permit")){
				
				resultResponse = "Permit";
				
			}
			else {
				System.err.println("CI DEVE ESSERE UN ERRORE NEL CHECK, SICURAMENTE SARA' NEI PARAMETRI");
			}
			
			if(resultResponse.equals("Permit")) {
			
				int risultato = stmt.executeUpdate(
						"INSERT INTO eventi_avversi(id_evento_avverso, evento, severita, note, id_vaccinazione) "
								+ "VALUES(nextval('eventi_avversi_seq'), '" + tipo + "', " + severy + ", '" + note + "', "
								+ id1 + ");");
				if (risultato > 0) {
					deployAllRuleXACML();
					return "inserimento avvenuto";
				} else {
					return "inserimento fallito";
				}
			} else if(resultResponse.equals("Deny")) {
				
				return "permesso_negato";
				
			} else {
				
				return "errore";
				
			}

		}

	}

	/**
	 * Il metodo cerca tutte le vaccinazioni somministrate ad un
	 * cittadino
	 * 
	 * @param param      indica il centro vaccinale
	 * @param CFemail email o codice fiscale
	 * 
	 * @return 	niente se il cittadino non è stato ancora vaccinato,
	 * 			lista di tutte le vaccinazioni altrimenti
	 */
	public String popola_id_V(String param, String CFemail) throws SQLException {
		ResultSet ris = null;

		ResultSet ris2 = null;

		String cf = "";

		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		if (!(CFemail == null)) {
			ris2 = stmt.executeQuery(
					"SELECT cf FROM cittadini_registrati WHERE email = '" + CFemail.toLowerCase() + "' ;");
			if (ris2.next()) {
				cf = ris2.getObject(1).toString();
			} else {
				cf = "";
			}
		}

		ris = stmt.executeQuery("SELECT vaccinazione_effettuata.id_vaccinazione "
				+ "FROM vaccinazione_effettuata JOIN centri_vaccinali "
				+ "ON vaccinazione_effettuata.centro_vaccinale_id = centri_vaccinali.centro_vaccinale_id "
				+ "WHERE vaccinazione_effettuata.centro_vaccinale_id = '" + param.toString() + "'  "
				+ "AND (vaccinazione_effettuata.cf = '" + CFemail.toLowerCase() + "' OR  vaccinazione_effettuata.cf = '"
				+ cf.toLowerCase() + "')");

		String[] array = new String[3];
		ResultSetMetaData rsmd = ris.getMetaData();
		int numColonne = rsmd.getColumnCount();
		String nomeColonne = "";
		for (int nr = 0; nr < numColonne; nr++)
			nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
		String datiRighe = "";
		while (ris.next()) {
			String riga = "";
			for (int nr = 0; nr < numColonne; nr++) {
				riga += (ris.getObject(nr + 1).toString() + ":");
			}
			datiRighe += riga + "___________";
		}
		array[0] = nomeColonne;
		array[1] = datiRighe;

		if (!(ris.first())) {
			return "niente";
		}
		return (array[0] + "-" + array[1]);

	}

	/**
	 * Il metodo permette ad un cittadino di modificare un'avversità
	 * già presente
	 * 
	 * @param id_vaccinazione id della vaccinazione
	 * @param id              id dell'evento avverso
	 * @param evento          evento avverso
	 * @param severita        grado di severtià
	 * @param note            eventuali commenti (opzionale)
	 * 
	 * @return modifca avvenuta se non ci sono stati problemi, modifica fallita
	 *         altrimenti
	 */
	public String updateAvversita(String cf2, String id_vaccinazione, String id, String evento, String severita, String note)
			throws Exception {

		ResultSet ris = null;
		
		String cf = "";
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String resultResponse = "";
		
		ris = stmt.executeQuery(
				"SELECT cf FROM cittadini_registrati NATURAL JOIN vaccinazione_effettuata WHERE id_vaccinazione = '" + id_vaccinazione + "' ;");
		if (ris.next()) {
			cf = ris.getObject(1).toString();
		} else {
			cf = "";
		}
		
		String id_evento_avverso = id;
		checker = new XMLChecker();
		checker.initBalana();
		String resultCheckXACML = checker.resultCheck(cf, "modify", id_evento_avverso);
		
		if(resultCheckXACML.equals("Deny")) {
			resultResponse = "Deny";
		}
		else if(resultCheckXACML.equals("Permit")){
			
			resultResponse = "Permit";
			
		}
		else {
			System.err.println("CI DEVE ESSERE UN ERRORE NEL CHECK, SICURAMENTE SARA' NEI PARAMETRI");
		}
		
		if(resultResponse.equals("Permit")){
		
			int risultato = stmt.executeUpdate("UPDATE public.eventi_avversi " + "SET id_evento_avverso=" + id
					+ ", evento='" + evento.toLowerCase() + "', severita=" + severita + ", " + "id_vaccinazione="
					+ id_vaccinazione + ", note='" + note.toLowerCase() + "' " + "WHERE id_vaccinazione=" + id_vaccinazione
					+ " AND  id_evento_avverso=" + id + " ;");
			if (risultato > 0) {
				deployAllRuleXACML();
				return "modifica avvenuta";
			} else {
				return "modifica fallita";
			}
		} else if (resultResponse.equals("Deny")) {
			
			return "permesso_negato";
			
		}
		else {
			return "error";
		}

	}

	/**
	 * Il metodo rimuove una avversità selezionata da un utente
	 * 
	 * @param id id rappresentante l'avversità 
	 * 
	 * @return cancellazione avvenuta se non ci sono stati problemi, cancellazione fallita se ci sono stati dei problemi
	 */
	public String deleteAvversita(String id1, String id) throws Exception {

		ResultSet ris = null;
		
		String cf = "";
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String resultResponse = "";
		
		ris = stmt.executeQuery(
				"SELECT cf FROM cittadini_registrati NATURAL JOIN vaccinazione_effettuata WHERE id_vaccinazione = '" + id1 + "' ;");
		if (ris.next()) {
			cf = ris.getObject(1).toString();
		} else {
			cf = "";
		}
		
		String id_evento_avverso = id;
		checker = new XMLChecker();
		checker.initBalana();
		String resultCheckXACML = checker.resultCheck(cf, "delete", id_evento_avverso);
		
		if(resultCheckXACML.equals("Deny")) {
			resultResponse = "Deny";
		}
		else if(resultCheckXACML.equals("Permit")){
			
			resultResponse = "Permit";
			
		}
		else {
			System.err.println("CI DEVE ESSERE UN ERRORE NEL CHECK, SICURAMENTE SARA' NEI PARAMETRI");
		}
		
		if(resultResponse.equals("Permit")){
		
			int risultato = stmt.executeUpdate("DELETE FROM eventi_avversi WHERE id_evento_avverso = " + id + " ;");
			if (risultato > 0) {
				deployAllRuleXACML();
				return "cancellazione avvenuta";
			} else {
				return "cancellazione fallita";
			}
		} else if (resultResponse.equals("Deny")) {
			
			return "permesso_negato";
			
		}
		else {
			return "error";
		}
	}
	
	public int inserisciUser(String email, String pass) {

		int result = 0;
		
		try {

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

			ResultSet ris = stmt.executeQuery(
					"SELECT iduser FROM auth " + "WHERE email = '" + email + "';");

			if (ris.first()) {
				System.out.println("Operatore già presente");
				result = -1;
				return result;
			}

			result = stmt.executeUpdate(
					"INSERT INTO auth(iduser, email, password) VALUES(nextval('id_user_seq'),"
							+ "'" + email + "'," + "'" + pass + "');");

			if (result > 0) { 
				System.out.println("Operatore inserito");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String checkUser(String email, String pass, String checkTypeLoginSource, String CF) throws SQLException{

		String result = "error";
		ResultSet ris = null;
		
		try {

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo
			Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo
			Statement stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

			if(checkTypeLoginSource.equals("null")) {
				ris = stmt.executeQuery(
						"SELECT iduser FROM auth " + "WHERE email = '" + email + "';");
			}
			else if (checkTypeLoginSource.equals("oauth")){
				
				ris = stmt.executeQuery(
						"SELECT token FROM type_user " + "WHERE email = '" + email + "' and cf = '" + CF + "';");
				
			}
			if (ris.first()) {
				
				ResultSet ris2 = null;
				if(checkTypeLoginSource.equals("null")) {
					ris2 = stmt2.executeQuery(
							"SELECT iduser FROM auth " + "WHERE email = '" + email + "' AND password = '" + pass + "';");
				}
				else if(checkTypeLoginSource.equals("oauth")) {
					
					ris2 = stmt2.executeQuery(
							"SELECT token FROM type_user " + "WHERE email = '" + email + "' AND password = '" + pass + "' and cf = '" + CF + "';");
					
				}

				if (ris2.first()) { 
					
					if(checkTypeLoginSource.equals("null")) {
						System.out.println("Login avvenuto con successo");
						result = "correct";
					}
					else if(checkTypeLoginSource.equals("oauth")) {
						
						
						String timestampNew = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(Calendar.getInstance().getTime());
						
						Boolean ris3 = stmt3.execute("UPDATE type_user SET tymestamp = '" + timestampNew + "' WHERE email = '" + email + "' AND password = '" + pass + "' and cf = '" + CF + "';");
						
						if(!ris3) {
							
							result = "Token OAuth aggiornato";
							
						}
						else {
							System.out.println("Errore nell'aggiornamento del timestamp");
							result = "Errore nell'aggiornamento del timestamp";
						}
						
					}
				}
				else {
					System.out.println("Password sbagliata");
					result = "Password o Codice Fiscale sbagliati";
				}
				
			}
			else {
				System.out.println("Email inesistente");
				result = "Email inesistente";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
		
	}

	public String presenceUserOAuth(String token) throws SQLException{
		
		try {
			ResultSet ris = null;
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

			ris = stmt.executeQuery("SELECT iduser FROM auth WHERE email = '" + token+ "';");

			if (ris.first()) { 
				return "0";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "-1";
	}

	public int deleteUserOAuthOperatori(String token , String project) throws SQLException{
		
		Boolean result = false;
		
		try {
			if(project.equals("operatori")) {
				String check = this.presenceUserOAuth(token);
				
				if(check.equals("0")) {
					
					Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo
		
					result = stmt.execute("DELETE FROM auth WHERE email = '" + token+ "';");
		
					if (!result) { 
						return 0;
					}
				}
				else if (check.equals("-1")) {
					return -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -2;
	}

	public String deployAllRuleXACML() throws Exception{
		
		String result = "error";
		try {
			ResultSet ris = null;
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

			ris = stmt.executeQuery("SELECT DISTINCT cf FROM vaccinazione_effettuata NATURAL JOIN cittadini_registrati;");
			
			policyCittadini.setPolicyId("policycittadiniInsertShow");

	        Actionss action1 = new Actionss();
	        Actionss action2 = new Actionss();
	        
			action1.setAttributeValueAllOf("show");
	        action2.setAttributeValueAllOf("insert");
	        
	        policyCittadini.setActionss(action1);
	        policyCittadini.setActionss(action2);
	        
	        System.out.println(policyCittadini.getActionss());
	        
				while (ris.next()) {
					
					Ruless rule1 = new Ruless();
			        Resourcess resources1 = null;
			        
			        rule1.setRuleAtt("rule_" + ris.getString(1));
			        rule1.setEffectAtt("Permit");
			        
			        rule1.setAttributeValueSubject(ris.getString(1));
			        
			        ResultSet ris2 = null;
					Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

					ris2 = stmt2.executeQuery("SELECT id_vaccinazione FROM vaccinazione_effettuata WHERE cf = '" + ris.getString(1) + "';");
			        
					while(ris2.next()) {	
						
						resources1 = new Resourcess();
				        resources1.setResourcename(ris2.getString(1));
				        rule1.setResources(resources1);
				        System.out.println(rule1.getResources());

					}
			        
			        policyCittadini.setRules(rule1);
				}
				
				marshaller = jc.createMarshaller();
			    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		        marshaller.marshal(policyCittadini, fileShowInsert);
		        
			if (!(ris.first())){
				
				result = "not";
	        	
			}
			else {
				result = "yes";
			}
			
			System.out.println(result);
			
			ris = stmt.executeQuery("SELECT DISTINCT cf FROM vaccinazione_effettuata NATURAL JOIN eventi_avversi;");
			
			policyCittadini2.setPolicyId("policycittadiniModifyDelete");
			
	        Actionss action3 = new Actionss();
	        Actionss action4 = new Actionss();
	        
	        action3.setAttributeValueAllOf("modify");
	        action4.setAttributeValueAllOf("delete");
	        
	        policyCittadini2.setActionss(action3);
	        policyCittadini2.setActionss(action4);
	        
	        System.out.println(policyCittadini2.getActionss());
		        
		    while (ris.next()) {
		        	
		    	Resourcess resources1 = null;
		        Ruless rule1 = new Ruless();
		        rule1.setRuleAtt("rule_" + ris.getString(1));
		        rule1.setEffectAtt("Permit");
		        
		        rule1.setAttributeValueSubject(ris.getString(1));
		        
				Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

				ResultSet ris2 = stmt2.executeQuery("SELECT id_evento_avverso FROM vaccinazione_effettuata NATURAL JOIN eventi_avversi WHERE cf = '" + ris.getString(1) + "';");
		        
				while(ris2.next()) {	
					
					resources1 = new Resourcess();
			        resources1.setResourcename(ris2.getString(1));
			        rule1.setResources(resources1);
			        System.out.println(rule1.getResources());

				}
		        
				policyCittadini2.setRules(rule1);
			}
			
			marshaller = jc.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(policyCittadini2, fileModifyDelete);
	        
		if (!(ris.first())){
			
			result = "not";
        	
		}
		else {
			result = "yes";
		}
		
		System.out.println(result);
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("C'e' stato qualache problema");
		}
		return result;
	}
}

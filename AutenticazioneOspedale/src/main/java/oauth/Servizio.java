package oauth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * E' la classe che fornisce i metodi principali che si occupano di interrogare il database eseguendo la query di interesse.
 * 
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */
public class Servizio{
	private Connection conn;
	private String return_ = "";
	private Servizio servizio = this;
	/**
	 * Il costruttore inizializza la connessione con il database
	 * 
	 * @param conn rappresenta la connessione stabilita con il database
	 * @throws Exception 
	 */
	 public Servizio(Connection conn) throws Exception {
		this.conn = conn;
		
	 }
	 public String openOauth(String login, String operatori) throws Exception {
		
		Thread thread = Thread.currentThread();
		 
		Runnable r = new Runnable() {
	         public void run() {
	        	 
	        	 OAuthGestione gestione = null;
				try {
					gestione = new OAuthGestione(thread, servizio, login, operatori);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     		gestione.setVisible(true);
	        	 
	         }
	     };
	    
	    new Thread(r).start();
			    
	    if(return_ == "") {
	    	
	    	try {
	    		synchronized (thread){
	    		       try{
	    		          thread.wait();
	    		          System.out.println("valore : " + return_ + ":   sono qui 1");
	    		          return return_;
	    		       } catch (InterruptedException e) {
	    		          e.printStackTrace();
	    		       }
    		    }
			} catch (Exception e) { 
				e.printStackTrace();
                System.err.println("Thread Interrupted");
			}
	 
	    	System.out.println("valore : " + return_ + ":   sono qui 2");	    
	    	return return_;
		
	   }
	   System.out.println("valore : " + return_ + ":   sono qui 3");
	   return return_;
	    
	 }
	
	 public void setReturn(String value) {
		 return_ = value;
	 }
	 
	 public String allUserOAuth(String project) throws SQLException{
			
			ResultSet ris = null;
			String[] array = new String[3];

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String typeProject = "";
			if(project.equals("operatori")) {
				
				typeProject = "operatore";
				
			}
			else if(project.equals("cittadini")) {
				
				typeProject = "cittadino";
				
			}
			
			ris = stmt.executeQuery("SELECT type, token, cf, email, tymestamp FROM type_user WHERE type = '" + typeProject + "';");
			if (!(ris.first())) {
				return "niente";
			}
			else {
				array = new String[5];
				ResultSetMetaData rsmd = ris.getMetaData();
				int numColonne = rsmd.getColumnCount();
				String nomeColonne = "";
				for (int nr = 0; nr < numColonne; nr++)
					nomeColonne += (rsmd.getColumnName(nr + 1).toString() + ":");
				String datiRighe = "";
				ris.beforeFirst();
				while (ris.next()) {
					String riga = "";
					for (int nr = 0; nr < numColonne; nr++) {
						if(ris.getObject(nr + 1) != null) {
							riga += (ris.getObject(nr + 1).toString() + ":");
						}
						else {
							riga += ":";
						}
					}
					datiRighe += riga + "___________";
				}
				array[0] = nomeColonne;
				array[1] = datiRighe;
			}
			return (array[0] + "-" + array[1]);
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
	 
		public int inserNewUserOauth(String email, String password, String tymestamp, String cFString, String project) {
			
			int result = 0;
			
			try {

				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo
				
				ResultSet ris = null;
				
				if(project.equals("operatori")) {
					
					ris = stmt.executeQuery(
							"SELECT id_user FROM type_user " + "WHERE email = '" + email + "' AND token LIKE 'OP%';");
				}
				else if (project.equals("cittadini")) {
					
					ris = stmt.executeQuery(
							"SELECT id_user FROM type_user " + "WHERE email = '" + email + "' AND cf = '" + cFString + "' AND token LIKE 'CT%';");
				}

				if (ris.first()) {
					System.out.println("Operatore già presente");
					result = -1;
					return result;
				}
				
				String[] pezziEmail = email.split("@");
				String[] dopoChiocciola = pezziEmail[1].split("");
				String dopo = dopoChiocciola[1] + dopoChiocciola[3] + dopoChiocciola[5];
				String[] primaChiocciola = pezziEmail[0].split("");
				String prima = "";
				for(int i = 0; i < primaChiocciola.length; i++) {
					if(i%2 == 0) {
						prima += primaChiocciola[i];
					}
				};
				
				String typeProject = "";
				
				if(project.equals("operatori")) {
					
					typeProject = "OP";
					
				}
				else if(project.equals("cittadini")) {
					
					typeProject = "CT";
					
				}
				
				String cfToken = "";
				if(project.equals("cittadini")) {
					
					String[] cfTokenSplit = cFString.split("");
					for(int i = 0; i < cfTokenSplit.length; i++) {
						if(i%3 == 0) {
							cfToken += cfTokenSplit[i];
						}
					};
					
				}
				
				System.out.println(tymestamp);
				
				String token = typeProject + (prima + dopo).toUpperCase() + cfToken;
				
				String timestampTokenWithoutPunto = tymestamp.replace(".", "");
				String timestampTokenWithoutSlash = timestampTokenWithoutPunto.replace("/", "");
				String timestampToken = timestampTokenWithoutSlash.replace(" ", "");
				
				System.out.println(timestampToken);
				
				token += timestampToken;
				
				String[] passTokenSplit = password.split("");
				String passToken = "";
				for(int i = 0; i < passTokenSplit.length; i++) {
					if(i%2 == 0) {
						passToken += passTokenSplit[i];
					}
				};
				
				token += passToken.toUpperCase();
				
				System.out.println(token);
				
				if(project.equals("operatori")) {
					result = stmt.executeUpdate(
							"INSERT INTO type_user(id_user, type, cf, token, email, tymestamp, password) VALUES(nextval('id_user_seq'), 'operatore', 'null', '"+ token +"', "
									+ "'" + email + "'," + "'" + tymestamp+ "', '" + password + "');");
				}
				else if(project.equals("cittadini")) {
					result = stmt.executeUpdate(
							"INSERT INTO type_user(id_user, type, cf, token, email, tymestamp, password) VALUES(nextval('id_user_seq'), 'cittadino', '" + cFString + "', '"+ token +"', "
									+ "'" + email + "'," + "'" + tymestamp+ "', '" + password + "');");
				}

				if (result > 0) { 
					System.out.println("Operatore inserito");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;
		}
		public int deleteUserOAuth(String token) throws SQLException{
			
			Boolean result = false;
			
			try {

				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Preparo

				result = stmt.execute("DELETE FROM type_user WHERE token = '" + token+ "';");

				if (!result) { 
					return 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return -1;
		}
}

package oauth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;

/**
 * E' la classe che gestisce tutte le richieste inviate
 * <p>da ogni client in quanto ad ogni richiesta corrisponde un singolo thread Backend
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */

public class Backend extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Connection conn;
	private Servizio servizio; /*Rappresenta tutte le query possibili*/

	/**
	 * Il costruttore inizializza socket e gli oggetti di I/O
	 * 
	 * @param socket socket su cui si mette in ascolto il server
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 */
	public Backend(Socket socket) throws IOException {
		this.socket = socket;
		in = null;
		conn = null;
		servizio = null;

		start();
	}

	/**
	 * Il metodo run è il metodo che viene eseguito dal thread
	 * Vengono creati gli oggetti input ed output e in seguito si mette in attesa di ricevere
	 * richieste.
	 * Ogni richiesta viene controllata e viene gestita con il metodo corrispondente della classe Servizio
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * @exception Exception se il server è disconnesso
	 */
	public void run(){
		System.out.println("Thread numero: " + this.getId());

		Connessione connessione = new Connessione(Server.username, Server.pwd, Server.host, Server.port, Server.dbName);
		conn = connessione.connetti();

		try {
			servizio = new Servizio(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*Creo un oggetto Servizio passando la connessione al database*/

		String richiesta = "";
		String[] param = null;

		/*Creazione oggetti I/O*/
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			while (true) {
				try {
					richiesta = in.readLine(); /*Aspetto una nuova richiesta*/
				} catch (Exception e) {
					System.out.println("Client Disconnesso");
					break;
			
				}

				if (richiesta != null) {
					System.out.println("RICHIESTA->" + richiesta); //
					param = richiesta.split(":"); /*Procedo a segmentare la mia richiesta attraverso i ':'*/

					if (param[0].equals("openOauth")) {

						String result = servizio.openOauth(param[1], param[2]);
						if (out != null) {
							out.println(result);
						}
						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					}

//					else if (param[0].equals("inserisciVN")) {
//						int result = servizio.inserisciVN(param[1], param[2], param[3], param[4], param[5], param[6]);
//
//						if (out != null) {
//							out.println(result);
//						}
//
//						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
//					}
					else {
						System.out.println("Errore nell'input di param[0]");
					}

					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				/*Chiudo tutti gli stream I/O e la socket*/
				System.out.println("Thread terminato" + "\n");
				in.close();
				out.close();
				socket.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

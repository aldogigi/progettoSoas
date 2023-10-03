package server;

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
 * @author Andrea Ferro 740958 VA
 * @author Gianluca Fontana 742393 VA
 * @author Manuel Nguyen 741939 VA 
 * @author Digvijaysinh D. Raj 741976 VA
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
	public void run() {
		System.out.println("Thread numero: " + this.getId());

		Connessione connessione = new Connessione(Server.username, Server.pwd, Server.host, Server.port, Server.dbName);
		conn = connessione.connetti();

		servizio = new Servizio(conn); /*Creo un oggetto Servizio passando la connessione al database*/

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

					if (param[0].equals("inserisciCV")) {

						int result = servizio.inserisciCV(param[1], param[2], param[3], param[4],
								Integer.parseInt(param[5]), param[6], param[7], Integer.parseInt(param[8]));
						if (out != null) {
							out.println(result);
						}
						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					}

					else if (param[0].equals("inserisciVN")) {
						int result = servizio.inserisciVN(param[1], param[2], param[3], param[4], param[5], param[6]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					}

					else if (param[0].equals("popolaCV")) {
						String result = servizio.popolaCV();

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("inserisciCittadini")) {
						String result = servizio.registraCittadino(param[1], param[2], param[3]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("CheckCittadini")) {
						String result = servizio.CheckCittadini(param[1], param[2]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("research_forName")) {
						String result = servizio.research_forName(param[1]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("research_forType_Municipality")) {
						String result = servizio.research_forType_Municipality(param[1], param[2]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("setCF")) {
						String result = servizio.setCF(param[1]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("selectAvversita")) {
						String result = servizio.visualizzaInfoCentroVaccinale(param[1], param[2]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("selectAvversita2")) {
						String result = servizio.visualizzaInfoCentroVaccinale2(param[1]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("insertAvversita")) {
						String result = servizio.inserisciEventiAvversi(param[1], Integer.parseInt(param[2]), param[3],
								param[4], param[5]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("popola_id_V")) {
						String result = servizio.popola_id_V(param[1], param[2]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");
					} else if (param[0].equals("updateAvversita")) {
						String result = servizio.updateAvversita(param[1], param[2], param[3], param[4], param[5]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");

					} else if (param[0].equals("deleteAvversita")) {
						String result = servizio.deleteAvversita(param[1]);

						if (out != null) {
							out.println(result);
						}

						System.out.println("Il thread " + this.getId() + " ha finito \n---------------------------");

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

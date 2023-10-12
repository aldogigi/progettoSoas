package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * E' la classe che contiene il metodo main che procede a creare una ServerSocket <p>specificando
 *  la porta corretta e in seguito si mette in attesa in un ciclo infinito in cui 
 * <p>aspetta che un client richieda una connessione al server e procede a gestire tale richiesta
 * <p>avviando un nuovo thread (Backend) a cui viene passato un nuovo Socket in quanto ogni thread 
 * <p>ha la propria connessione.
 * <p>Questo permette di avere più client che lavorano parallelamente
 * 
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */
public class Server {
	
	public static String username, pwd, host, port, dbName, portServiziOauth;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
		System.out.print("Inserisci la porta:\n4444 per ServerServizi e 4445 per ServerOauth:\n");
		portServiziOauth = in.readLine(); 
		
		int PORT = Integer.parseInt(portServiziOauth); /*Porta su cui gira la ServerSocket*/
		
		System.out.print("Inserisci l'username per accedere al database: ");
		username = in.readLine(); 
		
		System.out.print("Inserisci la password per accedere al database: ");
		pwd = in.readLine(); 
		
		System.out.print("Inserisci l'host del database: ");
		host = in.readLine(); 
		
		System.out.print("Inserisci la porta su cui gira PostgreSQL (5432 default): ");
		port = in.readLine(); 
		
		System.out.print("Inserisci il nome del  database: ");
		dbName = in.readLine(); 
		
		Connessione conn = new Connessione(username, pwd, host, port, dbName);
		conn.connetti();
		conn.chiudiConn();
		
		ServerSocket s = new ServerSocket(PORT);  /*Avvio il canale di comunicazione sulla porta specifica*/
		System.out.println("\nSERVER ACCESO\n");

		try {
			while (true) { /*Attesa infinita di una nuova richiesta di connessione*/
				System.out.println("IN ATTESA DI CONNESSIONE"); 
				Socket socket = s.accept();  /*Metodo bloccante che aspetta di ricevere una richiesta di connessione*/
				System.out.println("QUALCUNO SI È COLLEGATO AL SERVER");
				try {
					new Backend(socket); /*Ad ogni nuova richiesta di connessione viene avviato un thread con la Socket apposita*/
				} catch (IOException e) {
					socket.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Client Disconnesso");
		} finally {
			s.close();
		}
	}
}

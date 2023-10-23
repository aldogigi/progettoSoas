package cittadini;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe permette al cittadino che si è vaccinato almeno una volta di potersi registrarsi
 * <p>e di poter, oltre ad accedere alle funzionalità libere, inserire eventuali avversità 
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */
public class Registrazione_Cittadini2 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CF;
	private JTextField Email;
	private JPasswordField Pass;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JButton SignInButton;
	private JLabel toLogin;

	private JLabel ErrorLabel;

	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");
	private Pattern CF_REGEX = Pattern.compile("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$");
	private JPasswordField ConfirmPass;
	private JButton btnAutoLogin;
	/*
	 * La password deve avere una lunghezza di almeno otto (8) caratteri in cui il
	 * sistema può supportarla. Le password devono includere caratteri di almeno due
	 * (2) di questi raggruppamenti: caratteri alfa, numerici e speciali.
	 */

	/**
	 * Crea l'interfaccia grafica che mostra un form tramite quale l'utente può registrarsi
	 * 
	 * @exception Exception se non si riesce a comunicare con il server o se il server è disconnesso
	 */
	public Registrazione_Cittadini2() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CF = new JTextField();
		CF.setBounds(132, 93, 285, 20);
		contentPane.add(CF);
		CF.setColumns(10);

		Email = new JTextField();
		Email.setBounds(132, 143, 285, 20);
		contentPane.add(Email);
		Email.setColumns(10);

		Pass = new JPasswordField();
		Pass.setBounds(132, 195, 285, 20);
		Pass.setToolTipText(
				"<html> La password deve avere: <br>       Almeno un carattere maiuscolo <br>       Almeno un carattere minusculo <br>       Almeno un carattere speciale <br>       Almeno un numero <br> 		 Deve essere lunga almeno 8 caratteri.</html> ");
		contentPane.add(Pass);
		Pass.setColumns(10);

		lblNewLabel_2 = new JLabel("CF:");
		lblNewLabel_2.setBounds(132, 73, 285, 17);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(132, 124, 285, 17);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setBounds(132, 174, 285, 17);
		contentPane.add(lblNewLabel_5);

		SignInButton = new JButton("Registrati");
		SignInButton.setBounds(208, 284, 124, 23);
		contentPane.add(SignInButton);

		toLogin = new JLabel("Sei già registrato? Accedi");
		toLogin.setHorizontalAlignment(SwingConstants.CENTER);
		toLogin.setBounds(132, 318, 285, 14);
		contentPane.add(toLogin);
		toLogin.setForeground(Color.BLUE.darker());
		toLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ErrorLabel = new JLabel("Riscontro");
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setBounds(10, 428, 528, 45);
		contentPane.add(ErrorLabel);

		JLabel lblNewLabel_5_1 = new JLabel("Conferma Password:");
		lblNewLabel_5_1.setBounds(132, 226, 285, 17);
		contentPane.add(lblNewLabel_5_1);

		ConfirmPass = new JPasswordField();
		ConfirmPass.setColumns(10);
		ConfirmPass.setBounds(132, 247, 285, 20);
		contentPane.add(ConfirmPass);
		
		btnAutoLogin = new JButton("");
		btnAutoLogin.setBounds(208, 342, 124, 87);
		
		System.out.println(System.getProperty("user.dir"));
		ImageIcon icon = new ImageIcon("image/oauth.png");
		btnAutoLogin.setIcon(icon);
		btnAutoLogin.setBorder(BorderFactory.createEmptyBorder());
		btnAutoLogin.setContentAreaFilled(false);
		btnAutoLogin.setFocusable(false);
		
		btnAutoLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 dispose();
				 ProcessBuilder builder = new ProcessBuilder(
				            "cmd.exe", "/c", "java -jar AutenticazioneOspedale\\target\\AutenticazioneOspedale-1.0.jar registrazione cittadini");
				        builder.redirectErrorStream(true);
				        Process p;
						try {
							p = builder.start();
							BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					        String line;
					        while (true) {
					            line = r.readLine();
					            if (line == null) { break; }
					            System.out.println(line);
					        }
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				        
			}
		});
		
		contentPane.add(btnAutoLogin);

		ErrorLabel.setVisible(false);

		toLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				(new Home2()).setVisible(true);
				dispose();

			}
		});
		SignInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ProxyServer proxy = null;
				
				ErrorLabel.setForeground(Color.RED);
				
				if (CF.getText().toString().length() == 0 || Email.getText().toString().length() == 0
						|| String.valueOf(Pass.getPassword()).length() == 0) {
					ErrorLabel.setText("Casella CF o Email o Password mancanti");
					ErrorLabel.setVisible(true);
				} else if (CF.getText().toString().length() != 16) {
					ErrorLabel.setText("Il codice fiscale deve essere lungo 16 caratteri");
					ErrorLabel.setVisible(true);
				} else if (!(CF.getText().toString().equals((CF.getText().toString()).toUpperCase()))) {
					ErrorLabel.setText("Il codice fiscale ha tutte lettere maiuscole");
					ErrorLabel.setVisible(true);
				}
				else if (!(validateCF(CF.getText().toString()))) {
					ErrorLabel.setText("forma scorretta per il codice fiscale");
					ErrorLabel.setVisible(true);
				}
				else if (!(validateEmailAddress(Email.getText().toString()))) {
					ErrorLabel.setText("forma scorretta per l'email");
					ErrorLabel.setVisible(true);
				} 
				else if (!(validatePassword(String.valueOf(Pass.getPassword())))) {

					ErrorLabel.setText("forma scorretta per la password");
					ErrorLabel.setVisible(true);
				} else if (!(String.valueOf(Pass.getPassword()).equals(String.valueOf(ConfirmPass.getPassword())))) {
					ErrorLabel.setText("Password e Conferma Password sono differenti");
					ErrorLabel.setVisible(true);
				} else {

					try {
						proxy = new ProxyServer();
					} catch (IOException e) {
						e.printStackTrace();
					}

					String risultato = "";
					try {
						risultato = proxy.registraCittadino(CF.getText().toString(), Email.getText().toString(),
								String.valueOf(Pass.getPassword()));

					} catch (Exception e1) {
						ErrorLabel.setText("Server Disconnesso");
                        ErrorLabel.setVisible(true);
					}
					if (risultato.equals("L'utente e' gia' registrato")) {

						ErrorLabel.setText("L'utente e' gia' registrato");
						ErrorLabel.setVisible(true);
					} else if (risultato.equals("L'utente non si e' ancora vaccinato")) {
						ErrorLabel.setText("L'utente non si e' ancora vaccinato");
						ErrorLabel.setVisible(true);
					} else if (risultato.equals("inserimento avvenuto")) {
						ErrorLabel.setForeground(Color.GREEN);
						ErrorLabel.setText("inserimento avvenuto");
						ErrorLabel.setVisible(true);
					}
				}

			}
		});

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("SignIn");

	}

	/**
	 * Metodo che tramite una regex controlla l'email inserita dall'utente
	 * 
	 * @param email email del cittadino,
	 * @return 	true se l'email è scritta in un formato corretto
	 * 		   	false altrimenti
	 */
	public boolean validateEmailAddress(String email) {
		final Matcher matcher = EMAIL_REGEX.matcher(email);
		return matcher.matches();
	}

	/**
	 * Metodo che tramite una regex controlla la password inserita dall'utente
	 * 
	 * @param pass password inserita al cittadino
	 * @return 	true se la password è scritta in una forma corretta,
	 * 		   	false altrimenti
	 */
	public boolean validatePassword(String pass) {
		final Matcher matcher = PASSWORD_REGEX.matcher(pass);
		return matcher.matches();
	}
	
	/**
	 * Metodo che tramite una regex controlla il codice fiscale inserito dall'utente
	 * 
	 * @param cf codice fiscale inserito dell'utente
	 * @return 	true se il codice fiscale è scritto in un formato corretto,
	 * 		   	false altrimenti
	 */
	public boolean validateCF(String cf) {
		final Matcher matcher = CF_REGEX.matcher(cf);
		return matcher.matches();
	}
}

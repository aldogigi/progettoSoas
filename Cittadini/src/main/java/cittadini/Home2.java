
package cittadini;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * E' la classe principale contenente il main.
 * <p> Viene mostrare la schermata principale che permette di autenticarsi, registrarsi e
 * <p> accedere alle funzionalità libere
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */

public class Home2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private Homepage_cittadini2 Homepage;
	private Registrazione_Cittadini2 Registrazione;

	private JPanel contentPane;
	private JTextField Email_CF;
	private JPasswordField password;

	private JLabel toSignIn;
	private JLabel FreeAccess;
	private JLabel lblNewLabel;
	private JLabel lblInserisciLaPassword;

	private JTabbedPane TabbedPane;
	private JPanel panel;

	public static JLabel response;

	/**
	 * Il costruttore crea l'interfaccia grafica principale
	 * @param cf 
	 * @param args2 
	 * @param args 
	 * 
	 * @exception Expceton se non si riesce a comunicare con il server o se il server è disconnesso
	 */
	public Home2(String checkOauthLogin, String token, String cf) {
		super("Login Cittadini");
		
		
		setBounds(100, 100, 516, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Email_CF = new JTextField();
		Email_CF.setBounds(149, 49, 197, 20);
		contentPane.add(Email_CF);
		Email_CF.setColumns(10);

		password = new JPasswordField();
		password.setBounds(149, 91, 197, 20);
		contentPane.add(password);
		password.setColumns(10);

		Registrazione = new Registrazione_Cittadini2();

		toSignIn = new JLabel("Non ti sei ancora registrato? Registrati");
		toSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		toSignIn.setBounds(126, 160, 243, 14);
		contentPane.add(toSignIn);
		toSignIn.setForeground(Color.BLUE.darker());
		toSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblNewLabel = new JLabel("Inserisci Email o CF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(149, 34, 197, 14);
		contentPane.add(lblNewLabel);

		lblInserisciLaPassword = new JLabel("Inserisci la password");
		lblInserisciLaPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciLaPassword.setBounds(149, 80, 197, 12);
		contentPane.add(lblInserisciLaPassword);

		response = new JLabel("New label");
		response.setHorizontalAlignment(SwingConstants.CENTER);
		response.setBounds(10, 280, 482, 20);
		response.setForeground(Color.RED);
		contentPane.add(response);
		response.setVisible(false);

		JButton AccessButtom = new JButton("Accedi");
		AccessButtom.setBounds(206, 121, 85, 23);
		contentPane.add(AccessButtom);

		toSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registrazione.setVisible(true);
				dispose();

			}
		});

		Homepage = new Homepage_cittadini2();
		TabbedPane = Homepage.getTabbedPane();
		panel = Homepage.getPanel();
		
		FreeAccess = new JLabel("Accesso Libero");
		FreeAccess.setHorizontalAlignment(SwingConstants.CENTER);
		FreeAccess.setBounds(149, 185, 197, 14);
		contentPane.add(FreeAccess);
		FreeAccess.setForeground(Color.BLUE.darker());
		FreeAccess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btnAutoLogin = new JButton("");
		btnAutoLogin.setBounds(206, 202, 85, 68);
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
				            "cmd.exe", "/c", "java -jar AutenticazioneOspedale\\target\\AutenticazioneOspedale-1.0.jar login cittadini");
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
		

		FreeAccess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Homepage.setVisible(true);
				Homepage.setTitle("Accesso Libero");
				TabbedPane.remove(panel); 
				dispose();
			}
		});
		AccessButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProxyServer proxy = null;
				
				String res = "";
				if (Email_CF.getText().toString().length() == 0
						|| String.valueOf(password.getPassword()).length() == 0) {
					response.setText("Casella della Password o Email/CF vuota");
					response.setVisible(true);
					
				} else if (!(Email_CF.getText().toString().length() == 0
						|| String.valueOf(password.getPassword()).length() == 0)) {
					
					try {
						proxy = new ProxyServer();
					} catch (Exception x) {
						x.printStackTrace();
					}
					
					try {
						res = proxy.CheckCittadini(Email_CF.getText().toString(),
								String.valueOf(password.getPassword()));
					} catch(Exception e1) {
						response.setText("Server Disconnesso");
                        response.setVisible(true);
					}
					if (res.equals("autenticazione")) {
						//System.out.println("Autenticazione");
						try {
							proxy = new ProxyServer();
							String result = proxy.setCF(Email_CF.getText().toString());
							Homepage.set(result);
							Homepage.setCFnormale(Email_CF.getText().toString());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Homepage.setTitle("Accesso da Autenticato");
						Homepage.goSignIn().setVisible(false);
						Homepage.goLogIn().setText("Logout");
						Homepage.setVisible(true);
						Homepage.Severita();
						dispose();
					} else if (res.equals("non ho trovato ne' CF ne' Email sul Db, Registrati!")) {
						response.setText("non ho trovato ne' CF ne' Email sul Db, Registrati!");
						response.setVisible(true);
					} else if (res.equals("password errata")) {
						response.setText("password errata");
						response.setVisible(true);
					} else if (res.equals("Il codice fiscale ha tutte lettere maiuscole")) {
						response.setText("Il codice fiscale ha tutte lettere maiuscole");
						response.setVisible(true);
					}
				}

			}
		});

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cittadini - Login");
		setVisible(true);
		
		if(checkOauthLogin.equals("true")) {
			
			try {
				Homepage = new Homepage_cittadini2();
				TabbedPane = Homepage.getTabbedPane();
				panel = Homepage.getPanel();
				ProxyServer proxy = new ProxyServer();
				System.out.println(cf);
				String result = proxy.setCF(cf);
				Homepage.set(result);
				Homepage.setCFnormale(cf);
				Homepage.setTitle("Accesso da Autenticato");
				Homepage.goSignIn().setVisible(false);
				Homepage.goLogIn().setText("Logout");
				Homepage.setVisible(true);
				Homepage.Severita();
				setVisible(false);
				dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Home2(args[0], args[1], args[2]);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

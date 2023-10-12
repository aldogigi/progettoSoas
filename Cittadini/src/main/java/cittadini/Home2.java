
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

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
	 * 
	 * @exception Expceton se non si riesce a comunicare con il server o se il server è disconnesso
	 */
	public Home2() {
		super("Login Cittadini");
		
		
		setBounds(100, 100, 516, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Email_CF = new JTextField();
		Email_CF.setBounds(158, 51, 197, 20);
		contentPane.add(Email_CF);
		Email_CF.setColumns(10);

		password = new JPasswordField();
		password.setBounds(158, 93, 197, 20);
		contentPane.add(password);
		password.setColumns(10);

		Registrazione = new Registrazione_Cittadini2();

		toSignIn = new JLabel("Non ti sei ancora registrato? Registrati");
		toSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		toSignIn.setBounds(126, 162, 277, 14);
		contentPane.add(toSignIn);
		toSignIn.setForeground(Color.BLUE.darker());
		toSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblNewLabel = new JLabel("Inserisci Email o CF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(158, 36, 197, 14);
		contentPane.add(lblNewLabel);

		lblInserisciLaPassword = new JLabel("Inserisci la password");
		lblInserisciLaPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciLaPassword.setBounds(158, 82, 197, 12);
		contentPane.add(lblInserisciLaPassword);

		response = new JLabel("New label");
		response.setHorizontalAlignment(SwingConstants.CENTER);
		response.setBounds(22, 212, 482, 47);
		response.setForeground(Color.RED);
		contentPane.add(response);
		response.setVisible(false);

		JButton AccessButtom = new JButton("Accedi");
		AccessButtom.setBounds(202, 124, 119, 23);
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
		FreeAccess.setBounds(202, 187, 119, 14);
		contentPane.add(FreeAccess);
		FreeAccess.setForeground(Color.BLUE.darker());
		FreeAccess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
					} else if (res.equals("non ho trovato nè CF nè Email sul Db, Registrati!")) {
						response.setText("non ho trovato nè CF nè Email sul Db, Registrati!");
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
		setTitle("Login");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home2 frame = new Home2();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

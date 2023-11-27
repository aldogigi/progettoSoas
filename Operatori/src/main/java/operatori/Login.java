package operatori;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 * */

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private Registrazione registrazione;
	private ProxyServer ps;
	private JButton btnAutoLogin;
	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");



	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login(args[0], args[1]);
					if(args[0].equals("true")) {
						
						HomepageOperatori HO = new HomepageOperatori(args[0], args[1]);
						HO.setVisible(true);
						JOptionPane.showMessageDialog(new JFrame(), "Benvenuto Operatore");
						login.setVisible(false);
						
					}
					else {
						login.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param checkOAuth 
	 * @param tokenUserOAuth 
	 * @throws Exception 
	 */
	public Login(final String checkOAuth, final String tokenUserOAuth) throws Exception{
		
		btnAutoLogin = new JButton("");
		
		try {
			ps = new ProxyServer();
		}catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane); 
		
		JLabel labelLogin = new JLabel(" LOGIN");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		JLabel labelEmail = new JLabel("E-Mail");
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		EMail = new JTextField();
		EMail.setColumns(10);
		
		Password = new JPasswordField();
		Password.setColumns(10);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBackground(Color.BLUE);
		btnAccedi.setForeground(Color.WHITE);
		
		btnAccedi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String emailString = EMail.getText().toString();
				String passwordString = Password.getText().toString();
				String risultato = "errore";
				
				if (emailString.length() == 0 || passwordString.length() == 0) {
					
					JOptionPane.showMessageDialog(new JFrame(), "E-Mail o Password vuoti");
				
				}
				else if(!(EMAIL_REGEX.matcher(emailString).matches())) {
					
					JOptionPane.showMessageDialog(new JFrame(), "E-Mail sbagliata");
					
				}
				else if(!(PASSWORD_REGEX.matcher(passwordString).matches())) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Formato della password scorretto");
					
				}
				else {
				
					try {
						risultato = ps.checkUser(emailString + ":" + passwordString + ":" + "null" + ":" + "null");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if (!(risultato.equals("correct"))) {
						
						
						JOptionPane.showMessageDialog(new JFrame(), risultato);
					}
					else {
						HomepageOperatori HO = null;
						try {
							HO = new HomepageOperatori(checkOAuth, tokenUserOAuth);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						HO.setVisible(true);
						JOptionPane.showMessageDialog(new JFrame(), "Benvenuto Operatore");
						setVisible(false);
						
						
						
					}
				}
			}
		});
		
		JLabel LabelRegistrati = new JLabel("Non sei ancora registrato? Registrati");
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {

				 registrazione = new Registrazione(checkOAuth, tokenUserOAuth);
				 registrazione.setVisible(true);
				 setVisible(false);
				
			}
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
				            "cmd.exe", "/c", "java -jar AutenticazioneOspedale\\target\\AutenticazioneOspedale-1.0.jar login operatori && exit");
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(labelEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
				.addComponent(labelLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(EMail, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 661, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(242)
					.addComponent(btnAccedi, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(230))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(LabelRegistrati, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
					.addGap(153))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(326, Short.MAX_VALUE)
					.addComponent(btnAutoLogin)
					.addGap(305))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(labelPassword)
					.addGap(18)
					.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccedi, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(LabelRegistrati)
					.addGap(18)
					.addComponent(btnAutoLogin)
					.addGap(27))
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Operatori - Login");
		setResizable(false);
		
	}
}

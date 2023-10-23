package oauth;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 * */

public class Registrazione_OAuth extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private OAuthGestione oauth;
	private ProxyServer ps;
	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");

	/**
	 * Create the frame.
	 * @param check 
	 */
	public Registrazione_OAuth(String checkLR) throws Exception{
		ps = new ProxyServer();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane); 
		
		JLabel labelLogin = new JLabel("REGISTRAZIONE");
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
		Password.setToolTipText(
				"<html> La password deve avere: <br>       Almeno un carattere maiuscolo <br>       Almeno un carattere minusculo <br>       Almeno un carattere speciale <br>       Almeno un numero <br> 		 Deve essere lunga almeno 8 caratteri.</html> ");
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBackground(Color.BLUE);
		btnRegistrati.setForeground(Color.WHITE);
		
		btnRegistrati.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				String emailString = EMail.getText().toString();
				String passwordString = Password.getText().toString();
				
				if (emailString.length() == 0 || passwordString.length() == 0) {
					
					JOptionPane.showMessageDialog(new JFrame(), "E-Mail o Password vuoti");
				
				}
				else if(!(EMAIL_REGEX.matcher(emailString).matches())) {
					
					JOptionPane.showMessageDialog(new JFrame(), "E-Mail sbagliata");
					
				}
				else if(!(PASSWORD_REGEX.matcher(passwordString).matches())) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Formato della password scorretta");
					
				}
				else {
				
					int result = 0;
					
					String timestamp = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(Calendar.getInstance().getTime());
					System.out.println(timestamp);
					
					
					try {
						result = ps.inserNewUserOauth(emailString + ":" + passwordString + ":" + timestamp);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result > 0) {
						
						JOptionPane.showMessageDialog(new JFrame(), "Utente inserito correttamente");
						
					}
					else {
						
						JOptionPane.showMessageDialog(new JFrame(), "Errore");
						
					}
				}
				
			}
		});
		
		JLabel LabelRegistrati = new JLabel("Sei gia' registrato?");
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {

				 try {
					oauth = new OAuthGestione(checkLR);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 oauth.setVisible(true);
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
					.addComponent(btnRegistrati, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(230))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(LabelRegistrati, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
					.addGap(153))
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
					.addComponent(btnRegistrati, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(LabelRegistrati)
					.addGap(18))
		);
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   
				   dispose();
				   ProcessBuilder builder = new ProcessBuilder(
				            "cmd.exe", "/c", "java -jar Operatori\\target\\Operatori-1.0.jar false null");
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
		
		contentPane.setLayout(gl_contentPane);
		setTitle("Registrazione OAuth");
		setResizable(false);
	}
}

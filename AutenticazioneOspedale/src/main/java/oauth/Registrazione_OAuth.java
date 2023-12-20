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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 * */

public class Registrazione_OAuth extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private OAuthGestione oauth;
	private Registrazione_OAuth registrazione_OAuth = this;
	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");
	private JTextField CF;

	/**
	 * Create the frame.
	 * @param context 
	 * @param project 
	 * @param check 
	 */
	public Registrazione_OAuth(Thread thread, Servizio servizio, String checkLR, String project) throws Exception{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setBounds(100, 100, 695, 442);
		
		setContentPane(contentPane); 
		
		JLabel labelRegistrazione = new JLabel("REGISTRAZIONE");
		labelRegistrazione.setBounds(5, 15, 671, 90);
		labelRegistrazione.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegistrazione.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		JLabel labelEmail = new JLabel("E-Mail");
		labelEmail.setBounds(5, 111, 671, 26);
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel labelCF = new JLabel("CF");
		labelCF.setBounds(5, 172, 671, 26);
		labelCF.setHorizontalAlignment(SwingConstants.CENTER);
		labelCF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(5, 237, 671, 22);
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		EMail = new JTextField();
		EMail.setBounds(172, 139, 341, 19);
		EMail.setColumns(10);
		
		Password = new JPasswordField();
		Password.setBounds(172, 269, 341, 19);
		Password.setColumns(10);
		Password.setToolTipText(
				"<html> La password deve avere: <br>       Almeno un carattere maiuscolo <br>       Almeno un carattere minusculo <br>       Almeno un carattere speciale <br>       Almeno un numero <br> 		 Deve essere lunga almeno 8 caratteri.</html> ");
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(257, 298, 163, 36);
		btnRegistrati.setBackground(Color.BLUE);
		btnRegistrati.setForeground(Color.WHITE);
		
		btnRegistrati.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				String emailString = EMail.getText().toString();
				String passwordString = Password.getText().toString();
				String cFString = (CF.getText().toString()).toLowerCase();
				
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
						result = servizio.inserNewUserOauth(emailString, passwordString, timestamp, cFString, project);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result > 0) {
						
						JOptionPane.showMessageDialog(new JFrame(), "Utente inserito correttamente");
						try {
							oauth = new OAuthGestione(thread, servizio, checkLR, project);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 oauth.setVisible(true);
						 registrazione_OAuth.setVisible(false);
						
					}
					else {
						
						JOptionPane.showMessageDialog(new JFrame(), "Errore");
						
					}
				}
				
			}
		});
		
		JLabel LabelRegistrati = new JLabel("Sei gia' registrato?");
		LabelRegistrati.setBounds(5, 344, 671, 13);
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {

				try {
					oauth = new OAuthGestione(thread, servizio, checkLR, project);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 oauth.setVisible(true);
				 registrazione_OAuth.setVisible(false);
				
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
			
		this.addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent evt) {
			   
			   servizio.setReturn("1");
			   
			   synchronized (thread){
    		       thread.notify();	
			   }
			   setVisible(false);	
			   
		   }
		  });
		
		contentPane.setLayout(null);
		contentPane.add(labelPassword);
		contentPane.add(LabelRegistrati);
		contentPane.add(Password);
		contentPane.add(labelRegistrazione);
		contentPane.add(labelEmail);
		contentPane.add(EMail);
		contentPane.add(btnRegistrati);
		contentPane.add(labelCF);
		
		CF = new JTextField();
		CF.setColumns(10);
		CF.setBounds(172, 208, 341, 19);
		contentPane.add(CF);
		setTitle("Registrazione OAuth");
		setResizable(false);
		
		CF.setVisible(false);
		labelCF.setVisible(false);
		
		if(project.equals("cittadini")) {
			
			setBounds(100, 100, 695, 442);
			CF.setBounds(172, 208, 341, 19);
			labelRegistrazione.setBounds(5, 15, 671, 90);
			labelEmail.setBounds(5, 111, 671, 26);
			EMail.setBounds(172, 139, 341, 19);
			labelCF.setBounds(5, 172, 671, 26);
			labelPassword.setBounds(5, 237, 671, 22);
			Password.setBounds(172, 277, 341, 19);
			btnRegistrati.setBounds(263, 306, 163, 36);
			LabelRegistrati.setBounds(172, 367, 351, 13);
			CF.setVisible(true);
			labelCF.setVisible(true);
			
		}
		else if(project.equals("operatori")) {
			setBounds(100, 100, 695, 378);
			labelEmail.setBounds(5, 111, 671, 26);
			labelRegistrazione.setBounds(5, 15, 671, 90);
			labelPassword.setBounds(5, 174, 671, 22);
			CF.setVisible(false);
			labelCF.setVisible(false);
			EMail.setBounds(172, 139, 341, 19);
			Password.setBounds(172, 208, 341, 19);
			btnRegistrati.setBounds(262, 233, 163, 36);
			LabelRegistrati.setBounds(172, 279, 351, 13);

		}
		
	}
}

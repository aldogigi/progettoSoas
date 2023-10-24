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
 * @author Alex Rabuffetti "Matricola"
 * */

public class Login_OAuth extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private Registrazione_OAuth registrazione;
	private ProxyServer ps;
	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");
	private JTextField CF;

	/**
	 * Create the frame.
	 * @param token 
	 * @param project 
	 */
	public Login_OAuth(String checkLR, String token, String project){
				
		try {
			ps = new ProxyServer();
		}catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane); 
		
		JLabel labelLogin = new JLabel(" LOGIN");
		labelLogin.setBounds(5, 28, 671, 72);
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		JLabel labelEmail = new JLabel("E-Mail");
		labelEmail.setBounds(5, 109, 671, 26);
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(5, 239, 671, 22);
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		EMail = new JTextField();
		EMail.setBounds(163, 145, 351, 19);
		EMail.setColumns(10);
		
		Password = new JPasswordField();
		Password.setBounds(165, 271, 351, 19);
		Password.setColumns(10);
		
		CF = new JTextField();
		CF.setColumns(10);
		CF.setBounds(163, 210, 351, 19);
		contentPane.add(CF);
		
		JLabel labelCF = new JLabel("CF");
		labelCF.setHorizontalAlignment(SwingConstants.CENTER);
		labelCF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelCF.setBounds(5, 174, 671, 26);
		contentPane.add(labelCF);
		setTitle("Login OAuth");
		setResizable(false);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBounds(240, 312, 199, 39);
		btnAccedi.setBackground(Color.BLUE);
		btnAccedi.setForeground(Color.WHITE);
		
		btnAccedi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String emailString = EMail.getText().toString();
				String passwordString = Password.getText().toString();
				String cFString = CF.getText().toString().toLowerCase();
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
						if(project.equals("operatori")) {
							risultato = ps.checkUser(emailString + ":" + passwordString + ":" + "oauth" + ":" + "null");
						}
						else if(project.equals("cittadini")) {
							risultato = ps.checkUser(emailString + ":" + passwordString + ":" + "oauth" + ":" + cFString);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if (!(risultato.equals("Token OAuth aggiornato"))) {
						
						
						JOptionPane.showMessageDialog(new JFrame(), risultato);
					}
					else {
						
						 setVisible(false);
						 OAuthGestione oAuthGestione = null;
						try {
							oAuthGestione = new OAuthGestione(checkLR, project);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 oAuthGestione.setVisible(true);
					}
				}
			}
		});
		
		JLabel LabelRegistrati = new JLabel("Non sei ancora registrato? Registrati");
		LabelRegistrati.setBounds(5, 361, 671, 13);
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {

				 try {
					registrazione = new Registrazione_OAuth(checkLR, project);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		JLabel lblIndietro = new JLabel("Indietro");
		lblIndietro.setBounds(5, 5, 72, 17);
		lblIndietro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		lblIndietro.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				OAuthGestione oAuthGestione;
				try {
					oAuthGestione = new OAuthGestione(checkLR, project);
					oAuthGestione.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		if(project.equals("operatori")) {
			
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
		}
		else if (project.equals("cittadini")) {
			this.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   
					   dispose();
					   ProcessBuilder builder = new ProcessBuilder(
					            "cmd.exe", "/c", "java -jar Cittadini\\target\\Cittadini-1.0.jar false null null");
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
		}
		contentPane.setLayout(null);
		contentPane.add(labelEmail);
		contentPane.add(labelLogin);
		contentPane.add(EMail);
		contentPane.add(labelPassword);
		contentPane.add(btnAccedi);
		contentPane.add(LabelRegistrati);
		contentPane.add(Password);
		contentPane.add(lblIndietro);
		
		if(project.equals("operatori")) {
			setBounds(100, 100, 695, 409);
			lblIndietro.setBounds(5, 5, 72, 17);
			labelLogin.setBounds(5, 28, 671, 72);
			labelEmail.setBounds(5, 109, 671, 26);
			EMail.setBounds(163, 145, 351, 19);
			labelPassword.setBounds(5, 174, 671, 22);
			Password.setBounds(165, 218, 351, 19);
			btnAccedi.setBounds(240, 259, 199, 39);
			LabelRegistrati.setBounds(5, 308, 671, 13);

			labelCF.setVisible(false);
			CF.setVisible(false);
			
		}
		else if (project.equals("cittadini")) {
			setBounds(100, 100, 695, 442);
			lblIndietro.setBounds(5, 5, 72, 17);
			labelLogin.setBounds(5, 28, 671, 72);
			labelEmail.setBounds(5, 109, 671, 26);
			EMail.setBounds(163, 145, 351, 19);
			labelCF.setBounds(5, 174, 671, 26);
			CF.setBounds(163, 210, 351, 19);
			labelPassword.setBounds(5, 239, 671, 22);
			Password.setBounds(165, 271, 351, 19);
			btnAccedi.setBounds(240, 312, 199, 39);
			LabelRegistrati.setBounds(5, 361, 671, 13);
			
			labelCF.setVisible(true);
			CF.setVisible(true);
		}
		
		
	}
}

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

public class Login_OAuth extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private Registrazione_OAuth registrazione;
	private ProxyServer ps;
	private Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private Pattern PASSWORD_REGEX = Pattern.compile("^.*(?=.*[A-Z])(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&]).*$");

	/**
	 * Create the frame.
	 * @param token 
	 */
	public Login_OAuth(String checkLR, String token){
				
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
						risultato = ps.checkUser(emailString + ":" + passwordString + ":" + "oauth");
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
							oAuthGestione = new OAuthGestione(checkLR);
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
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {

				 try {
					registrazione = new Registrazione_OAuth(checkLR);
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
		lblIndietro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		lblIndietro.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				OAuthGestione oAuthGestione;
				try {
					oAuthGestione = new OAuthGestione(checkLR);
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(labelEmail, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
				.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
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
					.addComponent(lblIndietro, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(599, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblIndietro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(labelPassword)
					.addGap(18)
					.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccedi, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(LabelRegistrati)
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Login OAuth");
		setResizable(false);
	}
}

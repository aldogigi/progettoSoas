import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private registrazione Registrazione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnAutoLogin = new JButton("");
		String iconfilePath = this.getClass().getClassLoader().getResource("Oauth_image.png").getFile();
		btnAutoLogin.setIcon(new ImageIcon(iconfilePath));
		btnAutoLogin.setBorder(BorderFactory.createEmptyBorder());
		btnAutoLogin.setContentAreaFilled(false);
		btnAutoLogin.setFocusable(false);
		contentPane.add(btnAutoLogin);
		btnAutoLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("immagine cliccata");
			}
		});
		
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
		
		Password = new JTextField();
		Password.setColumns(10);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBackground(Color.BLUE);
		btnAccedi.setForeground(Color.WHITE);
		
		JLabel LabelRegistrati = new JLabel("Non sei ancora registrato? Registrati");
		LabelRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegistrati.setForeground(Color.BLUE);
		LabelRegistrati.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				 Registrazione = new registrazione();
				 Registrazione.setVisible(true);
				 setVisible(false);
				
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(labelEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelPassword, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
				.addComponent(labelLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(EMail, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(243)
					.addComponent(btnAccedi, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(229))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addComponent(btnAutoLogin, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addGap(180))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(LabelRegistrati, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(Password, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(153))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(labelPassword, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAccedi)
					.addGap(18)
					.addComponent(LabelRegistrati)
					.addGap(12)
					.addComponent(btnAutoLogin, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Login");
		setResizable(false);
	}
}

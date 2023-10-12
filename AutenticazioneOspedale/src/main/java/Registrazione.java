import java.awt.Color;
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

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 * */

public class Registrazione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EMail;
	private JTextField Password;
	private Login login;

	public Registrazione() {
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
		
		Password = new JTextField();
		Password.setColumns(10);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBackground(Color.BLUE);
		btnRegistrati.setForeground(Color.WHITE);
		
		JLabel BackLogin = new JLabel("Sei già registrato? Torna al login");
		BackLogin.setForeground(Color.BLUE);
		BackLogin.setHorizontalAlignment(SwingConstants.CENTER);
		BackLogin.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				login = new Login();
				login.setVisible(true);
				setVisible(false);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});;
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addComponent(btnAutoLogin, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addGap(180))
				.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(249, Short.MAX_VALUE)
					.addComponent(btnRegistrati, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addGap(237))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(EMail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(BackLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(Password, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(labelPassword, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addGap(155))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelLogin, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelEmail, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(labelPassword)
					.addGap(18)
					.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRegistrati)
					.addGap(15)
					.addComponent(BackLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAutoLogin, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Registrazione");
		setResizable(false);
	}
}

package operatori;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * La classe contiene il main del nostro applicativo.
 * <p>Genera la nostra interfaccia principale
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
 */
public class HomepageOperatori extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProxyServer ps;
	
	/**
	 * Frame richiama il metodo initialize() che si occupera' della costruzione della GUI
	 * @param checkOAuth 
	 * @param tokenUserOAuth 
	 * @param  
	 * @throws IOException 
	 */
	public HomepageOperatori(final String checkOAuth, final String tokenUserOAuth) throws IOException {
				
		ps = new ProxyServer();
		setTitle("CENTRO APPLICATIVO - OPERATORI");
		setBounds(100, 100, 598, 358);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		JLabel lblAttivitDesiderata = new JLabel("CENTRO APPLICATIVO - OPERATORI");
		lblAttivitDesiderata.setBounds(160, 23, 271, 15);
		lblAttivitDesiderata.setHorizontalAlignment(SwingConstants.CENTER);

		getContentPane().add(lblAttivitDesiderata);

		JButton btnCentroVaccinale = new JButton("CENTRO VACCINALE");
		btnCentroVaccinale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CentroVaccinale();

			}
		});
		btnCentroVaccinale.setBounds(48, 51, 234, 250);
		ImageIcon icon = new ImageIcon("image/hospital.png");
		btnCentroVaccinale.setIcon(new ImageIcon(icon.getImage().getScaledInstance(250, -1, java.awt.Image.SCALE_SMOOTH)));

		getContentPane().add(btnCentroVaccinale);

		JButton btnVaccinazione = new JButton("VACCINAZIONE");
		btnVaccinazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Vaccinazione();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Server Disconnesso", "ATTENZIONE",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVaccinazione.setBounds(323, 51, 234, 250);
		ImageIcon icon2 = new ImageIcon("image/vaccine.jpeg");
		btnVaccinazione.setIcon(new ImageIcon(icon2.getImage().getScaledInstance(382, -1, java.awt.Image.SCALE_SMOOTH)));
		getContentPane().add(btnVaccinazione);
		
		JLabel labelLogout = new JLabel("Logout");
		labelLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelLogout.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogout.setBounds(10, 10, 53, 15);
		labelLogout.setForeground(Color.BLUE);
		getContentPane().add(labelLogout);
		
		
		labelLogout.addMouseListener(new MouseListener()
		
		{
			public void mouseClicked(MouseEvent e) {
				Login login = null;
				try {
					login = new Login(checkOAuth, tokenUserOAuth);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				login.setVisible(true);
				setVisible(false);
			}
			public void mouseReleased(MouseEvent e) {	
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
		});
		
		String costructRules = ps.deployAllRuleXACML();
		if (costructRules.equals("not")) {
			System.out.println("Costruzione non avvenuta");

		}
		else if (costructRules.equals("yes")) {
			System.out.println("Costruzione regole XACML automatiche avvenuto con successo");
		}
		else {
			System.out.println(costructRules);
		}
		
	}
}

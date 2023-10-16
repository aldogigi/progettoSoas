package operatori;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.security.AlgorithmConstraints;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * La classe contiene il main del nostro applicativo.
 * <p>Genera la nostra interfaccia principale
 *
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 */
public class HomepageOperatori {
	private JFrame frmCentroApplicativo;
	private Login login;

	/**
	 * Frame richiama il metodo initialize() che si occupera' della costruzione della GUI
	 */
	public HomepageOperatori() {
		initialize();
	}

	/**
	 * Crea l'interfaccia grafica 
	 */
	public void initialize() {
		frmCentroApplicativo = new JFrame();
		frmCentroApplicativo.setTitle("CENTRO APPLICATIVO - OPERATORI");
		frmCentroApplicativo.setBounds(100, 100, 598, 358);
		frmCentroApplicativo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCentroApplicativo.getContentPane().setLayout(null);
		frmCentroApplicativo.setResizable(false);
		JLabel lblAttivitDesiderata = new JLabel("CENTRO APPLICATIVO - OPERATORI");
		lblAttivitDesiderata.setBounds(160, 23, 271, 15);
		lblAttivitDesiderata.setHorizontalAlignment(SwingConstants.CENTER);

		frmCentroApplicativo.getContentPane().add(lblAttivitDesiderata);

		JButton btnCentroVaccinale = new JButton("CENTRO VACCINALE");
		btnCentroVaccinale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CentroVaccinale();

			}
		});
		btnCentroVaccinale.setBounds(48, 51, 234, 250);
		ImageIcon icon = new ImageIcon("image/hospital.png");
		btnCentroVaccinale.setIcon(new ImageIcon(icon.getImage().getScaledInstance(250, -1, java.awt.Image.SCALE_SMOOTH)));

		frmCentroApplicativo.getContentPane().add(btnCentroVaccinale);

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
		frmCentroApplicativo.getContentPane().add(btnVaccinazione);
		
		JLabel labelLogout = new JLabel("Logout");
		labelLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelLogout.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogout.setBounds(10, 10, 53, 15);
		labelLogout.setForeground(Color.BLUE);
		frmCentroApplicativo.getContentPane().add(labelLogout);
		
		labelLogout.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				login = new Login();
				frmCentroApplicativo.setVisible(false);
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
	}
}

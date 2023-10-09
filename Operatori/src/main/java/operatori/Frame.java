package operatori;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * La classe contiene il main del nostro applicativo.
 * <p>Genera la nostra interfaccia principale
 *
 * @author Andrea Ferro 740958 VA
 * @author Gianluca Fontana 742393 VA
 * @author Manuel Nguyen 741939 VA 
 * @author Digvijaysinh D. Raj 741976 VA
 */
public class Frame {
	private JFrame frmCentroApplicativo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frmCentroApplicativo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Frame richiama il metodo initialize() che si occupera' della costruzione della GUI
	 */
	public Frame() {
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
		ImageIcon icon = new ImageIcon("C:\\Users\\gianl\\git\\progettoSoas\\Operatori\\src\\main\\java\\operatori\\image\\hospital.png");
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
		ImageIcon icon2 = new ImageIcon("C:\\Users\\gianl\\git\\progettoSoas\\Operatori\\src\\main\\java\\operatori\\image\\vaccine.jpeg");
		btnVaccinazione.setIcon(new ImageIcon(icon2.getImage().getScaledInstance(382, -1, java.awt.Image.SCALE_SMOOTH)));
		frmCentroApplicativo.getContentPane().add(btnVaccinazione);
		
	}

}

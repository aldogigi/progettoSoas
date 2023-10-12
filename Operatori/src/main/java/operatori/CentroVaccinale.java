package operatori;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
* E' la classe che gestisce la GUI e la logica applicativa per la gestione dei centri vaccinali
*
* @author Gianluca Fontana 21452A
* @author Alex Rabuffetti "Matricola"
*/
public class CentroVaccinale extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;

	private String nomecv, hub, indirizzo, nome, comune, provincia;
	private int civico, cap;

	/**
	 * E' il costruttore della classe che va ad inizializzare i dati e avviare
	 * il metodo creaGUI()

	 */
	public CentroVaccinale() {
		nomecv = "";
		nome = "";
		comune = "";
		provincia = "";
		civico = 0;
		cap = 0;

		creaGUI();
	}

	/**
	 * Viene creata l'interfaccia grafica per la gestione dei Centri Vaccinali
	 * 
	 */
	public void creaGUI() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("CENTRO VACCINALE");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNomeCentroVaccinale = new JLabel("Nome");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblHub = new JLabel("Tipologia");

		comboBox = new JComboBox<String>();
		comboBox.addItem("hub");
		comboBox.addItem("ospedaliero");
		comboBox.addItem("aziendale");

		JLabel lblIndirizzo = new JLabel("Indirizzo");

		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("via");
		comboBox_1.addItem("viale");
		comboBox_1.addItem("piazza");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNome = new JLabel("Qualificatore");

		JLabel lblCivico = new JLabel("Civico");
		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel lblComune = new JLabel("Comune");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel lblProvincia = new JLabel("Provincia");

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		JLabel lblCap = new JLabel("CAP");

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		JButton btnRegistra = new JButton("REGISTRA");
		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acquisizioneDati();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNome)
									.addComponent(lblComune)
									.addComponent(lblCivico)
									.addComponent(lblProvincia)
									.addComponent(lblCap)
									.addComponent(lblIndirizzo))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(33)
									.addComponent(lblHub)))
							.addGap(48))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNomeCentroVaccinale)
							.addGap(57)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
							.addComponent(btnRegistra))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeCentroVaccinale))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHub)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIndirizzo))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCivico))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComune)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProvincia)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRegistra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCap)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
		setSize(450, 350);
		setVisible(true);
	}

	/**
	 * Il metodo salva tutti gli input inseriti dall'operatore 
	 * 
	 */
	public void acquisizioneDati() {

		if (textField.getText().length() > 0) {
			nomecv = textField.getText().replaceAll("\\s{2,}", " ").trim();
			nomecv = nomecv.toLowerCase();
		}

		hub = (String) comboBox.getSelectedItem();

		indirizzo = (String) comboBox_1.getSelectedItem();

		if (textField_1.getText().length() > 0) {
			nome = textField_1.getText().replaceAll("\\s{2,}", " ").trim();
			nome = nome.toLowerCase();
		}

		if (textField_2.getText().length() > 0) {
			if (textField_2.getText().matches("[0-9]+")) {
				civico = Integer.parseInt(textField_2.getText().replaceAll("\\s{2,}", " ").trim());
			}
		}

		if (textField_3.getText().length() > 0) {
			comune = textField_3.getText().replaceAll("\\s{2,}", " ").trim();
			comune = comune.toLowerCase();
		}

		if (textField_4.getText().length() > 0) {
			provincia = textField_4.getText().replaceAll("\\s{2,}", " ").trim();
			provincia = provincia.toLowerCase();
		}

		if (textField_5.getText().length() > 0) {
			if (textField_5.getText().matches("[0-9]+")) {
				cap = Integer.parseInt(textField_5.getText().replaceAll("\\s{2,}", " ").trim());
			}
		}

		registraCentroVaccinale();
	}

	/**
	 * Il metodo procede a verifica tutti gli input salvati e nel caso in cui rispettino
	 * tutti i vincoli procede all'invio di essi al server
	 * 
	 * @exception IOException se il server è disconnesso
	 */
	public void registraCentroVaccinale() {
		RegistrazioneCentroVaccinale reg = new RegistrazioneCentroVaccinale(nomecv, hub, indirizzo, nome, civico,
				comune, provincia, cap);

		int risultato = 0;

		String errore = reg.controllo();

		if (!errore.equals("OK"))
			JOptionPane.showMessageDialog(new JFrame(), errore, "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				risultato = reg.registraCentroVaccinale();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Server Disconnesso", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
			}

			if (risultato > 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Il centro vaccinale è stato inserito con successo !");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
			}else if(risultato == -1) {
				
				JOptionPane.showMessageDialog(new JFrame(), "Centro vaccinale già presente!");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Il centro vaccinale non è stato inserito", "ATTENZIONE",
						JOptionPane.ERROR_MESSAGE);
			}

			pulisciCampi();
		}
	}

	/**
	 * Resetta tutti gli input del form
	 * 
	 */
	public void pulisciCampi() {
		nomecv = "";
		nome = "";
		comune = "";
		provincia = "";
		civico = 0;
		cap = 0;
	}

}

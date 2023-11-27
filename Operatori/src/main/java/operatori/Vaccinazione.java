package operatori;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
* E' la classe che gestisce la GUI e la logica applicativa per la gestione delle vaccinazioni
*
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti 20290A
*/
public class Vaccinazione extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JDateChooser dateChooser;
	private JButton btnInserisci;
	private String centroVaccinale, nome, cognome, codiceFiscale, data, vaccinoSom;
	private String errore;
	ArrayList<String> listaCV;

	/**
	 * E' il costruttore della nostra classe che va ad inizializzare i dati e avviare
	 * il metodo initialize()
	 */
	public Vaccinazione() throws IOException {
		listaCV = new ArrayList<String>();

		centroVaccinale = "";
		nome = "";
		cognome = "";
		codiceFiscale = "";
		data = "";
		vaccinoSom = "";

		errore = "";

		initialize();
	}

	/**
	 * Viene creata l'interfaccia grafica per la gestione delle vaccinazioni
	 * 
	 */
	public void initialize() throws IOException {
		setTitle("VACCINAZIONE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblCentroVaccinale = new JLabel("Centro Vaccinale");
		lblCentroVaccinale.setBounds(71, 12, 119, 15);
		getContentPane().add(lblCentroVaccinale);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(208, 7, 111, 24);
		getContentPane().add(comboBox);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(145, 45, 45, 15);
		getContentPane().add(lblNome);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(126, 76, 70, 15);
		getContentPane().add(lblCognome);

		textField = new JTextField();
		textField.setBounds(208, 43, 114, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(208, 74, 111, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCodiceFiscale = new JLabel("Codice Fiscale");
		lblCodiceFiscale.setBounds(90, 107, 100, 15);
		getContentPane().add(lblCodiceFiscale);

		textField_2 = new JTextField();
		textField_2.setBounds(208, 105, 137, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblDataSomministrazione = new JLabel("Data somministrazione");
		lblDataSomministrazione.setBounds(26, 140, 164, 15);
		getContentPane().add(lblDataSomministrazione);

		JLabel lblVaccinoSomministrato = new JLabel("Vaccino somministrato");
		lblVaccinoSomministrato.setBounds(36, 172, 164, 15);
		getContentPane().add(lblVaccinoSomministrato);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Pfizer");
		comboBox_1.addItem("Astrazeneca");
		comboBox_1.addItem("Moderna");
		comboBox_1.addItem("J&j");
		comboBox_1.setBounds(208, 167, 119, 24);
		getContentPane().add(comboBox_1);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(208, 136, 111, 19);
		getContentPane().add(dateChooser);

		btnInserisci = new JButton("INSERISCI");
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					acquisizioneDati();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnInserisci.setBounds(208, 203, 111, 25);
		getContentPane().add(btnInserisci);

		String ris = popolaCV();

		if (!ris.equals("vuota")) {
			String[] array = ris.split(":");
			for (int i = 0; i < array.length - 1; i++) {
				comboBox.addItem(array[i]);
			}

			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					"Nel database non risultano " + "centri vaccinali insertiti.\n" + "Inserire un centro vaccinale",
					"ERRORE", JOptionPane.ERROR_MESSAGE);

			setVisible(false);
		}
	}

	/**
	 * Il metodo salva tutti gli input inseriti dall'operatore 
	 * 
	 */
	public void acquisizioneDati() throws IOException {
		centroVaccinale = (String) comboBox.getSelectedItem();
		//System.out.println(centroVaccinale);

		if (textField.getText().length() > 0) {
			nome = textField.getText().replaceAll("\\s{2,}", " ").trim();
			nome = nome.toLowerCase();
			//System.out.println(nome);
		}

		if (textField_1.getText().length() > 0) {
			cognome = textField_1.getText().replaceAll("\\s{2,}", " ").trim();
			cognome = cognome.toLowerCase();
			//System.out.println(cognome);
		}

		if (textField_2.getText().length() > 0) {
			codiceFiscale = textField_2.getText().replaceAll("\\s{2,}", " ").trim();
			//System.out.println(codiceFiscale);
		}

		if (dateChooser.getDate() != null) {
			data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
			//System.out.println(data);
		}
		vaccinoSom = (String) comboBox_1.getSelectedItem();
		//System.out.println(vaccinoSom);

		registraVaccinazione();
	}

	/**
	 * Il metodo procede a verificare tutti gli input salvati e nel caso in cui rispettino
	 * tutti i vincoli procede all'invio di essi al server
	 * 
	 * @exception IOException se il server è disconnesso
	 */
	public void registraVaccinazione() throws IOException {
		RegistrazioneVaccinazione reg = new RegistrazioneVaccinazione(centroVaccinale, nome, cognome, codiceFiscale,
				data, vaccinoSom);

		errore = reg.controllo();

		if (errore.equals("OK")) {
			int risultato = reg.registraVaccinato();
			if (risultato > 0) {
				JOptionPane.showMessageDialog(new JFrame(), "La vaccinazione è stata inserita con successo !");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "La vaccinazione non è stata inserita con successo",
						"ATTENZIONE", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), errore, "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
		}

		pulisciCampi();
	}

	/**
	 * Creando un'istanza del ProxyServer viene inviata una richiesta per ricevere 
	 * tutti i centri vaccinali presenti
	 * 
	 * @return lista di tutti i centri vaccinali se presenti, lista vuota altrimenti
	 */
	public String popolaCV() throws IOException {
		ProxyServer ps = new ProxyServer();
		String risposta = ps.popolaCV();
		return risposta;
	}

	/**
	 * Resetta tutti gli input del form
	 * 
	 */
	public void pulisciCampi() {
		centroVaccinale = "";
		nome = "";
		cognome = "";
		codiceFiscale = "";
		errore = "";
	}
}

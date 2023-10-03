package cittadini;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * La classe gestisce l'interfaccia grafica e la logica applicativa per la gestione 
 * <p>delle avversità permettendo le seguenti funzionalità: inserimento, modifica e rimozione 
 *
 * @author Andrea Ferro 740958 VA
 * @author Gianluca Fontana 742393 VA
 * @author Manuel Nguyen 741939 VA 
 * @author Digvijaysinh D. Raj 741976 VA
 */
public class Eventi_Avversi extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String id;
	private String CFemail;
	private String result;
	private String id_vaccinazioni;

	private ProxyServer proxy;
	private JTable table;

	private JLabel response;
	private JLabel Events;
	private JLabel New_events;
	private JLabel id_vacc_label;

	private String[] result2;
	private String[] arrayAvversita = { "mal di testa", "febbre", "dolori muscolari articolari", "linfoadenopatia",
			"tachicardia", "crisi ipertensiva" };

	private JButton Update_Avv;
	private JButton delete_Avv;

	private JComboBox<Integer> comboBox_severity;
	private JComboBox<String> comboBox_event;
	private JComboBox<Integer> comboBox_id;

	private JLabel Event_label;
	private JLabel Severity_label;
	private JLabel Notes;

	private JButton Button_refresh;
	private JButton Insert_Avv;

	private JTextArea Insert_notes;
	private JLabel alert;

	private Boolean clicked;
	private JLabel lblnum_segn;
	private JLabel lblSeveritMedia;

	/**
	 * Il costruttore procede a creare l'interfaccia grafica ed effettuare tutti i controlli
	 * sugli input inseriti dall'utente
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * @exception Exception se il server è disconnesso
	 * 
	 * @param id1 id del centro vaccinale selezionato
	 * @param CFEmail codice fiscale o email del cittadino
	 */
	public Eventi_Avversi(final String id1, final String CFEmail) {
		this.id = id1;
		this.CFemail = CFEmail;

		clicked = false;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		id_vacc_label = new JLabel("id_vaccinazione:");
		id_vacc_label.setHorizontalAlignment(SwingConstants.CENTER);
		id_vacc_label.setBounds(48, 361, 168, 18);
		contentPane.add(id_vacc_label);

		comboBox_id = new JComboBox<Integer>();
		comboBox_id.setBounds(209, 357, 115, 22);
		contentPane.add(comboBox_id);

		try {
			proxy = new ProxyServer();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			id_vaccinazioni = proxy.popola_id_V(id, CFemail);
		//	System.out.println(id_vaccinazioni);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if (id_vaccinazioni.equals("niente")) {

			comboBox_id.setEnabled(false);

		} else {

			popola_id();

		}

		try {
			proxy = new ProxyServer();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBounds(100, 100, 896, 484);

		Events = new JLabel("Propri eventi avversi");
		Events.setForeground(Color.BLUE);
		Events.setHorizontalAlignment(SwingConstants.LEFT);
		Events.setBounds(48, 4, 314, 19);
		contentPane.add(Events);

		table = new JTable();
		
		Update_Avv = new JButton("MODIFICA PROPRIE AVVERSITA'");
		Update_Avv.setBounds(46, 404, 233, 23);
		contentPane.add(Update_Avv);

		response = new JLabel("New label");
		response.setHorizontalAlignment(SwingConstants.CENTER);
		response.setBounds(117, 174, 658, 14);
		contentPane.add(response);

		Insert_Avv = new JButton("INSERISCI NUOVE AVVERSITA'");
		Insert_Avv.setBounds(606, 404, 245, 23);
		contentPane.add(Insert_Avv);

		New_events = new JLabel("Inserisci nuovi eventi avversi:");
		New_events.setForeground(Color.BLUE);
		New_events.setBounds(48, 269, 251, 14);
		contentPane.add(New_events);

		comboBox_event = new JComboBox<String>();
		comboBox_event.setBounds(209, 307, 115, 22);
		for (int i = 0; i < arrayAvversita.length; i++) {
			comboBox_event.addItem(arrayAvversita[i]);
		}
		comboBox_event.setAlignmentX(SwingConstants.CENTER);
		contentPane.add(comboBox_event);

		comboBox_severity = new JComboBox<Integer>();
		comboBox_severity.setBounds(209, 332, 115, 22);
		for (int i = 1; i < 6; i++) {
			comboBox_severity.addItem(i);
		}
		comboBox_severity.setAlignmentX(SwingConstants.CENTER);
		contentPane.add(comboBox_severity);

		Insert_notes = new JTextArea();
		Insert_notes.setBounds(334, 306, 550, 87);
		contentPane.add(Insert_notes);
		Insert_notes.setText("lunghezza massima 256 caratteri");
		Insert_notes.setLineWrap(true);

		Insert_notes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (clicked == false) {
					clicked = true;
					Insert_notes.setText(" ");
				}

			}
		});

		Event_label = new JLabel("Evento Avverso:");
		Event_label.setHorizontalAlignment(SwingConstants.CENTER);
		Event_label.setBounds(48, 311, 168, 14);
		contentPane.add(Event_label);

		Severity_label = new JLabel("Severità:");
		Severity_label.setHorizontalAlignment(SwingConstants.CENTER);
		Severity_label.setBounds(48, 335, 158, 14);
		contentPane.add(Severity_label);

		Notes = new JLabel("Note:");
		Notes.setHorizontalAlignment(SwingConstants.CENTER);
		Notes.setBounds(606, 280, 48, 14);
		contentPane.add(Notes);

		Button_refresh = new JButton("AGGIORNA");
		Button_refresh.setBounds(393, 0, 126, 23);
		contentPane.add(Button_refresh);

		alert = new JLabel("New label");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setBounds(117, 386, 207, 14);
		contentPane.add(alert);

		lblnum_segn = new JLabel("Numero segnalazioni: ");
		lblnum_segn.setBounds(419, 4, 182, 14);
		contentPane.add(lblnum_segn);
		lblnum_segn.setVisible(false);

		lblSeveritMedia = new JLabel("Severità media: ");
		lblSeveritMedia.setBounds(593, 4, 182, 14);
		contentPane.add(lblSeveritMedia);
		
		delete_Avv = new JButton("ELIMINA AVVERSITA SELEZIONATA");
		delete_Avv.setBounds(316, 404, 261, 23);
		contentPane.add(delete_Avv);
		
		
		lblSeveritMedia.setVisible(false);

		alert.setVisible(false);
		response.setVisible(false);

		if (CFEmail == null) {
			select2();
		} else if (CFEmail != null) {
			select();
		}

		setResizable(false);

		setTitle("Avversità");

		Button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					proxy = new ProxyServer();
				} catch (IOException i) {
					i.printStackTrace();
				}

				Button_refresh.setBounds(289, 7, 126, 23);

				select();
				dispose();
				(new Eventi_Avversi(id1, CFEmail)).setVisible(true);

			}
		});

		Update_Avv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alert.setForeground(Color.RED);
				
				String risultato = "";

				int num_column = table.getColumnCount();
				int num_rows = table.getRowCount();

				String datiTabella = "";

				for (int x = 1; x < num_rows; x++) {
					for (int y = 0; y < num_column; y++) {

						datiTabella += (table.getValueAt(x, y)).toString() + ":";

					}
					datiTabella += "_";
				}

				String[] datiTabellaSplit = datiTabella.split("_");
				for (int z = 0; z < datiTabellaSplit.length; z++) {

					String[] datiTabellaSplit2 = datiTabellaSplit[z].split(":");

					try {
						proxy = new ProxyServer();
					} catch (Exception i) {
						i.printStackTrace();
					}

					try {
						if (datiTabellaSplit2[2].length() == 0) {
							alert.setText("evento vuoto");
							alert.setVisible(true);
							break;
						} else if (!(checkInString(datiTabellaSplit2[2]))) {
							alert.setText("evento non presente");
							alert.setVisible(true);
							break;
						} else if (datiTabellaSplit2[3].length() == 0) {

							alert.setText("severità vuota");
							alert.setVisible(true);
							break;
						} else if ((!(Integer.parseInt(datiTabellaSplit2[3]) <= 5))
								|| ((Integer.parseInt(datiTabellaSplit2[3])) <= 0)) {

							alert.setText("severità solo da 1 a 5");
							alert.setVisible(true);
							break;
						}

						else if (datiTabellaSplit2.length == 4) {

							risultato = proxy.updateAvversita(datiTabellaSplit2[0], datiTabellaSplit2[1],
									datiTabellaSplit2[2], datiTabellaSplit2[3], " ");
						} else {
							risultato = proxy.updateAvversita(datiTabellaSplit2[0], datiTabellaSplit2[1],
									datiTabellaSplit2[2], datiTabellaSplit2[3], datiTabellaSplit2[4]);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

				if (risultato.equals("modifica avvenuta")) {
					alert.setForeground(Color.GREEN);
					alert.setText("modifica avvenuta");
					alert.setVisible(true);

				} else if (risultato.equals("modifica fallita")) {

					alert.setText("modifica fallita");
					alert.setVisible(true);

				}

			}
		});

		delete_Avv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alert.setForeground(Color.RED);
				
				String risultato = "";

				String id_evento_avverso = table.getValueAt(table.getSelectedRow(), 1).toString();


					try {
						proxy = new ProxyServer();
					} catch (Exception i) {
						i.printStackTrace();
					}

					try {
							risultato = proxy.deleteAvversita(id_evento_avverso);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				if (risultato.equals("cancellazione avvenuta")) {
					alert.setForeground(Color.GREEN);
					alert.setText("cancellazione avvenuta");
					alert.setVisible(true);

				} else if (risultato.equals("cancellazione fallita")) {

					alert.setText("cancellazione fallita");
					alert.setVisible(true);

				}

			}
		});
		
		Insert_Avv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alert.setForeground(Color.RED);
				
				try {
					proxy = new ProxyServer();
				} catch (IOException i) {
					i.printStackTrace();
				}

				String tipo = comboBox_event.getSelectedItem().toString();
				int severy = (Integer) comboBox_severity.getSelectedItem();
				String note = Insert_notes.getText().toString();
				if (note.equals("lunghezza massima 256 caratteri")) {
					note = " ";
				}
				int id_combo = (Integer) comboBox_id.getSelectedItem();

				try {
					if (note.length() > 256) {

						alert.setText("lunghezza note > 256 caratteri");
						alert.setVisible(true);

					} else {
						result = proxy.inserisciEventiAvversi(tipo, severy, note, String.valueOf(id_combo), CFEmail);
						//System.out.println(result);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (result.equals("trovato evento avverso uguale!")) {

					alert.setText("trovato evento avverso uguale!");
					alert.setVisible(true);
				} else if (result.equals("inserimento avvenuto")) {
					alert.setForeground(Color.GREEN);
					alert.setText("inserimento avvenuto");
					alert.setVisible(true);

				} else if (result.equals("inserimento fallito")) {

					alert.setText("inserimento fallito");
					alert.setVisible(true);

				}

			}
		});

	}

	/**
	 * Il metodo manda una richiesta al server per ricevere tutte
	 * le avversità di un cittadino legate ad un determinato centro vaccinale
	 * 
	 * @exception Exception se non si riesce a comunicare con il server
	 */
	public void select() {

		lblnum_segn.setVisible(false);
		lblSeveritMedia.setVisible(false);

		String result = "";

		try {
			//System.out.println(id + CFemail);
			result = proxy.visualizzaInfoCentroVaccinale(id, CFemail);
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result.equals("niente")) {
			response.setForeground(Color.RED);
			response.setText("Non ho trovato nessun evento avverso registrato da te");
			response.setVisible(true);
			table.setVisible(false);
			Update_Avv.setVisible(true);
			Events.setVisible(true);
			Insert_Avv.setVisible(true);
			New_events.setVisible(true);
			Notes.setVisible(true);
			Event_label.setVisible(true);
			comboBox_event.setVisible(true);
			Insert_notes.setVisible(true);
			Severity_label.setVisible(true);
			comboBox_severity.setVisible(true);
			Button_refresh.setVisible(true);
			id_vacc_label.setVisible(true);
			comboBox_id.setVisible(true);
			delete_Avv.setVisible(true);

		} else {
			response.setVisible(false);
			Vector<Vector> Righe = new Vector<Vector>();
			result2 = new String[2];
			result2 = result.split("-");
			String nomeColonne = result2[0];
			String datiRighe = result2[1];
			String[] datiRigheSplit = datiRighe.split("___________");

			String[] nomi = nomeColonne.split(":");

			Vector colonne = new Vector();
			for (int nr = 0; nr < nomi.length; nr++) {
				colonne.add(nomi[nr]);
			}
			Righe.add(colonne);

			for (int i = 0; i < datiRigheSplit.length; i++) {
				Vector riga = new Vector();
				String[] split = datiRigheSplit[i].split(":");
				for (int nr = 0; nr < split.length; nr++) {
					riga.add(split[nr]);
				}
				Righe.add(riga);
			}

			table = new JTable(Righe, colonne);
			table.setBounds(58, 35, 826, 222);
			table.setRowSelectionAllowed(false);
			table.isCellEditable(0, 0);
			contentPane.add(table);
			table.setVisible(true);

		}
	}

	/**
	 * Il metodo manda una richiesta al server per ricevere tutte
	 * le avversità, in forma anonima, legate ad un determinato centro vaccinale
	 * 
	 * @exception Exception se non si riesce a comunicare con il server
	 */
	public void select2() {
		lblnum_segn.setVisible(true);
		lblSeveritMedia.setVisible(true);

		String result = "";

		try {
			result = proxy.visualizzaInfoCentroVaccinale2(id);
		//	System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result.equals("niente")) {
			Button_refresh.setBounds(176, 7, 126, 23);
			response.setForeground(Color.RED);
			response.setText("Non ho trovato nessun evento avverso");
			response.setVisible(true);
			table.setVisible(false);
			Update_Avv.setVisible(false);
			Events.setVisible(false);
			Insert_Avv.setVisible(false);
			New_events.setVisible(false);
			Notes.setVisible(false);
			Event_label.setVisible(false);
			comboBox_event.setVisible(false);
			Insert_notes.setVisible(false);
			Severity_label.setVisible(false);
			comboBox_severity.setVisible(false);
			Button_refresh.setVisible(false);
			id_vacc_label.setVisible(false);
			comboBox_id.setVisible(false);
			lblnum_segn.setVisible(false);
			lblSeveritMedia.setVisible(false);
			delete_Avv.setVisible(false);

		} else {
			float countSeverita = 0;
			float sum = 0;
			Button_refresh.setBounds(142, 7, 126, 23);
			response.setVisible(false);
			Vector<Vector> Righe = new Vector<Vector>();
			result2 = new String[2];
			result2 = result.split("-");
			String nomeColonne = result2[0];
			String datiRighe = result2[1];
			String[] datiRigheSplit = datiRighe.split("___________");

			String[] nomi = nomeColonne.split(":");

			Vector colonne = new Vector();
			for (int nr = 0; nr < nomi.length; nr++) {
				colonne.add(nomi[nr]);
			}
			Righe.add(colonne);

			for (int i = 0; i < datiRigheSplit.length; i++) {
				Vector riga = new Vector();
				String[] split = datiRigheSplit[i].split(":");
				sum += Integer.parseInt(split[3]);
				countSeverita++;
				for (int nr = 0; nr < split.length; nr++) {
					riga.add(split[nr]);
				}
				Righe.add(riga);
			}

			table = new JTable(Righe, colonne);
			table.setRowSelectionAllowed(false);
			table.setBounds(10, 33, 862, 410);
			contentPane.add(table);
			table.setDefaultEditor(Object.class, null);
			table.setVisible(true);
			response.setVisible(false);
			Update_Avv.setVisible(false);
			Events.setVisible(false);
			Insert_Avv.setVisible(false);
			New_events.setVisible(false);
			Notes.setVisible(false);
			Event_label.setVisible(false);
			comboBox_event.setVisible(false);
			Insert_notes.setVisible(false);
			Severity_label.setVisible(false);
			comboBox_severity.setVisible(false);
			Button_refresh.setVisible(false);
			id_vacc_label.setVisible(false);
			comboBox_id.setVisible(false);
			delete_Avv.setVisible(false);

			lblnum_segn.setText(lblnum_segn.getText() + (table.getRowCount() - 1));
			float resultato = (sum / countSeverita);
			lblSeveritMedia.setText(lblSeveritMedia.getText() + String.valueOf(resultato));

		}

	}

	/**
	 * Il metodo restituisce il riferimento al label Events 
	 * 
	 * @return riferimento al label
	 */
	public JLabel getevents() {
		return Events;
	}

	/**
	 * Controllo per verificare che il cittadino, dopo una modifica, abbia inserito un
	 * evento avverso corretto
	 * 
	 * @param string evento avverso inserito dall'utente
	 * @return false se l'utente inserisce un evento avverso non corretto, true altrimenti
	 */
	public Boolean checkInString(String string) {

		for (int i = 0; i < arrayAvversita.length; i++) {
			if (string.toLowerCase().equals(arrayAvversita[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Nel caso in cui ci fossero vaccinazioni somministrare ad un cittadino, il metodo
	 * carica tutti gli id di esse nella combobox per poterle selezionare quando si desidera
	 * inserire un evento avverso a riguardo
	 * 
	 */
	public void popola_id() {
		result2 = new String[2];
		result2 = id_vaccinazioni.split("-");
		String datiRighe = result2[1];
		String[] datiRigheSplit = datiRighe.split("___________");

		for (int i = 0; i < datiRigheSplit.length; i++) {
			String[] split = datiRigheSplit[i].split(":");
			for (int nr = 0; nr < split.length; nr++) {
				comboBox_id.addItem(Integer.parseInt(split[0]));
			}
		}

	}
}

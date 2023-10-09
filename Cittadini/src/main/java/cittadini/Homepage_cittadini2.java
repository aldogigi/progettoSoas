package cittadini;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * La classe gestisce l'interfaccia grafica e la logica applicativa per la gestione di:
 * <p> Ricerca centri vaccinali per: nome, comune e tipologia (accesso libero)
 *<p> Visualizzazione delle proprie avversità (post autenticazione)
 *
 * @author Andrea Ferro 740958 VA
 * @author Gianluca Fontana 742393 VA
 * @author Manuel Nguyen 741939 VA 
 * @author Digvijaysinh D. Raj 741976 VA
 */
public class Homepage_cittadini2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private Eventi_Avversi avversi = null;
	private ArrayList<Eventi_Avversi> listaAvversi = null;
	private String compare = "";
	private int count = 1;

	private JPanel contentPane;
	private JTextField Search_name;

	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JLabel Comune;
	private JTextField Search_comune;
	private JLabel Tipologia;
	private JComboBox<String> Search_type;
	private JButton button_type_municipality;
	private JLabel Nome_Centro;
	private JButton button_name;
	private JLabel goSignIn;
	private JLabel goLogin;
	private JLabel Mine_center;
	private JLabel Avversita;

	private ComboItem Resarch_name;
	private ComboItem Research_Municipaly_type;

	private ProxyServer proxy;

	private JTable Table;
	private JTable Table1;

	private String resultTable = "";

	private String[] resultTable2;
	private String[] arrayType = { "hub", "ospedaliero", "aziendale" };

	private String CFset = "";
	private String CFnormale = "";

	private int rowsCount;

	private JTextArea resultFromTable2;

	/**
	 * Il costruttore si occupa della creazione dell'interfaccia grafica
	 * 
	 * @exception IOException se non si riesce a comunicare con il server
	 * @exception Exception se il server è disconnesso
	 * 
	 */
	public Homepage_cittadini2() {

		listaAvversi = new ArrayList<Eventi_Avversi>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Resarch_name = new ComboItem("Cerca per nome", "Cerca per nome");
		Research_Municipaly_type = new ComboItem("Cerca per comune e tipologia", "Cerca per comune e tipologia");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 13, 664, 429);
		contentPane.add(tabbedPane);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Ricerca", null, panel_1, null);

		panel = new JPanel();
		tabbedPane.addTab("Avversità", null, panel, null);
		panel.setLayout(null);

		resultFromTable2 = new JTextArea();
		resultFromTable2.setBounds(10, 33, 613, 336);
		panel.add(resultFromTable2);
		resultFromTable2.setVisible(false);
		resultFromTable2.setLineWrap(true);

		Mine_center = new JLabel("Proprio Centro Vaccinale:");
		Mine_center.setHorizontalAlignment(SwingConstants.LEFT);
		Mine_center.setBounds(10, 11, 180, 14);
		panel.add(Mine_center);
		Mine_center.setVisible(false);

		Avversita = new JLabel("Per inserire eventuali avversità cliccare sul proprio centro vaccinale");
		Avversita.setForeground(Color.RED);
		Avversita.setBounds(200, 8, 423, 14);
		panel.add(Avversita);
		Avversita.setVisible(false);

		goLogin = new JLabel("Ritorna al Login");
		goLogin.setHorizontalAlignment(SwingConstants.CENTER);
		goLogin.setBounds(0, 0, 168, 14);
		contentPane.add(goLogin);
		goLogin.setForeground(Color.BLUE.darker());
		goLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		Table1 = new JTable();
		Table1.setVisible(false);

		goSignIn = new JLabel("Vai alla registrazione");
		goSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		goSignIn.setBounds(180, 0, 190, 14);
		contentPane.add(goSignIn);
		goSignIn.setForeground(Color.BLUE.darker());
		goSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JComboBox<ComboItem> comboBox_Research = new JComboBox<ComboItem>();
		comboBox_Research.setBounds(0, 0, 258, 22);
		comboBox_Research.addItem(Resarch_name);
		comboBox_Research.addItem(Research_Municipaly_type);
		panel_1.add(comboBox_Research);

		// componenti per ricerca per nome

		Nome_Centro = new JLabel("Nome Centro Vaccinale:");
		Nome_Centro.setHorizontalAlignment(SwingConstants.CENTER);
		Nome_Centro.setBounds(222, 100, 196, 14);
		panel_1.add(Nome_Centro);
		Nome_Centro.setVisible(true);

		Search_name = new JTextField();
		Search_name.setBounds(272, 143, 96, 20);
		panel_1.add(Search_name);
		Search_name.setColumns(10);
		Search_name.setVisible(true);

		button_name = new JButton("Cerca");
		button_name.setBounds(272, 191, 96, 23);
		panel_1.add(button_name);
		button_name.setVisible(true);

		// componenti per ricerca per tipologia e comune

		Comune = new JLabel("Comune:");
		Comune.setHorizontalAlignment(SwingConstants.CENTER);
		Comune.setBounds(272, 83, 96, 14);
		panel_1.add(Comune);
		Comune.setVisible(false);

		Search_comune = new JTextField();
		Search_comune.setBounds(272, 108, 96, 20);
		panel_1.add(Search_comune);
		Search_comune.setColumns(10);
		Search_comune.setVisible(false);

		Tipologia = new JLabel("Tipologia:");
		Tipologia.setHorizontalAlignment(SwingConstants.CENTER);
		Tipologia.setBounds(272, 139, 96, 14);
		panel_1.add(Tipologia);
		Tipologia.setVisible(false);

		Search_type = new JComboBox<String>();
		Search_type.setBounds(272, 164, 96, 20);
		panel_1.add(Search_type);
		Search_type.setVisible(false);

		for (int i = 0; i < arrayType.length; i++) {
			Search_type.addItem(arrayType[i]);
		}

		button_type_municipality = new JButton("Cerca");
		button_type_municipality.setBounds(272, 206, 96, 23);
		panel_1.add(button_type_municipality);
		button_type_municipality.setVisible(false);

		final JLabel resultFromTable = new JLabel();
		resultFromTable.setVerticalAlignment(SwingConstants.TOP);
		resultFromTable.setHorizontalAlignment(SwingConstants.CENTER);
		resultFromTable.setBounds(103, 240, 427, 134);
		resultFromTable.setForeground(Color.RED);
		panel_1.add(resultFromTable);
		resultFromTable.setVisible(false);

		Table = new JTable();
		Table.setVisible(false);
		
		comboBox_Research.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				JComboBox<Object> comboBox = (JComboBox<Object>) event.getSource();

				Object selected = comboBox.getSelectedItem();
				String command = event.getActionCommand();
				
				String value = ((ComboItem) selected).getValue();

				if ("Cerca per nome".equals(value)) {

					Comune.setVisible(false);
					Search_comune.setVisible(false);
					Tipologia.setVisible(false);
					Search_type.setVisible(false);
					button_type_municipality.setVisible(false);
					Table.setVisible(false);
					resultFromTable.setVisible(false);

					Nome_Centro.setVisible(true);
					Search_name.setVisible(true);
					button_name.setVisible(true);

				} else if ("Cerca per comune e tipologia".equals(value)) {

					Comune.setVisible(true);
					Search_comune.setVisible(true);
					Tipologia.setVisible(true);
					Search_type.setVisible(true);
					button_type_municipality.setVisible(true);
					resultFromTable.setVisible(false);
					Table.setVisible(false);

					Nome_Centro.setVisible(false);
					Search_name.setVisible(false);
					button_name.setVisible(false);

				}
			}
		});

		button_name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (Search_name.getText().toString().length() == 0) {
					resultFromTable.setText("campo vuoto");
					resultFromTable.setVisible(true);
					Table.setVisible(false);
				} else {

					try {

						try {
							proxy = new ProxyServer();
						} catch (IOException i) {
							i.printStackTrace();
						}

						resultTable = proxy.research_forName(Search_name.getText().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					if (resultTable.equals("niente")) {
						resultFromTable.setText("Non ho trovato nessun centro vaccinale registrato");
						resultFromTable.setVisible(true);
						Table.setVisible(false);
					} else {
						Vector<Vector> Righe = new Vector<Vector>();
						resultTable2 = new String[2];
						resultTable2 = resultTable.split("-");
						String nomeColonne = resultTable2[0];
						String datiRighe = resultTable2[1];
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

						Table = new JTable(Righe, colonne);
						Table.setBounds(10, 33, 637, 357);
						panel_1.add(Table);
						Table.setDefaultEditor(Object.class, null);

						Nome_Centro.setVisible(false);
						Search_name.setVisible(false);
						button_name.setVisible(false);
						resultFromTable.setVisible(false);
						Table.setVisible(true);

						Table.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								try {
									compare = Table.getValueAt(Table.getSelectedRow(), 6).toString();
								//	System.out.println(compare);
									if (Table.isCellSelected(Table.getSelectedRow(), 0)) {
										if (!(compare.equals("centro_vaccinale_id"))) {
											if (avversi == null) {
												avversi = new Eventi_Avversi(compare, null);

												avversi.getevents().setVisible(false);
												avversi.setTitle("Avversità delle vaccinazioni");
											
												avversi.setVisible(true);
												listaAvversi.add(avversi);

											} else if (avversi != null) {
												avversi = null;

											}
										}
									}
								} catch (Exception z) {

								}
							}
						});

					}

				}
			}
		});

		button_type_municipality.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (Search_comune.getText().toString().length() == 0) {
					resultFromTable.setText("campo vuoto");
					resultFromTable.setVisible(true);
					Table.setVisible(false);
				} else {

					try {

						try {
							proxy = new ProxyServer();
						} catch (IOException i) {
							i.printStackTrace();
						}
						resultTable = proxy.research_forType_Municipality(Search_comune.getText().toString(),
								Search_type.getSelectedItem().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					if (resultTable.equals("niente")) {
						resultFromTable.setText("Non ho trovato nessun centro vaccinale registrato");
						resultFromTable.setVisible(true);
						Table.setVisible(false);
					} else {
						Vector<Vector> Righe = new Vector<Vector>();
						resultTable2 = new String[2];
						resultTable2 = resultTable.split("-");
						String nomeColonne = resultTable2[0];
						String datiRighe = resultTable2[1];
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

						Table = new JTable(Righe, colonne);
						Table.setBounds(10, 33, 637, 357);
						panel_1.add(Table);
						Table.setDefaultEditor(Object.class, null);

						Comune.setVisible(false);
						Search_comune.setVisible(false);
						Tipologia.setVisible(false);
						Search_type.setVisible(false);
						button_type_municipality.setVisible(false);
						resultFromTable.setVisible(false);
						Table.setVisible(true);

						Table.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								try {
									compare = Table.getValueAt(Table.getSelectedRow(), 6).toString();

									if (!(compare.equals("centro_vaccinale_id"))) {
										if (avversi == null) {
											avversi = new Eventi_Avversi(compare, null);
											avversi.getevents().setVisible(false);
											avversi.setTitle("Avversità della vaccinazione");

											avversi.setVisible(true);
											listaAvversi.add(avversi);

										} else if (avversi != null) {
											avversi = null;
											count = 1;

										}
									}
								} catch (Exception z) {

								}
							}
						});

					}

				}
			}
		});

		
		goLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				(new Home2()).setVisible(true);
				
				for(Eventi_Avversi x : listaAvversi)
					x.dispose();
					
				listaAvversi.clear();
				dispose();

			}
		});
		
		goSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				(new Registrazione_Cittadini2()).setVisible(true);
				
				for(Eventi_Avversi x : listaAvversi)
					x.dispose();
					
				listaAvversi.clear();
				dispose();

			}
		});
		
		setResizable(false);
	}

	/**
	 * Il metodo mostra, se presenti, tutte le avversità del cittadino dopo che ha eseguito l'autenticazione
	 * 
	 * @exception Exception se non si riesce a comunicare con il server
	 */
	public void Severita() {
		String result2 = "";

		result2 = CFset;

		if (result2.equals("niente")) {
			resultFromTable2.setText("Non ho trovato nessun centro vaccinale associato alla tua somministrazione");
			resultFromTable2.setVisible(true);
			Table1.setVisible(false);
		} else {
			Vector<Vector> Righe = new Vector<Vector>();
			resultTable2 = result2.split("-");
			String nomeColonne = resultTable2[0];
			String datiRighe = resultTable2[1];
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

			Table1 = new JTable(Righe, colonne);
			Table1.setBounds(10, 33, 613, 336);
			panel.add(Table1);
			Table1.setDefaultEditor(Object.class, null);

			resultFromTable2.setVisible(false);
			Mine_center.setVisible(true);
			Avversita.setVisible(true);
			Table1.setVisible(true);

		}

		Table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {

					compare = Table1.getValueAt(Table1.getSelectedRow(), 0).toString();

					if (!(compare.equals("centro_vaccinale_id"))) {
						
						avversi = new Eventi_Avversi(compare, CFnormale);
						avversi.setVisible(true);
						listaAvversi.add(avversi);
					}
				} catch (Exception z) {

				}
			}
		});

	}

	/**
	 * Metodo per ottenere il riferimento corrente del label "Vai alla registrazione" e andare a registrarsi
	 * 
	 * @return riferimento del label
	 */
	public JLabel goSignIn() {
		return goSignIn;
	}

	/**
	 * Metodo per ottenere il riferimento corrente del label "Ritorna al Login" e ritornare al login
	 * 
	 * @return riferimento del label
	 */
	public JLabel goLogIn() {
		return goLogin;
	}

	/**
	 * Metodo per settare il codice fiscale del cittadino per la gestione delle avversità
	 */
	public void set(String string) {
		CFset = string;
	}

	/**
	 * Metodo per settare il codice fiscale del cittadino per la gestione delle avversità
	 */
	public void setCFnormale(String string) {
		CFnormale = string;
	}
	
	/**
	 * 
	 * @return riferimento del JTabbedPane per la gestione delle schede
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	/**
	 * 
	 * @return riferimento del panel generale del Frame
	 */
	public JPanel getPanel() {
		return panel;
	}
}

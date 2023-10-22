package oauth;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import jbutton_custom_users.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 * */

public class OAuthGestione extends JFrame {

	private static final long serialVersionUID = 1L;
	private Container contentPane;
	private JPanel panel;
	private String timeToken;
	private ProxyServer ps;
	private String[] emails;
	private Registrazione_OAuth registrazione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					OAuthGestione frame = new OAuthGestione(args[0]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public OAuthGestione(String check) throws Exception {
		
		ps = new ProxyServer();
		
		setBackground(new Color(119, 119, 255));
		
		timeToken = "Expired";
		
		panel = new JPanel();
		GridLayout gl = new GridLayout();		
		gl.setColumns(1);
		gl.setRows(0);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		panel.setLayout(gl);
		
		JLabel registati = new JLabel("Registrati");
		registati.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		registati.setForeground(Color.BLACK);
		registati.setHorizontalAlignment(SwingConstants.LEFT);
		registati.setVerticalAlignment(SwingConstants.TOP);
		panel.add(registati);
		
		String allUserOAuthString = ps.allUserOAuth();
		if(allUserOAuthString.equals("niente")) {
			JLabel nothing = new JLabel("Nessun utente trovato nel server OAuth");
			panel.setBackground(Color.RED);
			nothing.setFont(new Font("Tahoma", Font.BOLD, 22));
			nothing.setForeground(Color.WHITE);
			nothing.setHorizontalAlignment(SwingConstants.CENTER);
			nothing.setVerticalAlignment(SwingConstants.CENTER);
			panel.add(nothing);
			
		}
		else {
			System.out.println(allUserOAuthString);
			String[] splitUser = allUserOAuthString.split("-");
			emails = splitUser[1].split("___________");
			
			int num = emails.length;
			for(int i=0;i<num;i++) {
				String[] riga = emails[i].split(":");
				String email = riga[0];
				timeToken = riga[1];			
				OAuth_buttons_users oAuth_buttons = new OAuth_buttons_users(email, timeToken);
				panel.add(oAuth_buttons);
			}
		}
		
		this.addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent evt) {
			   
			   dispose();
			   ProcessBuilder builder = new ProcessBuilder(
			            "cmd.exe", "/c", "java -jar Operatori\\target\\Operatori-1.0.jar");
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
		
		registati.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					registrazione = new Registrazione_OAuth(check);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				registrazione.setVisible(true);
				
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
		
		JScrollPane scroll = new JScrollPane(panel, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		setResizable(false);
		contentPane.add(scroll);
		contentPane.setVisible(true);
		setBounds(100,100,519,145);
	}
}

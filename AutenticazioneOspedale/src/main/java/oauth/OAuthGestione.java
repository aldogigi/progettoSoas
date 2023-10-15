package oauth;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gianluca Fontana 21452A
 * @author Alex Rabuffetti "Matricola"
 * */

public class OAuthGestione extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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
	 */
	public OAuthGestione(String check) {
		
		if(check.equals("true")) {
			System.out.println("Login");
		}
		else{
			System.out.println("Registrazione");
		}
		
		System.out.println(System.getProperty("user.dir"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		table = new JTable();
		table.setPreferredSize(new Dimension(400 , 200));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		setBounds(100, 100, 695, 409);
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
	}
}

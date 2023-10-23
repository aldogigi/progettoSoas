package jbutton_custom_users;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import oauth.Login_OAuth;
import oauth.OAuthGestione;
import oauth.ProxyServer;

public class OAuth_buttons_users extends JButton {

	private static final long serialVersionUID = 1L;
	private ProxyServer ps;

	public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(new Color(119, 119, 255));
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private Login_OAuth loginOauth;
    /**
     * @wbp.nonvisual location=41,23
     */
    private final JLabel check_text;
    /**
     * @wbp.nonvisual location=281,43
     */
    private final JLabel labelTime;
    /**
     * @wbp.nonvisual location=466,33
     */
    private final JButton delete = new JButton("");

    public OAuth_buttons_users(String email, String timeToken, String checkLR, OAuthGestione oAuthGestione, String token) throws Exception{
        //  Init Color
    	
    	ps = new ProxyServer();
    	GridLayout gl = new GridLayout();

    	gl.setColumns(3);
    	gl.setRows(0);
    	setLayout(gl);
    	
    	check_text = new JLabel(email);
    	check_text.setHorizontalAlignment(SwingConstants.RIGHT);
    	check_text.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	check_text.setForeground(Color.WHITE);
    	add(check_text);
    	
		String timestampNew = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(Calendar.getInstance().getTime());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss", Locale.ENGLISH);

		java.util.Date datenew = formatter.parse(timestampNew);
		java.util.Date dateold = formatter.parse(timeToken);
		
		long duration  = datenew.getTime() - dateold.getTime();
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		
		labelTime = new JLabel("");
		
		if(diffInMinutes > 30) {
			labelTime.setText("Expired");
		}
		else {
			labelTime.setText(timeToken);
		}
    	
    	labelTime.setHorizontalAlignment(SwingConstants.RIGHT);
    	labelTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
    	labelTime.setForeground(Color.RED);
    	add(labelTime);
    	
    	ImageIcon icon = new ImageIcon("image/trash.png");
    	delete.setHorizontalAlignment(SwingConstants.RIGHT);
    	delete.setIcon(icon);
    	delete.setBorder(BorderFactory.createEmptyBorder());
    	delete.setContentAreaFilled(false);
    	delete.setFocusable(false);
    	delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				try {
					result = ps.deleteUserOAuth(token);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(result == 0){
					try {
						OAuthGestione oAuthGestione2 = new OAuthGestione(checkLR);
						oAuthGestione2.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(new JFrame(), "Utente eliminato con successo");
					oAuthGestione.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Errore");
				}
				
			}
		});
    	add(delete);
    	
    	setBackground(new Color(119, 119, 255));
    	color = new Color(119, 119, 255);
        colorOver = new Color(100, 100, 255);
        colorClick = new Color(90, 90, 255);
        borderColor = new Color(30, 136, 56);
        setContentAreaFilled(false);
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
        
        String time = labelTime.getText().toString();
        
        addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(checkLR.equals("login")) {
					if(time == "Expired") {
						
						 System.out.println("Expired login");
						 try {
							 oAuthGestione.setVisible(false);
							 loginOauth = new Login_OAuth(checkLR, token);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							 loginOauth.setVisible(true);
							 setVisible(false);
					}
					else {
						
						String presenceUserOAuth = "";
						try {
							presenceUserOAuth = ps.presenceUserOAuth(token);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(presenceUserOAuth.equals("0")) {
							oAuthGestione.dispose();
							ProcessBuilder builder = new ProcessBuilder(
						            "cmd.exe", "/c", "java -jar Operatori\\target\\Operatori-1.0.jar true " + token + "");
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
						else if (presenceUserOAuth.equals("-1")) {
								
							oAuthGestione.showOptionPane("Questo utente non e' presente all'interno del database degli Operatori");
							
						}
					}
				}
				else if(checkLR.equals("registrazione")) {
					if(time == "Expired") {
						
						
						
					}
					else {
						
						
						
					}
				}
			}
		
		});
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
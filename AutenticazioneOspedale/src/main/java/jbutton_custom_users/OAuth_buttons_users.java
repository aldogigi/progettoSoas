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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

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
    /**
     * @wbp.nonvisual location=41,23
     */
    private final JLabel check_text;
    /**
     * @wbp.nonvisual location=281,43
     */
    private final JLabel labelTime;

    public OAuth_buttons_users(String email, String timeToken) throws Exception{
        //  Init Color
    	
    	ps = new ProxyServer();
    	GridLayout gl = new GridLayout();

    	gl.setColumns(2);
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

				if(time == "Expired") {
					
					System.out.println("Expired");
					
				}
				else {
					System.out.println("Not expired");
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
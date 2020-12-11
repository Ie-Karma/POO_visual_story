package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data.character;

public class screenPanel extends JPanel implements ActionListener{
		
	@SuppressWarnings("unused")
	private String url_pres = new String("imgs/pressed_buttons/");
	private String url_all = new String("imgs/all_buttons/");
	private String url_dis = new String("imgs/disabled_buttons/");
	
	private ImageIcon ask = new ImageIcon(url_dis + "ask_to.png");
	private ImageIcon drop = new ImageIcon(url_dis + "drop.png");
	private ImageIcon give = new ImageIcon(url_dis + "give.png");
	private ImageIcon move = new ImageIcon(url_all + "move_to.png");
	private ImageIcon skip = new ImageIcon(url_all + "skip.png");
	private ImageIcon take = new ImageIcon(url_dis + "take.png");
	private ImageIcon top = new ImageIcon(url_all + "top.png");
	private ImageIcon player_img = new ImageIcon("imgs/Jugador.png");
		
	private character[] characters;
	
	private static final long serialVersionUID = 1L;

	public screenPanel() {
			
		Timer tm = new Timer(4,this);
		tm.start();
		
	}
	
	public void paint (Graphics g) {
				
		super.paintComponents(g);
								
		g.setColor(new Color(84, 92, 82,255));
		g.fillRect(0, 0, 1000, 800);
		
		g.setColor(characters[0].getLocation().getColor());
		g.fillRoundRect(10,9, 965, 695, 50, 50);
		
		g.drawImage(player_img.getImage(), 395, 400, null);
		g.drawImage(ask.getImage(), 0, 0, null);
		g.drawImage(drop.getImage(), 0, 0, null);
		g.drawImage(give.getImage(), 0, 0, null);
		g.drawImage(move.getImage(), 0, 0, null);
		g.drawImage(skip.getImage(), 0, 0, null);
		g.drawImage(take.getImage(), 0, 0, null);
		g.drawImage(top.getImage(), 0, 0, null);

		g.setFont(new Font("vcr osd mono", Font.PLAIN,40) );
		g.setColor(new Color(240,246,0,255));
		g.drawString(characters[0].getLocation().getName(), 400, 100);
		
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		if(characters[0].getObject() != null) {
			
			g.drawString(characters[0].getObject().getName(), 750, 97);
			
		}else {
			
			g.drawString("NOTHING", 750, 97);
			
		}
		
	}
	
	public character[] updateImages(character[] characters_0) {
				
		characters = characters_0;
				
		ask = new ImageIcon(url_dis + "ask_to.png");
		drop = new ImageIcon(url_dis + "drop.png");
		give = new ImageIcon(url_dis + "give.png");
		move = new ImageIcon(url_all + "move_to.png");
	 	skip = new ImageIcon(url_all + "skip.png");
		take = new ImageIcon(url_dis + "take.png");
		
		
		if(characters[0].getObject() != null) {
						
			drop = new ImageIcon(url_all + "drop.png");
			
			if(characters[0].isAsked()) {
				
				give = new ImageIcon(url_all + "give.png");

			}			
			
		}else {
			
			if(characters[0].getLocation().getGuestsNum() > 1) {
				
				ask = new ImageIcon(url_all + "ask_to.png");
			
			}

			if(characters[0].getLocation().getObjectsNum() > 0) {
				
				take = new ImageIcon(url_all + "take.png");
				
			}
			
		}
		return characters;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		repaint();
		
	}
	
	
}

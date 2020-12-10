package graphic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import data.character;

public class screen extends JFrame implements KeyListener{
		
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
	
	private int key_p = 0;
	private boolean option = false;
	
	private character[] characters;
	
	private static final long serialVersionUID = 1L;

	public screen() {
			
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(false);
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(64,50,51,255));
				
		addKeyListener(this);
		
		repaint();
		
	}
	
	public void paint (Graphics g) {
				
		super.paintComponents(g);
								
		g.setColor(new Color(241,154,62,255));
		g.fillRoundRect(10,33, 980, 657, 50, 50);
		
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
		
		key_p = -20;
		option = false;
		
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
		checker();
		if(key_p >= 0 && key_p <= 5) {System.out.println("KEY");option = true;repaint();}
		return characters;
	}
	
	private void checker() {
		
		switch (key_p) {
		
		case 0: 	 	
			skip = new ImageIcon(url_pres + "skip.png");break;
		case 1: 	 	
			move = new ImageIcon(url_pres + "move_to.png");break;
		case 2: 	 	
			if(characters[0].getObject() == null && characters[0].getLocation().getGuestsNum() > 1) {
				ask = new ImageIcon(url_pres + "ask_to.png");
				}else {key_p = -20;}
			;break;
		case 3: 	 	
			if(characters[0].getObject() != null && characters[0].isAsked()) {
				give = new ImageIcon(url_pres + "give.png");
			}else {key_p = -20;}
			;break;
		case 4: 	 	
			if(characters[0].getObject() == null && characters[0].getLocation().getObjectsNum() > 0) {
				take = new ImageIcon(url_pres + "take.png");
			}else {key_p = -20;}
			;break;
		case 5: 	 	
			if(characters[0].getObject() != null) {
				drop = new ImageIcon(url_pres + "drop.png");
			}else {key_p = -20;}
			;break;
		
		}
		
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		
		updateImages(characters);
		key_p = e.getKeyCode();
		key_p = key_p - 48;
		System.out.println(key_p);

		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public int getKey_p() {
		return key_p;
	}

	public void setKey_p(int key_p) {
		this.key_p = key_p;
	}
	
	
}

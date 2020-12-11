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
			
	//ints for animations
	private int animation = 0;
	private int xm = 730;
	private int selec = 0;
	private int max = 0;
	
	private character[] toAsk;
	private character[] characters;
	
	private static final long serialVersionUID = 1L;

	public screenPanel() {
			
		Timer tm = new Timer(1,this);
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
		
		switch (getAnimation()) {
		
		case 1:roomList(g);break;
		case 2:charactersList(g);break;
		case 4:objectsList(g);break;
		
		
		}
				
	}
	
	private void objectsList(Graphics g) {

		max = 0;
		for(int i = 0;characters[0].getLocation().getObjects()[i] != null; i++) {
			max++;
		}
		
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(273, xm-7, 444, 473, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		//g.fillRoundRect(280, 133, 430, 459, 50, 50);
		g.fillRoundRect(280, xm, 430, 459, 50, 50);
		//Name
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.drawString("-OBJECTS-", 425, xm+35);

		//selected
		g.setColor(new Color(240, 246, 0,255));
		g.fillRoundRect(290, xm+47, 410, 60, 30, 30);
		g.setColor(new Color(255, 100, 123,255));
		g.fillRoundRect(580, xm+57, 100, 40, 30, 30);
		//ENTER
		g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("ENTER", 600, xm+86);
		
		//characters	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.setColor(new Color(84, 92, 82,255));
		for(int i = selec; characters[0].getLocation().getObjects()[i] != null; i++) {
			
			g.drawString(characters[0].getLocation().getObjects()[i].getName(), 300, (xm+87) + (i-selec)*70);
			
		}
		
	}

	private void charactersList(Graphics g) {
		
		max = 0;
		
		//character img cover
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(700, xm+72, 278, 290, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(710, xm+80, 260, 275, 50, 50);
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(273, xm-7, 444, 473, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(280, xm, 430, 459, 50, 50);

		//Name
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.drawString("-CHARACTERS-", 425, xm+35);

		//selected
		g.setColor(new Color(240, 246, 0,255));
		g.fillRoundRect(290, xm+47, 410, 60, 30, 30);
		g.setColor(new Color(255, 100, 123,255));
		g.fillRoundRect(580, xm+57, 100, 40, 30, 30);
		//ENTER
		g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("ENTER", 600, xm+86);
		
		//characters	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.setColor(new Color(84, 92, 82,255));
		
		toAsk= new character[characters[0].getLocation().getGuestsNum()];
		
		int pos = 0;
		for(int i = 0; characters[0].getLocation().getGuests()[i] != null; i++) {
			
			if(characters[0].getLocation().getGuests()[i].equals(characters[0]) == false) {
				
				toAsk[pos] = characters[0].getLocation().getGuests()[i];
				max++;
				pos++;
				
			}
			
		}
		
		for(int i = selec; toAsk[i] != null; i++) {
			
				g.drawString(toAsk[i].getName(), 300, (xm+87) + (i-selec)*70);
				g.drawImage(toAsk[selec].getImg(), 720, xm+87, null);
	
		}
		
	}

	private void roomList(Graphics g) {
		
		max = 0;
		for(int i = 0;characters[0].getLocation().getNextTo()[i] != null; i++) {
			max++;
		}
		
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(273, xm-7, 444, 473, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		//g.fillRoundRect(280, 133, 430, 459, 50, 50);
		g.fillRoundRect(280, xm, 430, 459, 50, 50);
		//Name
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.drawString("-ROOMS-", 425, xm+35);

		//selected
		g.setColor(new Color(240, 246, 0,255));
		g.fillRoundRect(290, xm+47, 410, 60, 30, 30);
		g.setColor(new Color(255, 100, 123,255));
		g.fillRoundRect(580, xm+57, 100, 40, 30, 30);
		//ENTER
		g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
		g.setColor(new Color(84, 92, 82,255));
		g.drawString("ENTER", 600, xm+86);
		
		//characters	
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.setColor(new Color(84, 92, 82,255));
		for(int i = selec; characters[0].getLocation().getNextTo()[i] != null; i++) {
			
			g.drawString(characters[0].getLocation().getNextTo()[i].getName(), 300, (xm+87) + (i-selec)*70);
			
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

		if(getAnimation() != 0 && xm >133) {
			
			xm--;
			
		}
		if(getAnimation() == 0 && xm < 730) {
			
			xm++;
			
		}
		
		if(characters != null) {
		updateImages(characters);
		}
		repaint();
		
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public int getSelec() {
		//selec = 0;
		return selec;
	}

	public void setSelec(int selec) {
		this.selec = selec;
	}
	
	public void increaseSelec() {
		if(selec < max-1) {
		selec++;
		}
		
	}
	
	public void decreaseSelec() {
		if(selec > 0) {
		selec--;
		}
		
	}

	public character[] getToAsk() {
		return toAsk;
	}

	public void setToAsk(character[] toAsk) {
		this.toAsk = toAsk;
	}
	
}

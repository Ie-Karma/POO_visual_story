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
	private int opacity = 255;
	private double opacity_2 = 255;
	private boolean endScene = false;
	
	private character[] toAsk;
	private character[] characters;
	private character[] winners;
	
	private static final long serialVersionUID = 1L;

	public screenPanel() {
			
		Timer tm = new Timer(2,this);
		tm.start();
		
	}
	
	public void paint (Graphics g) {
				
		super.paintComponents(g);
								
		g.setColor(new Color(84, 92, 82,255));
		g.fillRect(0, 0, 1000, 800);
		
		if(endScene == false) {
			
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
			case 3:giveTo(g);break;
			case 4:objectsList(g);break;
			
			}

		}else {
			
			//podiums
			g.setColor(new Color(240, 246, 0,255));
			g.fillRoundRect(410, xm-390, 165, 1000, 20, 20);
			g.fillRoundRect(190, xm-290, 165, 1000, 20, 20);
			g.fillRoundRect(640, xm-190, 165, 1000, 20, 20);
			
			//pos
			g.setFont(new Font("vcr osd mono", Font.PLAIN,100) );
			g.setColor(new Color(84, 92, 82,255));
			g.drawString("1", 465, xm-280);
			g.drawString("2", 245, xm-180);
			g.drawString("3", 695, xm-80);

			//names
			g.setColor(new Color(240, 246, 0,255));
			g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
			g.drawString(winners[0].getName(), 440, xm-630);
			g.drawString(winners[1].getName(), 220, xm-530);
			g.drawString(winners[2].getName(), 670, xm-430);
			
			//end scene imgs
			g.drawImage(winners[0].getImg(), 370, xm-630, null);
			g.drawImage(winners[1].getImg(), 150, xm-530, null);
			g.drawImage(winners[2].getImg(), 600, xm-430, null);
			
			//end of the game
			if((int)(opacity_2) > 0) {g.setColor(new Color(0,0,0,((int)(opacity_2))));}
			else {g.setColor(new Color(0,0,0,0));}
			g.fillRect(0, 0, 1000, 800);
			g.setFont(new Font("vcr osd mono", Font.PLAIN,150) );
			if((int)(opacity_2) > 0) {g.setColor(new Color(84, 92, 82,((int)(opacity_2))));}
			else {g.setColor(new Color(84, 92, 82,0));}
			g.drawString("The game", 130, 300);
			g.drawString("is over", 170, 450);
			
		}		
		
		//fade rect
		g.setColor(new Color(84, 92, 82,opacity));
		g.fillRect(0, 0, 1000, 800);
		
	}
	
	private void giveTo(Graphics g) {
		
		//character img cover
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(700, xm+72, 278, 290, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(710, xm+80, 260, 275, 50, 50);

		g.drawImage(characters[0].getAsker().getImg(), 720, xm+87, null);
		
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(273, xm+113, 444, 215, 60, 60);
		//list
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(280, xm+120, 430, 200, 50, 50);
		//Name
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
		g.drawString("Do you want to give ",380,xm+150);
		g.drawString(characters[0].getObject().getName()
		+ " to " + characters[0].getAsker().getName() + "?", 390, xm+170);
		//yes no
		g.setColor(new Color(255, 100, 123,255));
		g.fillRoundRect(350, xm+220, 100, 70,30,30);
		g.fillRoundRect(520, xm+220, 100, 70,30,30);
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.drawString("YES",373,xm+250);
		g.drawString("(S)",373,xm+280);
		g.drawString("NO",553,xm+250);
		g.drawString("(ESC)",527,xm+280);
				
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

		
		if(endScene) {
			
			opacity_2 = opacity_2 - 0.5;
			
		}
		
		if(opacity > 0) {opacity--;}
				
		if(getAnimation() != 0 && xm > 133) {
			
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
	
	public void resetOpacity() {
		
		opacity = 255;
		
	}

	public boolean isEndScene() {
		return endScene;
	}

	public void setEndScene(boolean endScene) {
		this.endScene = endScene;
	}
	
	public void setWinners(character[] winners_0) {
		
		winners = winners_0;
		
	}
	
}

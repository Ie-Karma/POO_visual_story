package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data.character;

public class screenPanel extends JPanel implements ActionListener{
		
	//we create some urls string to make the image creation shorter
	private String url_all = new String("imgs/all_buttons/");
	private String url_dis = new String("imgs/disabled_buttons/");
	
	//we create all the images that we are gonna need
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
	private int offPacity = 0;
	
	private character[] toAsk;
	private character[] characters;
	private character[] winners;
	
	private static final long serialVersionUID = 1L;

	public screenPanel() {
			
		//we create and start a timer so we can make a repaint() with actionPerformed each 2ms
		Timer tm = new Timer(2,this);
		tm.start();
		
	}
	
	public void paint (Graphics g) {
				
		//we implement Graphics to paint String and images to make the animations and the GUI
		super.paintComponents(g);
								
		g.setColor(new Color(84, 92, 82,255));
		g.fillRect(0, 0, 1000, 800);
		
		//we check if the game still continues
		if(endScene == false) {
			
			g.setColor(characters[0].getLocation().getColor());
			g.fillRoundRect(10,9, 965, 695, 50, 50);
									
			//we draw all the images generating the complete GUI
			g.drawImage(player_img.getImage(), 395, opacity + 405 + offPacity, null);
			g.drawImage(ask.getImage(), opacity*3, 0, null);
			g.drawImage(drop.getImage(), opacity*3, opacity*3, null);
			g.drawImage(give.getImage(), opacity*(-1)*3, opacity*3, null);
			g.drawImage(move.getImage(), opacity*(-1)*3, 0, null);
			g.drawImage(skip.getImage(), opacity*3, opacity, null);
			g.drawImage(take.getImage(), opacity*(-1)*3, opacity*3, null);
			g.drawImage(top.getImage(), 0, opacity*(-3), null);

			//draw the name of the location
			g.setFont(new Font("vcr osd mono", Font.PLAIN,40) );
			g.setColor(new Color(240,246,0,255));
			g.drawString(characters[0].getLocation().getName(), 440, 100 + opacity*(-3));
			
			g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
			
			//and if the player has object we print its name
			if(characters[0].getObject() != null) {
				
				g.drawString(characters[0].getObject().getName(), 750, 97 + opacity*(-3));
				
			}else {
				
				g.drawString("NOTHING", 750, 97 + opacity*(-3));
				
			}
			
			//we activate the list and animations depending the Animation that the player has selected on screenFrame keylistener
			switch (getAnimation()) {
			
			case 1:roomList(g);break;
			case 2:charactersList(g);break;
			case 3:giveTo(g);break;
			case 4:objectsList(g);break;
			case 6:beliefsList(g);break;
			
			}

		}else {
			
			//if the game has ended we draw the podiums with the winners
			
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
		
		//we print invisible rectangles that cover all the screen
		//theis opacity depends on offpacity and opacity
		//with that we can make some fade animations
		
		//fade rect
		g.setColor(new Color(84, 92, 82,opacity));
		g.fillRect(0, 0, 1000, 800);
		
		//off rect
		g.setColor(new Color(84, 92, 82,offPacity));
		g.fillRect(0, 0, 1000, 800);
		
		//good bye
		g.setColor(new Color(255,255,255,offPacity));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,150) );
		g.drawString("GOOD BYE", 150, 350);
		
	}
	
	private void beliefsList(Graphics g) {
		
		//we draw the whole beliefs list
		
		//shadow
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(93, xm-7, 814, 473, 60, 60);
		
		//list
		g.setColor(new Color(244, 211, 94,255));
		g.fillRoundRect(100, xm, 800, 459, 50, 50);
		
		//line
		g.setColor(new Color(84, 92, 82,255));
		g.fillRoundRect(480, xm-5, 15, 470, 1, 1);
		
		//type
		g.setColor(new Color(84, 92, 82,255));

		g.drawString("-CHARACTERS-", 180, xm+50);
		g.drawString("-OBJECTS-", 620, xm+50);
		
		//beliefs	
		for(int i = selec+1; characters[0].getCharBeliefs()[i] != null && i<6; i++) {
			
			g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
			g.drawString(characters[0].getCharBeliefs()[i].getName() + " was in "+characters[0].getCharBeliefs()[i].getLocation().getName(), 150, (xm+90) + (i-selec-1)*70);
			
			g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
			
		}
		
		for(int i = selec; characters[0].getObjBeliefs()[i] != null && i<6; i++) {
			
			g.setFont(new Font("vcr osd mono", Font.PLAIN,20) );
			
			if(characters[0].getObjBeliefs()[i].getLocation() != null) {
			g.drawString(characters[0].getObjBeliefs()[i].getName() + " was in "+characters[0].getObjBeliefs()[i].getLocation().getName(), 550, (xm+90) + (i-selec)*70);
			}
			
			if(characters[0].getObjBeliefs()[i].getOwner() != null) {
			g.drawString(characters[0].getObjBeliefs()[i].getName() + " was in "+characters[0].getObjBeliefs()[i].getOwner().getName(), 550, (xm+90) + (i-selec)*70);
			}
			g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
			
		}
		
	}

	private void giveTo(Graphics g) {
		
		//we draw a menu with S(yes) or ESC(no) to select if the player gives or thot his object
		
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

		//we draw a list with all the objects in the same room as the player
		
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
		
		//we draw a list with all the characters in the same room as the player
		
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
		g.drawString("-CHARACTERS-", 400, xm+35);

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
		//Name
		g.setColor(new Color(84, 92, 82,255));
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.drawString(Integer.toString(pos), 300, xm+35);
		pos = 0;
		
		g.setFont(new Font("vcr osd mono", Font.PLAIN,30) );
		g.setColor(new Color(84, 92, 82,255));
		
		for(int i = selec; toAsk[i] != null && pos < 6; i++) {
			
				g.drawString(toAsk[i].getName(), 300, (xm+87) + (i-selec)*70);
				g.drawImage(toAsk[selec].getImg(), 720, xm+87, null);
				pos++;
	
		}
		
	}

	private void roomList(Graphics g) {
		
		//we draw a list with all the accessible rooms from the actual player room
		
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
				
		//we replace the images of the GUI depending on the options that the player can make
		
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
			
			opacity_2 = opacity_2 - 0.1;
			
		}
		if((int)(opacity_2) == -250) {System.exit(0);}
		
		if(opacity > 0) {opacity--;}
		
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

	/////////////////////////////////////////////////////gets and sets
	
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
		if(selec < max-1 && animation != 6) {
		selec++;
		}
		
		if(animation == 6) {selec++;}
		
	}
	
	public void decreaseSelec() {
		if(selec > 0 ) {
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

	public int getOffPacity() {
		
		return offPacity;
		
	}
	
	public void decreaseOffPacity() {
		offPacity = 0;
	}

	public void increaseOffPacity() {
		offPacity+=5;
	}
	
}

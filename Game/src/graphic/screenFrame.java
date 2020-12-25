package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import tools.*;
import data.character;
import data.object;
import data.room;

public class screenFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private actionsTools tool = new actionsTools();
	private dataTools data = new dataTools();
	private int key = -20;
	private room[] rooms;
	private character[] characters;
	private object[] objects;
	private screenPanel panel;
	
	public screenFrame(room[] rooms_0,character[] characters_0, object[] objects_0) throws IOException {
				
		setUndecorated(true);
		
		//we update the data received when invoking it from intro
		rooms = rooms_0;
		characters = characters_0;
		objects = objects_0;
		characters = data.playerOnArray(characters);
		characters[0].setBeliefs(characters,objects);
		
		//we create a screenpanel that we use to paint and draw the GUI
		panel = new screenPanel();
		
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(false);
		setSize(985,715);
		centerFrame();
		setDefaultCloseOperation(3);
		getContentPane().setBackground(new Color(64,50,51,255));
		panel.updateImages(characters);
		add(panel);
		
		//now we use the KeyListener in this class and not in intro
		addKeyListener(this);
		updateData();
		
	}
	
	private void centerFrame() {

		//we use this function only to center the game in relation to the screen used
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        //if the game exits the screen from the bottom or the top delete the following code and uncomment the next
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
        
        /*
        int dx = centerPoint.x - windowSize.width / 2;
        setLocation(dx, 0);
        */
        
   }
	
	//we handle all this class through the keyListener
	@Override
	public void keyPressed(KeyEvent e) {

		//we set the key with -48 so it is on decimal and not on ASCII
		setKey(e.getKeyCode()-48);
	
		unnAsked();
		
		panel.updateImages(characters);

		//we make a big switch to know what action is the player doing
		switch(key) {
		
		case 0:
			
			//SKIP
			//in case he press 0 we delete the asker and we make all the other characters do a random action 
			characters[0].setAsked(false);
			characters[0].setAsker(null);
			updateData();
			characters = tool.randomAction(characters,getKey());
			panel.setAnimation(0);
			panel.setSelec(0);
			panel.resetOpacity();
			break;
			
		case 1: 	
			
			// MOVE TO
			//we open the rooms list
			panel.setAnimation(1);					
			break;
			
		case 2:
			
			//ASK TO
			//we open the characters list only if the player doesn't have any object
			if(characters[0].getObject() == null && characters[0].getLocation().getGuestsNum() > 1) {
				
				panel.setAnimation(2);
								
			}
			break;

		case 3: 
			
			//GIVE
			//we open a menu only if the character has and object and is asked
			if(characters[0].isAsked() && characters[0].getObject() != null) {
				
				panel.setAnimation(3);
				
			}
			break;
			
		case 4:
			
			//TAKE
			//we open a list of objects in that room only if the player doesn't have any object
			if(characters[0].getObject() == null && characters[0].getLocation().getObjectsNum() > 0) {
				
				panel.setAnimation(4);
				
			}
			break;
		
		case 5:	
			
			//DROP
			//if the player has and object we activate his dropObject function
			//and make all the other characters do a random action 
			if(characters[0].getObject() != null) {
								
				characters[0].dropObject();
				updateData();
				characters = tool.randomAction(characters,getKey());
				panel.setAnimation(0);
				panel.setSelec(0);
				panel.resetOpacity();
				
			}break;
		
		case 6:
			
			//BELIEFS
			//we open the beliefs list
			panel.setAnimation(6);
			break;
			
		case (-8):
			
			//use this to move between the options in the lists
			panel.increaseSelec();
			break;
		
		case (-10):
			
			//use this to move between the options in the lists
			panel.decreaseSelec();
			break;
			
		case (-21):
			
			//ESC
			//continuously increments the offpacity value 
			//if the esc key is pressed. When it reaches 255 close the game
			panel.increaseOffPacity();
			if(panel.getOffPacity() == 255) {
				
				System.exit(0);
				
			}
			panel.setAnimation(0);
			panel.setSelec(0);
			break;
				
		case (35):
			
			//IS ASKED
			//when player is asked if he press S he will give his object to his asker
			//and make all the other characters do a random action 
			if(panel.getAnimation() == 3) {
				
				panel.setAnimation(0);
				giveObject();				
				updateData();
				characters = tool.randomAction(characters,getKey());

			}
			break;
			
		case (-38):
					
			//we make a change if the player presses ENTER depending on the animation, which indicates the list he has open
			//we use Selec to know the option the player has selected from the list
			switch(panel.getAnimation()){
				
			case 1:
				
				//MOVE TO
				//we replace the actual room of the player with the room selected from the room list
				characters[0].moveTo(characters[0].getLocation().getNextTo()[panel.getSelec()]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				if(characters[0].completeGoal()) {finish();}
				break;
				
			case 2:
				
				//ASK TO
				//we make asked the character that the player has selected
				panel.getToAsk()[panel.getSelec()].setAsked(true);
				panel.getToAsk()[panel.getSelec()].setAsker(characters[0]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				break;
				
			case 4:
				
				//TAKE
				//we replace the location and the owner of the selected object
				characters[0].takeObject(characters[0].getLocation().getObjects()[panel.getSelec()]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				break;
				
			}
			characters[0].updateBeliefs(characters, objects);
			panel.setAnimation(0);
			panel.setSelec(0);
			panel.resetOpacity();
			break;

		}

		unnAsked();
		updateData();
		panel.updateImages(characters);

	}
	
	private void unnAsked() {
		
		//we use this function to determine if the character is being asked by someone in the same room
		//if not, we set Asked to false and eliminate the Asker
		for(int i = 0; characters[i] != null; i++) {
			
			if(characters[i].isAsked() && characters[i].getAsker().getLocation().equals(characters[i].getLocation()) == false ) {
				characters[i].setAsked(false);
				characters[i].setAsker(null);
			}
			
		}

		updateData();
		panel.updateImages(characters);
		panel.repaint();
		
	}
	
	private void giveObject() {
		
		//we use this function to replace the owner of the object that the player have give
		object obj = characters[0].getObject();
		obj.setLocation(null);
		characters[0].setObject(null);
		characters[0].getAsker().takeObject(obj);
					
		for(int i = 0; characters[0].getObjBeliefs()[i] != null; i++) {
			
			if(characters[0].getObjBeliefs()[i].getName().equals(obj.getName())) {
				
				characters[0].getObjBeliefs()[i] = obj;
				
			}
			
		}

		characters[0].setAsked(false);
		characters[0].setAsker(null);
		
	}
	
	private void updateData() {
		
		//a simple function to update the arrays in the data class withe the new ones
		data.updateData(rooms, characters, objects);
		data.updateRooms(rooms);
		characters = data.getCharacters();
		rooms = data.getRooms();
		objects = data.getObjects();
		panel.updateImages(characters);
		
	}
	
	private void finish() {
		
		//when the game has finish (the player has reached his goals)
		//we force the rest of the characters to perform random actions until all have completed their goals
		//when they all finish we create a podium with the 3 first ones 
		//we take that from their Medal
		int round = 0;
		
		characters[0].setMedal(tool.getPodium());
		tool.setPodium((tool.getPodium()+1));
				
		while(data.endGame(characters) == false) {
						
			updateData();
			characters = tool.randomAction(characters,getKey());
			round++;
			
		}

		for(int i = 0; characters[i] != null; i++) {
			
			if(characters[i].getMedal() == -1) {characters[i].setMedal(tool.getPodium());
			tool.setPodium((tool.getPodium()+1));
			}
			
		}
		
		System.out.println("");
		System.out.println(round + " rounds after...");
		System.out.println("");
						
		character[] winners = new character[3];
								
			for(int i = 0; characters[i] != null; i++) {
			
				
				switch (characters[i].getMedal()) {
				
				case 0 : winners[0] = characters[i];break;
				case 1 : winners[1] = characters[i];break;
				case 2 : winners[2] = characters[i];break;

				}
			
			}
		
		updateData();panel.setWinners(winners);panel.setEndScene(true);panel.resetOpacity();

		
	}
	
	/////////////////////////////////////////////////////gets, sets and other functions
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		panel.decreaseOffPacity();
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
}

package graphic;

import java.awt.Color;
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
				
		rooms = rooms_0;
		characters = characters_0;
		objects = objects_0;
		characters = data.playerOnArray(characters);
		
		panel = new screenPanel();
		
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(false);
		setSize(1000,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(64,50,51,255));
		panel.updateImages(characters);
		add(panel);
		
		addKeyListener(this);

		
	}
	
	private void giveObject() {
		
		
		object obj = characters[0].getObject();
		obj.setLocation(null);
		characters[0].setObject(null);
		characters[0].getAsker().takeObject(obj);
			
		System.out.println(obj.getName() + " Entregado a " + obj.getOwner().getName());

		characters[0].setAsked(false);
		characters[0].setAsker(null);
		
	}
	
	private void updateData() {
		
		data.updateData(rooms, characters, objects);
		data.updateRooms(rooms);
		characters = data.getCharacters();
		rooms = data.getRooms();
		objects = data.getObjects();
		panel.updateImages(characters);
		
	}
	
	private void finish() {
		
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
		
		System.out.println("- TOP 3 WINNERS -");
				
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
	
	@Override
	public void keyPressed(KeyEvent e) {

		setKey(e.getKeyCode()-48);
		//System.out.println(getKey());
		
		updateData();
		
		if(characters[0].isAsked() && characters[0].getAsker().getLocation().equals(characters[0].getLocation()) == false ) {
			characters[0].setAsked(false);
			characters[0].setAsker(null);
		}
		
		panel.updateImages(characters);

		switch(key) {
		
		case 0:	//System.out.println("You have chosen to Skip this round");
			updateData();
			characters = tool.randomAction(characters,getKey());
			panel.setAnimation(0);
			panel.setSelec(0);
			panel.resetOpacity();
			break;
			
		case 1: 	
			
			panel.setAnimation(1);					
			break;
			
		case 2:
			if(characters[0].getObject() == null && characters[0].getLocation().getGuestsNum() > 1) {
				
				panel.setAnimation(2);
								
			}
			break;

		case 3: 
			
			if(characters[0].isAsked() && characters[0].getObject() != null) {
				
				panel.setAnimation(3);
				
			}
			break;
			
		case 4:
			if(characters[0].getObject() == null && characters[0].getLocation().getObjectsNum() > 0) {
				
				panel.setAnimation(4);
				
			}
			break;
		
		case 5:	
			if(characters[0].getObject() != null) {
				
				//System.out.println("You have droped " + characters[0].getObject().getName() + " in " + characters[0].getLocation().getName());
				
				characters[0].dropObject();
				updateData();
				characters = tool.randomAction(characters,getKey());
				panel.setAnimation(0);
				panel.setSelec(0);
				panel.resetOpacity();
				
			}break;
			
		case (-8):
			panel.increaseSelec();
			break;
		
		case (-10):
			panel.decreaseSelec();
			break;
			
		case (-21):
			panel.setAnimation(0);
			panel.setSelec(0);
			break;
				
		case (35):
			if(panel.getAnimation() == 3) {
				
				panel.setAnimation(0);
				giveObject();				
				updateData();
				characters = tool.randomAction(characters,getKey());

			}
			break;
			
		case (-38):
		
			//System.out.println("Accion: " + panel.getAnimation() + ", seleccion: " + panel.getSelec());
			
			switch(panel.getAnimation()){
				
			case 1:
				characters[0].moveTo(characters[0].getLocation().getNextTo()[panel.getSelec()]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				if(characters[0].completeGoal()) {finish();}
				break;
				
			case 2:
				panel.getToAsk()[panel.getSelec()].setAsked(true);
				panel.getToAsk()[panel.getSelec()].setAsker(characters[0]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				break;
				
			case 4:
				characters[0].takeObject(characters[0].getLocation().getObjects()[panel.getSelec()]);
				updateData();
				characters = tool.randomAction(characters,getKey());
				break;
				
			}
			panel.setAnimation(0);
			panel.setSelec(0);
			panel.resetOpacity();
			break;

		}

		updateData();
		panel.updateImages(characters);

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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

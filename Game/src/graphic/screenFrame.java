package graphic;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import tools.*;
import data.character;
import data.object;

public class screenFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private actionsTools tool = new actionsTools();
	private int key = -20;
	private character[] characters;
	
	public screenFrame(screenPanel panel) {
		
		setTitle("Visual Story - By Mario Gallego Cano");
		setVisible(true);
		setResizable(true);
		setSize(1000,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(64,50,51,255));
		add(panel);
		
		addKeyListener(this);

		
	}
	
	public character[] updateData(character[] characters_0) {
		
		characters = characters_0;
		
		//characters = tool.randomAction(characters,getKey());
		
		return characters;
	}
	
	private void giveObject() {
		
		
		object obj = characters[0].getObject();
		characters[0].setObject(null);
		characters[0].getAsker().takeObject(obj);
			
		System.out.println(obj.getName() + " Entregado a " + obj.getOwner().getName());

		characters[0].setAsked(false);
		characters[0].setAsker(null);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		setKey(e.getKeyCode()-48);
		System.out.println(getKey());
		
		switch(key) {
		
		case 0:	System.out.println("You have chosen to Skip this round");
		
			characters = tool.randomAction(characters,getKey());break;
			
		case 1: System.out.print("You have moved from " + characters[0].getLocation().getName());
		
			characters[0].setLocation(characters[0].getLocation().getNextTo()[0]);
			System.out.println(" to " + characters[0].getLocation().getName());

			characters = tool.randomAction(characters,getKey());
			
			
			break;
		case 2:
			if(characters[0].getObject() == null && characters[0].getLocation().getGuestsNum() > 1) {
				
				//print lista characters to ask
				characters = tool.randomAction(characters,getKey());
				
			}

			break;
		case 3: 
			
			if(characters[0].isAsked() && characters[0].getObject() != null) {
				
				giveObject();
				characters = tool.randomAction(characters,getKey());
				
			}
			break;
		case 4:
			if(characters[0].getObject() == null && characters[0].getLocation().getObjectsNum() > 0) {
				
				//desplegar lista de objetos
				
				characters = tool.randomAction(characters,getKey());
				
			}
		break;
		
		case 5:	
			if(characters[0].getObject() != null) {
				
				System.out.println("You have droped " + characters[0].getObject().getName() + " in " + characters[0].getLocation().getName());
				
				characters[0].dropObject();
				characters = tool.randomAction(characters,getKey());
				
			}
			
		
		}
		
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

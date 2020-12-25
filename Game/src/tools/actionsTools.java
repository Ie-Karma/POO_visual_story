package tools;

import data.*;

public class actionsTools {
		
	private int podium = 0;
		
	public character[] randomAction(character[] character, int key) {
		
		//we use this class to determine the random actions that the rest of the characters take
		for(int i = 1; character[i] != null; i++) {

		object obj;
		int options[] = {0,1,0,1};
		int optionsNum = 2;
		int choice = (int)(Math.random()*4);
				
		obj = character[i].getObject();

		//we generate a random number only with the options that the character can make
		if(character[i].completeGoal() == true) {
			
			if(character[i].getMedal() == -1) {
				
				character[i].setMedal(podium);
				podium++;
				
			}
			choice = 0;

			if(character[i].getObject() != null && character[i].getObject().equals(character[i].getGoal_object())==false) {
				choice = 5;
			}
			
		}else {
			
			if(obj == null) {
				
				character[i].setAsked(false);
				character[i].setAsker(null);
				
				if(randomAsked(character[i],0) > 0) {
					options[optionsNum] = 2;
					optionsNum++;
				}
				if(character[i].getLocation().getObjectsNum() > 0) {
					options[optionsNum] = 4;
					optionsNum++;
				}
				
			}else {
				
				if(character[i].isAsked() == true) {
					
					options[optionsNum] = 3;
					optionsNum++;
					
				}
				
				options[optionsNum] = 5;
				optionsNum++;
				
			}
			
			choice = options[choice];

		}
		
		//when we have the random number we execute the corresponding action 
		switch(choice) {
		
		case 0: 	
			
			//SKIP	
			break;
			
		case 1: 	
			
			//MOVE TO 	
			character[i].moveTo(randomRoom(character[i]));
			break;
			
		case 2: 	
			
			//ASK TO
			randomAsked(character[i],1);
			break;
			
		case 3: 
			
			//GIVE TO	
			giveObject(character[i]);
			break;
			
		case 4: 	
			
			//TAKE	
			character[i].takeObject(character[i].getLocation().getObjects()[(int)(Math.random()*(character[i].getLocation().getObjectsNum()))]);
			character[i].getObject().setLocation(null);
			break;
			
		case 5: 	
			//DROP
			character[i].dropObject();
			break;

		}
		
		}
		return character;
		
	}
	
	private void giveObject(character character) {
		
		//we replace the owner of the selecter object
		if(((int)(Math.random()*4))>0) {
			
			object obj = character.getObject();
			character.setObject(null);
			character.getAsker().takeObject(obj);;
		}
		
		character.setAsked(false);
		character.setAsker(null);
		
	}
	
	private room randomRoom(character character) {
		
		//we select a random room between all the available rooms
		room[] rooms;
		int rand = 0;
		int nextNum = 0;
		rooms = character.getLocation().getNextTo();
		
		for(int i = 0; rooms[i] != null; i++) {
			
			nextNum++;
			
		}
		
		rand = (int)(Math.random()*nextNum);
		
		return rooms[rand];
		
	}
	
	private int randomAsked(character character,int com) {
		
		//we select a random character between all the characters in the same room
		character[] people;
		int peopleNum = 0;
		
		people = character.getLocation().getGuests();
		
		if(com == 0) {
			
			for(int i = 0; people[i] != null; i++) {
				
				if(people[i].equals(character)) {peopleNum--;}
				peopleNum++;
				
			}
			
		}else {
			
			for(int i = 0; people[i] != null; i++) {
				
				if(people[i].equals(character)) {peopleNum--;}
				peopleNum++;
				
			}
		
				int random = (int)(Math.random()*peopleNum);
				
				if(people[random].equals(character)) {
					
					if(random==0) {random++;}
					else {random--;}
					
				}
				
				people[random].setAsked(true);
				people[random].setAsker(character);
							
		}

		return peopleNum;
		
	}
		
	/////////////////////////////////////////////////////gets and sets
	
	public int getPodium() {
		
		return podium;
		
	}
	
	public void setPodium(int x) {
		
		podium = x;
		
	}
}

package tools;

import data.*;

public class actionsTools {
		
	private int podium = 1;
	
	public character randomAction(character character) {
		
		/*
		options:
		
		skip = 0
		move to = 1
		ask for = 2 (no object)
		give to = 3
		take object = 4 (no object)
		drop object = 5
		*/
		
		object obj;
		int options[] = {0,1,0,1};
		int optionsNum = 2;
		int choice = (int)(Math.random()*4);
				
		obj = character.getObject();

		if(character.completeGoal() == true) {
			
			if(character.getMedal() == 0) {
				
				System.out.println("Podium de " + character.getName());
				character.setMedal(podium);
				podium++;
				
			}
			
			choice = 0;
			
		}else {
			
			if(obj == null) {
				
				character.setAsked(false);
				character.setAsker(null);
				
				if(randomAsked(character,0) > 0) {
					options[optionsNum] = 2;
					optionsNum++;
				}
				if(character.getLocation().getObjectsNum() > 0) {
					options[optionsNum] = 4;
					optionsNum++;
				}
				
			}else {
				
				if(character.isAsked() == true) {
					
					options[optionsNum] = 3;
					optionsNum++;
					
				}
				
				options[optionsNum] = 5;
				optionsNum++;
				
			}
			
			choice = options[choice];

		}

		System.out.print(character.getName() + " has chosen ");
		
		switch(choice) {
		
		case 0: 	
			System.out.println("SKIP");	
			character.skip();
			break;
			
		case 1: 	
			System.out.print("MOVE TO ");	
			character.moveTo(randomRoom(character));
			System.out.println(character.getLocation().getName());
			break;
			
		case 2: 	
			System.out.print("ASK TO ");	
			randomAsked(character,1);
			break;
			
		case 3: 	
			System.out.println("GIVE TO ");	
			giveObject(character);
			break;
			
		case 4: 	
			System.out.println("TAKE OBJECT ");	
			character.takeObject(character.getLocation().getObjects()[(int)(Math.random()*(character.getLocation().getObjectsNum()))]);
			character.getObject().setLocation(null);
			break;
			
		case 5: 	
			System.out.println("DROP OBJECT");
			character.dropObject();
			break;

		}
		
		return character;
		
	}
	
	private void giveObject(character character) {
		
		if(((int)(Math.random()*4))>0) {
			
			object obj = character.getObject();
			character.setObject(null);
			character.getAsker().takeObject(obj);;
			
			System.out.println(obj.getName() + " Entregado a " + obj.getOwner().getName());

		}
		
		character.setAsked(false);
		character.setAsker(null);
		
	}
	
	private room randomRoom(character character) {
		
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
				
				System.out.println(people[random].getName());
			
		}

		return peopleNum;
		
	}
		
}

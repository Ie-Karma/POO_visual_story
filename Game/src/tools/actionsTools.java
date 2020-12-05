package tools;

import data.*;

public class actionsTools {
	
	private room rooms_ran[];
	
	public void randomAction(character character) {
		
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
			
			choice = 0;
			
		}else {
			
			if(obj == null) {
				
				options[optionsNum] = 2;
				optionsNum++;
				options[optionsNum] = 4;
				optionsNum++;
				
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
			System.out.println("ASK FOR");	
			
			break;
			
		case 3: 	
			System.out.println("GIVE TO");	
			
			break;
			
		case 4: 	
			System.out.println("TAKE OBJECT");	
			
			break;
			
		case 5: 	
			System.out.println("DROP OBJECT");
			character.dropObject();
			break;

		
		}
		
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
	
	public void updateGuests(room[] rooms, character[] characters) {
		
		for(int i = 0; rooms[i] != null; i++) {
			
			rooms[i].setGuestsNum(0);
			rooms[i].resetGuests();
			
		}
		
		for(int i = 0; characters[i] != null; i++) {
			
			characters[i].getLocation().setGuests(characters[i]);
			characters[i].getLocation().increaseGuestsNum();
			
		}
	
		rooms_ran = rooms;
		
	}

	public room[] getRooms_ran() {
		return rooms_ran;
	}

	public void setRooms_ran(room rooms_ran[]) {
		this.rooms_ran = rooms_ran;
	}
	
}

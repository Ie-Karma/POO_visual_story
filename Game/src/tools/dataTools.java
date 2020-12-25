package tools;

import data.*;

public class dataTools {

	@SuppressWarnings("unused")
	private int podium = 1;
	
	private room rooms[];
	private character characters[];
	private object objects[];
		
	public boolean endGame(character[] ch) {
		
		//a function just to determine if all the characters have finished their goals
		boolean end = true;
		characters = ch;
		
		for(int i = 0; characters[i] != null; i++) {
			
			if(characters[i].completeGoal() == false) {
				
				end = false;
				
			}
			
		}
		
		return end;
		
	}
	
	public character[] playerOnArray(character[] ch) {
		
		//a function to make the player character position = 0 on the characters array
		characters = ch;
		int pos = 0;
		
		for (int i = 0; characters[i] != null; i++) {
			
			if(characters[i].getName().equals("PLAYER")) {pos = i;}
			
		}
				
		if(pos != 0) {
			
			character comodin;
			comodin = characters[0];
			characters[0] = characters[pos];
			characters[pos] = comodin;
			
		}
		
		return characters;
		
	}
	
	public void updateData(room[] rm, character[] ch, object[] obj){
		
		//updates current data values
		rooms=rm;
		characters = ch;
		objects = obj;
		
	}
	
	public room[] updateRooms(room[] rm) {
	
		//update and return rooms values with the changes of guests and objects
		rooms = rm;
		updateRoomsGuests();
		updateRoomObjects();
		return rooms;
		
	}
	
	private void updateRoomsGuests() {
		
		//take the location of each character to update the number of guests in rooms
		for(int i = 0; rooms[i] != null; i++) {
			
			rooms[i].setGuestsNum(0);
			rooms[i].resetGuests();
			
		}
		
		for(int i = 0; characters[i] != null; i++) {
			
			characters[i].getLocation().setGuests(characters[i]);
			characters[i].getLocation().increaseGuestsNum();
			
		}
		
	}
	
	private void updateRoomObjects() {
		
		//take the location of each object to update the number of guests in rooms
		for(int o = 0; rooms[o] != null; o++) {
			
			int x = 0;
			
			for(int i = 0; objects[i] != null; i++) {
			
				if(objects[i].getLocation()==rooms[o]) {
					
					rooms[o].setObjects(x,objects[i]);
					x++;
					
				}
				
			}
			
			rooms[o].setObjectsNum(x);
			
		}
				
	}
	
	///////////////////////////////////////////////////////////////////gets and sets

	public room[] getRooms() {
		return rooms;
	}

	public character[] getCharacters() {
		return characters;
	}

	public object[] getObjects() {
		return objects;
	}

}

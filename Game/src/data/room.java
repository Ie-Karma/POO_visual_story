package data;

import java.awt.Color;

public class room extends DataFather{
	
	//int to save the numer of guests
	//character array to save all the characters found in that room 
	private int guestsNum = 0;
	private character[] guests = new character[100];
	
	//int to save the numer of objects
	//object array to save all the characters found in that room 
	private int objectsNum = 0;
	private object[] objects = new object[100];
	
	//a unique color to identify the room
	//and a room array to save the accessible rooms 
	private Color color;
	private room[] nextTo = new room[100];
	
	public room(String name_set){
		
		setName(name_set);
		
		//with a,b,c we generate 3 random ints between 0 and 255 to creathe his color
		int a,b,c;
		
		a = (int)(Math.random()*250);
		b = (int)(Math.random()*250);
		c = (int)(Math.random()*250);

		setColor(new Color(a,b,c,255));
				
	}

	/////////////////////////////////////////////////////gets and sets
	
	public room[] getNextTo() {
		return nextTo;
	}

	public void setNextTo(int x,room nextTo) {
		this.nextTo[x] = nextTo;
	}

	public character[] getGuests() {
		return guests;
	}

	public void setGuests(character guests) {
		this.guests[guestsNum] = guests;
	}
	
	public void resetGuests() {
		
		//we delete all the guests in this room to update the data in each round
		for(int i = 0; guests[i] != null; i++) {
			
			guests[i] = null;
			
		}
		
	}
	
	public void increaseGuestsNum() {
		
		guestsNum++;
		
	}

	public int getGuestsNum() {
		return guestsNum;
	}

	public void setGuestsNum(int guestsNum) {
		this.guestsNum = guestsNum;
	}

	public object[] getObjects() {
		return objects;
	}

	public void setObjects(int x, object object) {
		this.objects[x] = object;
	}

	public int getObjectsNum() {
		return objectsNum;
	}

	public void setObjectsNum(int onjectsNum) {
		this.objectsNum = onjectsNum;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}

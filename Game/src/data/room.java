package data;

public class room extends DataFather{
	
	private int guestsNum = 0;
	private character[] guests = new character[100];
	
	private int objectsNum = 0;
	private object[] objects = new object[100];
	
	private room[] nextTo = new room[100];
	
	public room(String name_set){
		
		setName(name_set);
		
	}

	public room[] getNextTo() {
		return nextTo;
	}

	public void setNextTo(int x,room nextTo) {
		this.nextTo[x] = nextTo;
	}
	
	public void printNextTo() {
		
		for(int i = 0; nextTo[i]!=null;i++) {
			
			System.out.print(nextTo[i].getName() + " ");
			
		}
		
		
	}

	public character[] getGuests() {
		return guests;
	}

	public void setGuests(character guests) {
		this.guests[guestsNum] = guests;
	}
	
	public void resetGuests() {
		
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
	
}
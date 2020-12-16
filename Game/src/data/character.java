package data;

import java.awt.Image;

import tools.imgGenerator;

public class character extends DataFather{
	
	private room location;
	private object object = null;
	
	private boolean asked = false;
	private character asker;
	
	private room goal_location;
	private object goal_object;
	
	private boolean goal_obj = false;
	private Image img;
	private int medal = -1;
	
	private character[] CharBeliefs = new character[100];
	private object[] ObjBeliefs = new object[100];
		
	public character(String name_set) {
		
		imgGenerator gen = new imgGenerator();
		setImg(gen.img);
		setName(name_set);
		
	}

	public boolean completeGoal() {
				
		if(goal_object == object) {
			
			goal_obj = true;
			
		}
		
		if(location == goal_location && (object == goal_object || goal_object == null)) {

			if( goal_object == null && object != null) {
				
				dropObject();
				
			}

			return true;
			}
		
		else {
			return false;
		}
		
	}
	
	/////////////////////////////////////////////////////Actions	
	
	public void moveTo(room newRoom) {
		
		location = newRoom;
		
	}	
	
	public void takeObject(object newObject) {
		
		object = newObject;
		object.setOwner(this);
		
	}
	
	public void dropObject() {
		
		object.setLocation(location);
		object.dropObject();
		object = null;
		
	}
	
	public void skip() {}
	
	/////////////////////////////////////////////////////gets and sets
	
	public room getLocation() {
		return location;
	}

	public void setLocation(room location) {
		this.location = location;
	}

	public object getObject() {
		return object;
	}

	public void setObject(object object) {
		this.object = object;
	}

	public room getGoal_location() {
		return goal_location;
	}

	public void setGoal_location(room goal_location) {
		this.goal_location = goal_location;
	}

	public object getGoal_object() {
		return goal_object;
	}

	public void setGoal_object(object goal_object) {
		this.goal_object = goal_object;
	}

	public boolean isAsked() {
		return asked;
	}

	public void setAsked(boolean asked) {
		this.asked = asked;
	}

	public boolean isGoal_obj() {
		return goal_obj;
	}

	public void setGoal_obj(boolean goal_obj) {
		this.goal_obj = goal_obj;
	}

	public character getAsker() {
		return asker;
	}

	public void setAsker(character asker) {
		this.asker = asker;
	}

	public int getMedal() {
		return medal;
	}

	public void setMedal(int medal) {
		this.medal = medal;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void updateBeliefs(character[] characters,object[] objects) {
				
		for(int i = 0; CharBeliefs[i] != null; i++) {
						
			if(characters[i].getLocation().equals(location)) {
				
				CharBeliefs[i].setLocation(location);
				
			}
			
		}
		
		for(int i = 0; ObjBeliefs[i] != null; i++) {
			
			if(objects[i].getLocation() != null) {
				
				if(objects[i].getLocation().equals(location)) {
					
					ObjBeliefs[i].setLocation(characters[i].getLocation());
					
				}
				
			}
			
		}
		
	}
	
	public void setBeliefs(character[] characters,object[] objects) {
				
		room unknown = new room("unknown");
		
		for(int i = 0; characters[i] != null; i++) {
			
			CharBeliefs[i] = new character(characters[i].getName());
			
			if(characters[i].getLocation().equals(location)) {
				
				CharBeliefs[i].setLocation(location);
				
			}else {CharBeliefs[i].setLocation(unknown);}
			
		}
		
		for(int i = 0; objects[i] != null; i++) {
			
			ObjBeliefs[i] = new object(objects[i].getName());
			if(objects[i].getLocation() != null) {
				
				if(objects[i].getLocation().equals(location)) {
					
					ObjBeliefs[i].setLocation(characters[i].getLocation());
					
				}else {ObjBeliefs[i].setLocation(unknown);}
				
			}else {ObjBeliefs[i].setLocation(unknown);}
			
		}
		
	}
	
	public character[] getCharBeliefs() {
		
		return CharBeliefs;
		
	}
	
	public void setCharBeliefs(character[] chars) {
		
		CharBeliefs = chars;
		
	}

	public object[] getObjBeliefs() {
		return ObjBeliefs;
	}

	public void setObjBeliefs(object[] objBeliefs) {
		ObjBeliefs = objBeliefs;
	}

}

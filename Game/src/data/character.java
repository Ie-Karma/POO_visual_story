package data;

public class character extends DataFather{
	
	private room location;
	private object object = null;
	
	private boolean asked = false;
	private character asker;
	
	private room goal_location;
	private object goal_object;
	
	private boolean goal_obj = false;
	
	@SuppressWarnings("unused")
	private boolean GOAL = false;
	
	public character(String name_set) {
		
		setName(name_set);
		
	}

	public boolean completeGoal() {
				
		if(goal_object == object) {
			
			goal_obj = true;
			
		}
		
		if(location == goal_location && (object == goal_object || goal_object == null)) {
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
	
	public void askFor() {}
	
	public void giveTo() {}
	
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


}
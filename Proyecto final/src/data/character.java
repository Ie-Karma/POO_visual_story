package data;

public class character extends DataFather{
	
	private room location;
	private objet objet;
	
	private room goal_location;
	private objet goal_objet;
	
	@SuppressWarnings("unused")
	private boolean GOAL = false;
	
	public character(String name_set) {
		
		setName(name_set);
		
	}

	public boolean completeGoal() {
		
		if(location == goal_location && (objet == goal_objet || goal_objet == null)) {
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
	
	public void takeObjet(objet newObjet) {
		
		objet = newObjet;
		
	}
	
	public void dropObjet() {
		
		objet.setLocation(location);
		objet.dropObjet();
		objet = null;
		
	}
	
	public void skip() {}
	
	/////////////////////////////////////////////////////gets and sets
	
	public room getLocation() {
		return location;
	}

	public void setLocation(room location) {
		this.location = location;
	}

	public objet getObjet() {
		return objet;
	}

	public void setObjet(objet objet) {
		this.objet = objet;
	}

	public room getGoal_location() {
		return goal_location;
	}

	public void setGoal_location(room goal_location) {
		this.goal_location = goal_location;
	}

	public objet getGoal_objet() {
		return goal_objet;
	}

	public void setGoal_objet(objet goal_objet) {
		this.goal_objet = goal_objet;
	}
	
}

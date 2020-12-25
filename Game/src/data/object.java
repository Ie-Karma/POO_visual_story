package data;

public class object extends DataFather{
	
	//we save the location of the object in this two objects
	//depending on whether someone owns it or is thrown away
	private character owner;
	private room location;
	
	public object(String name_set) {
		
		setName(name_set);
		
	}
	
	/////////////////////////////////////////////////////Actions
	
	public void dropObject() {
		
		//we delete the owner
		owner = null;
		
	}
	
	/////////////////////////////////////////////////////gets and sets

	public character getOwner() {
		return owner;
	}
	public void setOwner(character owner) {
		this.owner = owner;
	}
	public room getLocation() {
		return location;
	}
	public void setLocation(room location) {
		this.location = location;
	}
	

}

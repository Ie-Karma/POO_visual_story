package data;

public class object extends DataFather{
	
	private character owner;
	private room location;
	
	public object(String name_set) {
		
		setName(name_set);
		
	}
	
	/////////////////////////////////////////////////////Actions
	
	public void dropObject() {
		
		owner = null;
		
	}
	
	/////////////////////////////////////////////////////gets and sets
	public void getPosition() {
		
		if(owner == null) {
			
			System.out.print(location.getName());
			
		}else {System.out.print(owner.getName());}
		
	}

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

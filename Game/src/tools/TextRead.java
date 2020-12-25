package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.*;

public class TextRead {

	//with this class we go through the txt to create the objects
	private String inicio;
	private room[] rooms_ini = new room[100];
	private character[] chars_ini = new character[100];
	private object[] objects_ini = new object[100];
	private int room_num = 0;
	private int char_num = 0;
	private int obj_num = 0;

	@SuppressWarnings("resource")
	public TextRead() throws IOException {
		
		//first we open the 2 txt and save them into a string
        try {
        	
            BufferedReader br = new BufferedReader(new FileReader("inicio.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine(); 
            
            while (line != null) { 
            	
            	sb.append(line).append("\n"); 
            	line = br.readLine(); 
            	
            	} 
            
            String string = sb.toString();
            string = string.toUpperCase();
 			setInicio(string);
 			inicioData();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Could not find or open the file inicio.txt");
			e.printStackTrace();
			System.exit(0);
			
		}
        
        try {
        	
            BufferedReader br = new BufferedReader(new FileReader("objetivos.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine(); 
            
            while (line != null) { 
            	
            	sb.append(line).append("\n"); 
            	line = br.readLine(); 
            	
            	} 
            
            String string = sb.toString();
            string = string.toUpperCase();
 			setInicio(string);
 			goalData(string);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Could not find or open the file objetivos.txt");
			e.printStackTrace();
			System.exit(0);
			
		}
		
	}
	
	private void inicioData(){
		
		int cont = 0;
		String todo = getInicio();
		boolean allRooms = false;
		boolean allChars = false;
		boolean allObjects = false;
		
		//loops through the text until it finds characters like <
		//when it finds these characters followed by a letter such that L, P or O 
		//calls its corresponding function to create the rooms, characters or objects respectively
		while(allRooms ==false && allChars == false && allObjects ==false) {
			
			while(cont < todo.length()) {
				
				if(todo.charAt(cont) == '<') {
					
					cont++;
					switch (todo.charAt(cont)){
					
					case 'L':
						while(todo.charAt(cont) != '>') {cont++;}
						cont++;cont++;
						if(allRooms == false) {inicioDataRooms(cont);allRooms= true;}
						break;
						
					case 'P':
						while(todo.charAt(cont) != '>') {cont++;}
						cont++;cont++;
						if(allChars == false) {inicioDataCharacters(cont);allChars = true;}
						break;
						
					case 'O':
						while(todo.charAt(cont) != '>') {cont++;}
						cont++;cont++;
						if(allObjects == false) {inicioDataObjects(cont);allObjects = true;}
						break;
					
					}
					
				}else {cont++;}

			}
			
		}
		
	}
	
	private void goalData(String todo) {
		
		int cont = 0;
		boolean allChars = false;
		boolean allObjects = false;
		
		//loops through the text until it finds characters like <
		//when it finds these characters followed by a letter such that L, P or O 
		//calls its corresponding function to determinate the goals
		while(allChars == false && allObjects == false ) {
			
			while(cont < todo.length()) {
				
				if(todo.charAt(cont) == '<') {
					
					cont++;
					switch (todo.charAt(cont)){
						
					case 'L':
						while(todo.charAt(cont) != '>') {cont++;}
						cont++;cont++;
						if(allChars == false) {goalDataCharacters(cont);allChars = true;}
						break;
						
					case 'P':
						while(todo.charAt(cont) != '>') {cont++;}
						cont++;cont++;
						if(allObjects == false) {goalDataObjects(cont);allObjects = true;}
						break;
					
					}
					
				}else {cont++;}
				
			}
		}
		
	}

	private void goalDataCharacters(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		String goal = "";
		int cont = cont_0;
		int loc_cont = 0;
		int char_cont = 0;
		
		//loops from '>' (the beginning of all characters goals) to '<' (the end of characters goals)
		//if it finds a '(' or a line break, it saves what has been read up to that moment as the character name of the next goal
		//what is between those parentheses saves it as the location goal of said character
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			while(todo.charAt(cont) != '(' && todo.charAt(cont) != '\n') {
				
				name = name + todo.charAt(cont);
				cont++;
				
			}
			if(todo.charAt(cont) == '(') {
				cont++;
				while(todo.charAt(cont) != ')') {
					
					goal = goal + todo.charAt(cont);
					cont++;
				}
				
			}
			
			for(int i = 0; i<char_num;i++) {
				
				if(getChars_ini()[i].getName().equals(name)) {
					
					char_cont = i;
					
				}
				
			}
			
			for(int i = 0; i<room_num;i++) {
				
				if(getRooms_ini()[i].getName().equals(goal)) {
					
					loc_cont = i;
					
				}
				
			}
			
			getChars_ini()[char_cont].setGoal_location(getRooms_ini()[loc_cont]);
			
			name = "";
			goal = "";
			cont++;
			cont++;
			
		}
		
	}

	private void goalDataObjects(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		String goal = "";
		int tam = todo.length();
		int cont = cont_0;
		int obj_cont = 0;
		int char_cont = 0;
		boolean fin = false;
		
		//loops from '>' (the beginning of all objects goals) to '<' (the end of objects goals)
		//if it finds a '(' or a line break, it saves what has been read up to that moment as the name of the object of the next goal
		//what is between those parentheses saves it as the goal owner of the object
		while(cont < tam && todo.charAt(cont) != '<' && fin == false) {
			

			while(todo.charAt(cont) != '(' && fin == false) {
				
				if(todo.charAt(cont)=='\n'){name = "";goal = "";}else {goal = goal + todo.charAt(cont);}
								
				if(cont == (tam-1)) {fin = true;}else {fin = false;cont++;}

			}

			
			if(todo.charAt(cont) == '(') {
				cont++;
				while(todo.charAt(cont) != ')') {
					
					name = name + todo.charAt(cont);
					cont++;
					
				}
				
			}

			for(int i = 0; i<char_num;i++) {
				
				if(getChars_ini()[i].getName().equals(name)) {
					
					char_cont = i;
					
				}
				
			}

			for(int i = 0; i<obj_num;i++) {
								
				if(getObjects_ini()[i].getName().equals(goal)) {
					
					obj_cont = i;
					
				}
				
			}
			
			getChars_ini()[char_cont].setGoal_object(getObjects_ini()[obj_cont]);
						
			name = "";
			goal = "";
			cont++;
			cont++;
			
		}

	}
	
	private void inicioDataRooms(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int room_cont = 0;
		int next = 0;
		
		//loops from '>' (the beginning of all rooms) to '<' (the end of rooms)
		//if it finds a '(' or a line break, it saves what has been read up to that moment as the name of a new room
		//what is between those parentheses saves it as the rooms accessible from that room
		while(cont < todo.length() && todo.charAt(cont) != '<') {
						
			if(todo.charAt(cont) == '(') {
				
				while(todo.charAt(cont) != ')') {
					
					cont++;
					
				}
				
			}else {
				
				if(todo.charAt(cont) == '\n') {
					
					getRooms_ini()[room_num] = new room(name);
					room_num++;
					name = "";
					
				}else {
					
					name = name + todo.charAt(cont);
					
				}
				
			}
			
			cont++;
			
		}
		
		cont = cont_0;
		
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			if(todo.charAt(cont) == '(') {
				
				cont++;

				while(todo.charAt(cont) != '\n') {
					
					if(todo.charAt(cont) == ',' || todo.charAt(cont) == ')') {
						
						for(int x = 0; x<room_num;x++) {
														
							if(getRooms_ini()[x].getName().equals(name)) {
								
								getRooms_ini()[room_cont].setNextTo(next,getRooms_ini()[x]);
								next++;
								name = "";
								
							}
							
						}
						
						if(todo.charAt(cont) == ',') {cont++;}
						if(todo.charAt(cont) == ')') {room_cont++;next = 0;}
						cont++;
						
					}else {
						
						name = name + todo.charAt(cont);
						cont++;
						
					}
					
				}
				
			}
			
			cont++;
			
		}
		
	}
	
	private void inicioDataCharacters(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int loc_num = 0;
		String loc = "";
		
		//loops from '>' (the beginning of all characters) to '<' (the end of characters)
		//if it finds a '(' or a line break, it saves what has been read up to that moment as the name of a new character
		//what is between those parentheses saves it as the location of said character
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			if(todo.charAt(cont) == '(') {
				
				getChars_ini()[char_num] = new character(name);
				char_num++;
				name = "";
				
				while(todo.charAt(cont) != ')') {
					
					if(todo.charAt(cont) == '(') {cont++;}
					loc = loc + todo.charAt(cont);
					cont++;

				}
				
				for(int x = 0; x<room_num;x++) {
					
					if(getRooms_ini()[x].getName().equals(loc)) {
						
						getChars_ini()[loc_num].setLocation(getRooms_ini()[x]);
						getRooms_ini()[x].setGuests(getChars_ini()[loc_num]);
						getRooms_ini()[x].setGuestsNum(getRooms_ini()[x].getGuestsNum()+1);
						loc_num++;
						loc = "";
						
					}
					
				}cont++;
				
			}else {
					
				name = name + todo.charAt(cont);
					
			}
			
			cont++;
			
		}
		
	}
	
	private void inicioDataObjects(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int loc_num = 0;
		String loc = "";
		
		//loops from '>' (the beginning of all objects) to '<' (the end of objects)
		//if it finds a '(' or a line break, it saves what has been read up to that moment as the name of a new object
		//what is between those parentheses saves it as the owner of the object
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			if(todo.charAt(cont) == '(') {
				
				getObjects_ini()[obj_num] = new object(name);
				obj_num++;
				name = "";
				
				while(todo.charAt(cont) != ')') {
					
					if(todo.charAt(cont) == '(') {cont++;}
					loc = loc + todo.charAt(cont);
					cont++;

				}
				
				for(int x = 0; x<room_num;x++) {
					
					if(getRooms_ini()[x].getName().equals(loc)) {
						
						getObjects_ini()[loc_num].setLocation(getRooms_ini()[x]);
						loc_num++;
						loc = "";
						
					}
					
				}
				
				for(int x = 0; x<char_num;x++) {
					
					if(getChars_ini()[x].getName().equals(loc)) {
						
						getObjects_ini()[loc_num].setOwner(getChars_ini()[x]);
						getChars_ini()[x].setObject(getObjects_ini()[loc_num]);
						loc_num++;
						loc = "";
						
					}
					
				}
				
				cont++;
				
			}else {
					
				name = name + todo.charAt(cont);
					
			}
			
			cont++;
			
		}

		
	}

	///////////////////////////////////////////////////////////Sets and Gets
	
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public room[] getRooms_ini() {
		return rooms_ini;
	}
	
	public void setRooms_ini(room[] rooms_ini) {
		this.rooms_ini = rooms_ini;
	}

	public character[] getChars_ini() {
		return chars_ini;
	}

	public void setChars_ini(character[] chars_ini) {
		this.chars_ini = chars_ini;
	}

	public object[] getObjects_ini() {
		return objects_ini;
	}

	public void setObjects_ini(object[] objects_ini) {
		this.objects_ini = objects_ini;
	}

}

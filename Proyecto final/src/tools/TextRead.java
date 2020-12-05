package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.*;

public class TextRead {

	private String inicio;
	private room[] rooms_ini = new room[100];
	private character[] chars_ini = new character[100];
	private objet[] objets_ini = new objet[100];
	private int room_num = 0;
	private int char_num = 0;

	@SuppressWarnings("resource")
	public TextRead() throws IOException {
		
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
			
			System.out.println("No se Pudo Encontrar El Archivo inicio.txt");
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
 			objetivosData(string);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No se Pudo Encontrar El Archivo objetivos.txt");
			e.printStackTrace();
			System.exit(0);
			
		}
		
	}
	
	private void objetivosData(String todo) {
		
		int cont = 0;
		boolean allChars = false;
		boolean allObjets = false;
		
		while(allChars == false && allObjets == false ) {
			
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
						if(allObjets == false) {goalDataObjets(cont);allObjets = true;}
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
		
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			while(todo.charAt(cont) != '(' ) {
				
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

	private void goalDataObjets(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		String goal = "";
		int cont = cont_0;
		int obj_cont = 0;
		int char_cont = 0;
		
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			while(todo.charAt(cont) != '(') {
				
				goal = goal + todo.charAt(cont);
				
				if(todo.charAt(cont)=='\n'){name = "";goal = "";}
				
				cont++;
				
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
			
			for(int i = 0; i<room_num;i++) {
				
				if(getObjets_ini()[i].getName().equals(goal)) {
					
					obj_cont = i;
					
				}
				
			}
			
			getChars_ini()[char_cont].setGoal_objet(getObjets_ini()[obj_cont]);
			
			name = "";
			goal = "";
			cont++;
			cont++;
			
		}

	}
	
	public void inicioDataRooms(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int room_cont = 0;
		int next = 0;
		
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
	
	public void inicioDataCharacters(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int loc_num = 0;
		String loc = "";
		
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
	
	public void inicioDataObjets(int cont_0) {
		
		String todo = getInicio();
		String name = "";
		int cont = cont_0;
		int obj_num = 0;
		int loc_num = 0;
		String loc = "";
		
		while(cont < todo.length() && todo.charAt(cont) != '<') {
			
			if(todo.charAt(cont) == '(') {
				
				getObjets_ini()[obj_num] = new objet(name);
				obj_num++;
				name = "";
				
				while(todo.charAt(cont) != ')') {
					
					if(todo.charAt(cont) == '(') {cont++;}
					loc = loc + todo.charAt(cont);
					cont++;

				}
				
				for(int x = 0; x<room_num;x++) {
					
					if(getRooms_ini()[x].getName().equals(loc)) {
						
						getObjets_ini()[loc_num].setLocation(getRooms_ini()[x]);
						loc_num++;
						loc = "";
						
					}
					
				}
				
				for(int x = 0; x<char_num;x++) {
					
					if(getChars_ini()[x].getName().equals(loc)) {
						
						getObjets_ini()[loc_num].setOwner(getChars_ini()[x]);
						getChars_ini()[x].setObjet(getObjets_ini()[loc_num]);
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
	
	public void inicioData(){
		
		int cont = 0;
		String todo = getInicio();
		boolean allRooms = false;
		boolean allChars = false;
		boolean allObjets = false;
		
		
		while(allRooms ==false && allChars == false && allObjets ==false) {
			
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
						if(allObjets == false) {inicioDataObjets(cont);allObjets = true;}
						break;
					
					}
					
				}else {cont++;}

			}
			
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

	public objet[] getObjets_ini() {
		return objets_ini;
	}

	public void setObjets_ini(objet[] objets_ini) {
		this.objets_ini = objets_ini;
	}

}

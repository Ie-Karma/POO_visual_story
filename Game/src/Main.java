import java.io.IOException;

import data.*;
import tools.TextRead;

public class Main {

	//creamos arrays de los 3 tipos de objectos que usaremos
	static room[] rooms;
	static character[] characters;
	static object[] objects;
		
	public static void main(String[] args) throws IOException {

		//iniciamos el TextRead de inicio
		TextRead inicio = new TextRead();
		
		//con el txt leido igualamos los datos a los objectos creados
		rooms = inicio.getRooms_ini();
		characters = inicio.getChars_ini();
		objects = inicio.getObjects_ini();
		
		playerOnArray();
		
	}

	//funcion para resetear los guests y su numero en cada room, ha de ejecutarse cada ronda
	@SuppressWarnings("unused")
	private static void updateGuests() {
		
		for(int i = 0; rooms[i] != null; i++) {
			
			rooms[i].setGuestsNum(0);
			rooms[i].resetGuests();
			
		}
		
		for(int i = 0; characters[i] != null; i++) {
			
			characters[i].getLocation().setGuests(characters[i]);
			characters[i].getLocation().increaseGuestsNum();
			
		}
		
	}
	
	//buscamos la posición de player en el array de characters y le colocamos en la posición 0
	private static void playerOnArray() {
		
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
		
	}

}

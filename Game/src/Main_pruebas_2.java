import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import data.*;
import tools.*;
import graphic.*;

public class Main_pruebas_2 {

	//creamos arrays de los 3 tipos de objectos que usaremos
	static room[] rooms;
	static character[] characters;
	static object[] objects;
	static actionsTools tool = new actionsTools();
	static dataTools dataTool = new dataTools();
	static Kimput imput = new Kimput();	
	static screen screen = new screen();
	
	public static void main(String[] args) throws IOException {
		
		//iniciamos el TextRead de inicio
		TextRead inicio = new TextRead();
		
		//con el txt leido igualamos los datos a los objectos creados
		rooms = inicio.getRooms_ini();
		characters = inicio.getChars_ini();
		objects = inicio.getObjects_ini();
		
		//buscamos la posici�n de player en el array de characters y le colocamos en la posici�n 0
		characters = dataTool.playerOnArray(characters);
		
		//recorre todos los characters sumandole un guestsNum al room en el que est�n
		//de esta forma actualizo el numero de characters en cada room
		//updateGuests();

///////////////////////////////////////////////////////////////////////test zone

		//pruebas();
						
///////////////////////////////////////////////////////////////////////test zone
		
	}
	
	@SuppressWarnings("unused")
	private static void pruebas() {
			
		int ronda = 0;
	
		while(dataTool.endGame(characters) == false) {
						
			System.out.println("");

			System.out.println("---------------------------------");
			System.out.println("Ronda " + ronda);
			System.out.println("---------------------------------");

			System.out.println("");
			
			allCharacters();
			dataTool.updateData(rooms,characters,objects);
			rooms = dataTool.updateRooms(rooms);
			ronda++;
			
		}
		
		System.out.println("---------------------------------");
		System.out.println("");

		System.out.println("- THE GAME IS OVER, ALL PLAYERS HAVE REACHED THEIR GOALS -");
		
		System.out.println("");

		int p = 0;
		
		for(int i = 0; characters[i] != null; i++) {
			
			p++;
			
		}
		for(int i = 0; characters[i] != null; i++) {
			
			if(characters[i].getMedal() == 0) {characters[i].setMedal(p);}
			
		}
		
		character[] podiumChars = new character[p];
		
		for(int i = 0; i<p; i++) {
			
			for(int x = 0; characters[x] != null; x++) {
				
				if(characters[x].getMedal() == (i+1)) {podiumChars[i] = characters[x];}
				
			}
			
		}
		
		for(int i = 0; characters[i] != null; i++) {
			
			System.out.println(characters[i].getName() + " has finished " + podiumChars[i].getMedal());
			
		}
		
	}

	private static void allCharacters() {
		
		for(int i = 0; characters[i] != null; i++) {
			
			characters[i] = tool.randomAction(characters[i]);
			
		}
		
	}

}

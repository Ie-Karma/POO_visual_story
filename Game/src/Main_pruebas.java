import java.io.IOException;
import java.util.Scanner;

import data.*;
import tools.*;

public class Main_pruebas {

	//creamos arrays de los 3 tipos de objectos que usaremos
	static room[] rooms;
	static character[] characters;
	static object[] objects;
	static actionsTools tool = new actionsTools();
	static dataTools dataTool = new dataTools();
	private int podium = 1;
		
	public static void main(String[] args) throws IOException {
		
		//iniciamos el TextRead de inicio
		TextRead inicio = new TextRead();
		
		//con el txt leido igualamos los datos a los objectos creados
		rooms = inicio.getRooms_ini();
		characters = inicio.getChars_ini();
		objects = inicio.getObjects_ini();
		
		//buscamos la posición de player en el array de characters y le colocamos en la posición 0
		characters = dataTool.playerOnArray(characters);
		
		//recorre todos los characters sumandole un guestsNum al room en el que estén
		//de esta forma actualizo el numero de characters en cada room
		//updateGuests();
		
		//prints de prueba para comprobar posiciones o goals
		//printGoals();
		//printData();

///////////////////////////////////////////////////////////////////////test zone

		pruebas();
			
///////////////////////////////////////////////////////////////////////test zone
		

	}
	
	@SuppressWarnings("resource")
	private static void pruebas() {
			
		int seleccion = 0;
		boolean finish = false;
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
			//System.out.println(characters[i].getName() + " has finished " + characters[i].getMedal());
			
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
		
		/*
		
		while (finish == false) {
			
			Scanner leer = new Scanner (System.in);
			System.out.println("¿Otra ronda?");
			seleccion = leer.nextInt();
			
			switch(seleccion) {
			
			case 3:
				printGoals();break;
			
			case 2:				
				printData();break;
			
			case 1:
				System.out.println("Ronda " + ronda);
				allCharacters();
				dataTool.updateData(rooms,characters,objects);
				rooms = dataTool.updateRooms(rooms);
				ronda++;
				break;
			
			case 0:
				finish = true;
				break;
			}
			
			
		}
		
		*/
		
	}

	private static void allCharacters() {
		
		for(int i = 0; characters[i] != null; i++) {
			
			characters[i] = tool.randomAction(characters[i]);
			
		}
		
	}

	@SuppressWarnings("unused")
	private static void printGoals() {
		
		for(int i=0; characters[i]!=null; i++) {
			
			System.out.print(characters[i].getName());
			System.out.println("");
			if(characters[i].getGoal_location() != null) {
				
				System.out.print("Su objectivo es llegar a " + characters[i].getGoal_location().getName());

			}
			
			if(characters[i].getGoal_object() != null) {
				
				System.out.print(" con " + characters[i].getGoal_object().getName());
				
			}
			System.out.println("");

		}
		
	}
	
	private static void printData(){
		
		dataTool.updateData(rooms,characters,objects);
		rooms = dataTool.updateRooms(rooms);
		
		System.out.println("");

		for(int i=0; rooms[i]!=null; i++) {
			
			System.out.print("Room: " + rooms[i].getName() + ", hay " + rooms[i].getGuestsNum() + " personas y "+ rooms[i].getObjectsNum() +" objetos, está pegado a ");
			rooms[i].printNextTo();
			System.out.println("");
			
		}
		
		System.out.println("");
		
		for(int i=0; characters[i]!=null; i++) {
			
			System.out.print("Character: " + characters[i].getName() + ". Situado en " + characters[i].getLocation().getName());
			if(characters[i].getObject() != null) {System.out.print(" y tiene " + characters[i].getObject().getName());}
			else {System.out.print(" y no tiene nada");}
			System.out.println("");
			
		}
		
		System.out.println("");

		for(int i=0; objects[i] != null; i++) {
			
			System.out.print("Object: " + objects[i].getName() + ". Situado en ");
			objects[i].getPosition();
			System.out.println("");
			
		}

	}

}

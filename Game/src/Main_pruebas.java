import java.io.IOException;

import data.*;
import tools.TextRead;

public class Main_pruebas {

	//creamos arrays de los 3 tipos de objetos que usaremos
	static room[] rooms;
	static character[] characters;
	static objet[] objets;
		
	public static void main(String[] args) throws IOException {

		//iniciamos el TextRead de inicio
		TextRead inicio = new TextRead();
		
		//con el txt leido igualamos los datos a los objetos creados
		rooms = inicio.getRooms_ini();
		characters = inicio.getChars_ini();
		objets = inicio.getObjets_ini();
		
		//buscamos la posición de player en el array de characters y le colocamos en la posición 0
		playerOnArray();
		
		//recorre todos los characters sumandole un guestsNum al room en el que estén
		//de esta forma actualizo el numero de characters en cada room
		//updateGuests();
		
		//prints de prueba para comprobar posiciones o goals
		//printGoals();
		//printData();

///////////////////////////////////////////////////////////////////////test zone

		for(int i = 0; rooms[i] != null; i++) {
			
			System.out.println(rooms[i].getName());
			System.out.println("");
			character guests[] = rooms[i].getGuests();
			
			for(int x = 0; guests[x] != null; x++) {
				
				System.out.println(guests[x].getName());
				
			}
			
			System.out.println("-----------------");
			
		}
		
		characters[0].moveTo(rooms[3]);
		characters[1].dropObjet();
		System.out.println("-----------------------");
		updateGuests();
		
		for(int i = 0; rooms[i] != null; i++) {
			
			System.out.println(rooms[i].getName());
			System.out.println("");
			
			if(rooms[i].getGuests() != null) {
				
				character guests[] = rooms[i].getGuests();
				
				for(int x = 0; guests[x] != null; x++) {
				
					System.out.println(guests[x].getName());
				
				}
				
			}
			System.out.println("-----------------");
			
		}
				
///////////////////////////////////////////////////////////////////////test zone
		

	}

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

	@SuppressWarnings("unused")
	private static void printGoals() {
		
		for(int i=0; characters[i]!=null; i++) {
			
			System.out.print(characters[i].getName());
			System.out.println("");
			if(characters[i].getGoal_location() != null) {
				
				System.out.print("Su objetivo es llegar a " + characters[i].getGoal_location().getName());

			}
			
			if(characters[i].getGoal_objet() != null) {
				
				System.out.print(" con " + characters[i].getGoal_objet().getName());
				
			}
			System.out.println("");

		}
		
	}
	
	@SuppressWarnings("unused")
	private static void printData(){
		
		updateGuests();
		
		System.out.println("");

		for(int i=0; rooms[i]!=null; i++) {
			
			System.out.print("Room: " + rooms[i].getName() + ", hay " + rooms[i].getGuestsNum() + " personas y está pegado a ");
			rooms[i].printNextTo();
			System.out.println("");
			
		}
		
		System.out.println("");
		
		for(int i=0; characters[i]!=null; i++) {
			
			System.out.print("Character: " + characters[i].getName() + ". Situado en " + characters[i].getLocation().getName());
			if(characters[i].getObjet() != null) {System.out.print(" y tiene " + characters[i].getObjet().getName());}
			else {System.out.print(" y no tiene nada");}
			System.out.println("");
			
		}
		
		System.out.println("");

		for(int i=0; objets[i] != null; i++) {
			
			System.out.print("Objet: " + objets[i].getName() + ". Situado en ");
			objets[i].getPosition();
			System.out.println("");
			
		}

	}

	private static void playerOnArray() {
		
		int pos = 0;
		
		for (int i = 0; characters[i] != null; i++) {
			
			if(characters[i].getName().equals("PLAYER")) {pos = i;}
			
		}
		
		System.out.println("------------------");
		
		if(pos != 0) {
			
			character comodin;
			comodin = characters[0];
			characters[0] = characters[pos];
			characters[pos] = comodin;
			
		}
		
	}

}

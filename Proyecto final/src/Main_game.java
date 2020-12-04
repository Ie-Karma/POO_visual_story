import java.io.IOException;

import data.*;
import tools.TextRead;

public class Main_game {

	
	public static void main(String[] args) throws IOException {

		//creamos arrays de los 3 tipos de objetos que usaremos
		room[] rooms = new room[100];
		character[] characters = new character[100];
		objet[] objets = new objet[100];

		//iniciamos el TextRead de inicio
		TextRead inicio = new TextRead();
		
		//con el txt leido igualamos los datos a los objetos creados
		rooms = inicio.getRooms_ini();
		characters = inicio.getChars_ini();
		objets = inicio.getObjets_ini();
		
///////////////////////////////////////////////////////////////////////test zone
		
		System.out.println("");
		
		for(int i=0; rooms[i]!=null; i++) {
			
			System.out.print("Room: " + rooms[i].getName() + ", hay " + rooms[i].getGuestsNum() + " personas y está pegado a ");
			rooms[i].printNextTo();
			System.out.println("");
			
		}
		
		System.out.println("");
		
		for(int i=0; characters[i]!=null; i++) {
			
			System.out.print("Player: " + characters[i].getName() + ". Situado en " + characters[i].getLocation().getName());
			if(characters[i].getObjet() != null) {System.out.print(" y tiene " + characters[i].getObjet().getName());}
			else {System.out.print(" y no tiene nada");}
			System.out.println("");
			System.out.print("Su objetivo es llegar a " + characters[i].getGoal_location().getName() + " con " + characters[i].getGoal_objet().getName());
			System.out.println("");
			
			
		}
		
		System.out.println("");

		for(int i=0; objets[i] != null; i++) {
			
			System.out.print("Objet: " + objets[i].getName() + ". Situado en ");
			objets[i].getPosition();
			System.out.println("");
			
		}
		
///////////////////////////////////////////////////////////////////////test zone
		
	}

}
